package com.videoprocessing;

import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * @author José Mario Naranjo Leiva
 * @version 0.1
 */
public class SegmentadorJugadores implements ISegmentadorJugadores {

	@Override
	public Mat obtenerMascaraCampo(Mat pFrame) {
		// Se convierte el espacio de color de la imagen de RGB a HSV
		Mat hsv = new Mat();
		hsv.create(pFrame.size(), CvType.CV_8U);
		Imgproc.cvtColor(pFrame, hsv, Imgproc.COLOR_BGR2HSV);
		
		// Se obtiene los canales del espacio de color HSV
		ArrayList<Mat> canales = new ArrayList<>();
		Core.split(hsv, canales);
		
		// Rangos para detectar el color verde
		Scalar verde_inferior = new Scalar(20,100,100);
		Scalar verde_superior = new Scalar(120,255,255);
		
		// Detectar la máscara con los rangos de verdor
		Mat mascaraCancha = new Mat();
		Core.inRange(canales.get(0), verde_inferior, verde_superior, mascaraCancha);
		
		// Rellenar los huecos de la máscara
		Imgproc.morphologyEx(mascaraCancha, mascaraCancha, Imgproc.MORPH_OPEN, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(40,40)));
		Imgproc.morphologyEx(mascaraCancha, mascaraCancha, Imgproc.MORPH_CLOSE, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(52,52)));
        	
		return mascaraCancha;
	}

	@Override
	public Mat obtenerMascaraJugadores(Mat pFrame) {
		
		// Se convierte el espacio de color de la imagen de RGB a HSV
		Mat hsv = new Mat();
		hsv.create(pFrame.size(), CvType.CV_8U);
		Imgproc.cvtColor(pFrame, hsv, Imgproc.COLOR_BGR2HSV);
		
		// Se obtiene los canales del espacio de color HSV
		ArrayList<Mat> canales = new ArrayList<>();
		Core.split(hsv, canales);
		
		// Normalización de la imagen
		Mat normalHSV = new Mat();
		Core.normalize(canales.get(0), normalHSV, 255, 0, Core.NORM_MINMAX);
		
		// Cálculo de la varianza local de la imagen
		Mat mu = new Mat();
		mu.create(pFrame.size(), pFrame.type());
		Imgproc.blur(normalHSV, mu, new Size(5,5));
		Core.absdiff(mu, normalHSV, mu);
		Core.pow(mu, 2.0, mu);
		
		// Aplicando la umbralización OTSU
		Imgproc.blur(mu, mu, new Size(10, 10));
        Imgproc.threshold(mu, mu, 0, 255, Imgproc.THRESH_OTSU);
        
        //Rellenar hollos en la imagen resultado
        Imgproc.morphologyEx(mu, mu, Imgproc.MORPH_OPEN, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5,5)));
        Imgproc.morphologyEx(mu, mu, Imgproc.MORPH_CLOSE, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(13,13)));
             
		return mu;
	}

	@Override
	public Mat obtenerBlobs(Mat pCancha, Mat pJugadores) {
		
		//Creación de la matriz resultante
		Mat hier = new Mat(pJugadores.rows(),pJugadores.cols(),pJugadores.type());
		
		// Realizar una operación AND sobre las imágenes de la cancha y jugadores
        Core.bitwise_and(pJugadores, pCancha, hier);
        
        // Quitar líneas que pueden quedar en la cancha
        int verticalSize = hier.rows() / 30;
        Mat verticalStruct = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(7,verticalSize));
        Imgproc.erode(hier, hier, verticalStruct, new Point(-1,-1), 1);
        Imgproc.dilate(hier, hier, verticalStruct, new Point(-1,-1), 1);
        
		return hier;
	}

}
