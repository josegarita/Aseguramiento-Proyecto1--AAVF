package BackEnd;

import java.util.ArrayList;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

/**
 * Esta clase crea el algoritmo de procesamiento del video.
 * 
 * @author Mariano Montero
 * @version 0.7
 * @since 03/09/16
 */
public class DeteccionEscenas {
	
	private final int PIXS=153600;
	
	
	/**
	 * Este metodo, es el que involucra todo los pasos del algoritmo de
	 * detección de escenas,en otras palabras el algoritmo completo, crea dos
	 * arreglos, uno de cortes y otro de no cortes
	 * 
	 * @param nombreArchivo
	 *            String con el nombre del video a ejecutar.
	 * @return void Solo efectua las operaciones.
	 */
	public void analizarVideo(String nombreArchivo) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat frame1 = new Mat();
		Mat frame2 = new Mat();
		ArrayList<Double> arrayDiferencias = new ArrayList<>();
		ArrayList<Integer> arrayCortes = new ArrayList<>();
		ArrayList<Integer> arrayNoCortes = new ArrayList<>();
		VideoCapture camera = new VideoCapture(nombreArchivo);
		double frameCount = camera.get(7);
		int i = 0;
		double distance;
		while (camera.read(frame1)) {
			if (frameCount >= i + 1 && i == 0) {
				try {
					camera.read(frame2);
					frame1 = FRAME2HIST(frame1);
					frame2 = FRAME2HIST(frame2);
					distance = calc_Bhattacharyya(frame1, frame2);
					arrayDiferencias.add(distance);
				} catch (Exception e) {
				}
			}
			if (frameCount >= i + 1 && i != 0) {
				frame1 = FRAME2HIST(frame1);
				distance = calc_Bhattacharyya(frame2, frame1);
				arrayDiferencias.add(distance);
				frame2 = frame1.clone();
			}
			i++;
		}
		i = 0;
		double media = calcular_Media(arrayDiferencias);
		double desvEstandar = calcular_DesvEstandar(arrayDiferencias);
		// Verificamos si es corte o no
		int cortes = 0;
		int noCortes = 0;
		for (i = 0; i < arrayDiferencias.size(); i++) {
			if (esCorte(media, desvEstandar, arrayDiferencias.get(i))) {
				arrayCortes.add(i);
				System.out.print("-" + i);
			} else {
				arrayNoCortes.add(i);
			}
		}
		System.out.println("Proceso terminado");
		// Termina el cronometro para ver cuanto dura procesando el algoritmo
		// Generar reporte XML o JSON

	}

	/**
	 * Convierte un frame a HSV
	 * 
	 * @param frame
	 *            un frame de un video o bien una imagen.
	 * @return Mat frame convertido.
	 */
	public Mat frame_HSV(Mat frame) {
		Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2HSV);
		return frame;
	}

	/**
	 * Deja listo el frame para poder aplicar el algoritmo.
	 * 
	 * @param frame
	 *            Frame a convertir.
	 * @return Mat Solo efectua las operaciones.
	 */

	private Mat normalizar5(Mat frame) {
		Mat imageHSV = new Mat();
		imageHSV = this.frame_HSV(frame);
		ArrayList<Mat> channels = new ArrayList<>();
		Core.split(imageHSV, channels);
		Mat H_Layer = channels.get(0);
		Mat histogramH = new Mat();
		ArrayList<Mat> ListvalorH = new ArrayList<Mat>();
		ListvalorH.add(H_Layer);
		Imgproc.calcHist(ListvalorH, new MatOfInt(0), new Mat(), histogramH,
				new MatOfInt(256), new MatOfFloat(0, 256), false);
		double cPixels = H_Layer.total();
		histogramH = normalizacion(histogramH, cPixels);
		return histogramH;
	}

	private Mat normalizacion(Mat hist1, double cPixels) {
		double tmp, sum = 0;
		for (int i = 0; i < hist1.total(); i++) {
			tmp = hist1.get(i, 0)[0];
			tmp = tmp / cPixels;
			hist1.put(i, 0, tmp);
			sum += hist1.get(i, 0)[0];
		}
		return hist1;
	}

	/**
	 * Calcula la distancia de Bhattacharyya entre dos histogramas metodo
	 * default opencv.
	 * 
	 * @param hist_h1
	 *            .
	 * @param hist_h2
	 *            .
	 * @return double distancia de Bhattacharyya.
	 */
	public double calcular_Bhattacharyya(Mat hist_h1, Mat hist_h2) {
		double distance = Imgproc.compareHist(hist_h1, hist_h2,
				Imgproc.CV_COMP_BHATTACHARYYA);
		return distance;
	}

	/**
	 * Calcula la media de un arreglo de doubles.
	 * 
	 * @param arrayDiferencias
	 *            arreglo a analizar .
	 * @return double media del arreglo.
	 */
	public double calcular_Media(ArrayList<Double> arrayDiferencias) {
		double acum = 0;
		for (int i = 0; i < arrayDiferencias.size(); i++) {
			acum += arrayDiferencias.get(i);
		}
		return acum / arrayDiferencias.size();
	}

	/**
	 * Calcula la desviacion estandar de un arreglo de doubles.
	 * 
	 * @param arrayDiferencias
	 *            arreglo a analizar .
	 * @return double desviacion estandar del arreglo.
	 */
	public double calcular_DesvEstandar(ArrayList<Double> arrayDiferencias) {
		double acum = 0;
		double Xi = 0;
		double media = calcular_Media(arrayDiferencias);
		for (int i = 0; i < arrayDiferencias.size(); i++) {
			Xi = arrayDiferencias.get(i);
			acum += (Xi - media) * (Xi - media);
		}
		return Math.sqrt(acum / arrayDiferencias.size());
	}

	/**
	 * Define dada la media y desv.estandar si un valor pertenece al corte o no.
	 * 
	 * @param media
	 *            .
	 * @param desviacion
	 *            .
	 * @param valor
	 *            valor a ver si es corte o no.
	 * @return boolean true si es corte de lo contrario, false.
	 */
	public boolean esCorte(double media, double desviacion, double valor) {
		if (valor >= media + desviacion) {
			return true;
		}
		return false;
	}

	/**
	 * Obtiene la capa h de un Mat HSV
	 * 
	 * @param imageHSV
	 *            un tipo Mat convertido en HSV .
	 * @return Mat true si es corte de lo contrario, false.
	 */
	public Mat obtenerCapaH(Mat imageHSV) {
		ArrayList<Mat> array = new ArrayList<Mat>();
		Core.split(imageHSV, array);
		return array.get(0);
	}

	/**
	 * Calculo de histograma con 256 bins y se normalizo
	 * 
	 * @param normalizedHSV
	 *            imagen normalizada .
	 * @return Mat histograma a 256 normalizado.
	 */
	// Calculo histograma con 256 bins y lo normalizo
	public Mat obtenerHistograma(Mat normalizedHSV) {
		ArrayList<Mat> matList = new ArrayList<Mat>();
		matList.add(normalizedHSV);
		Mat histogram = new Mat();
		MatOfFloat ranges = new MatOfFloat(0, 256);// 0 a 256 bins
		// Imgproc.cal
		Imgproc.calcHist(matList, new MatOfInt(0), new Mat(), histogram,
				new MatOfInt(256), ranges, false);
		// normalizar histograma
		return histogram;
	}

	/**
	 * Calculo de coeficiente Beta de Bhattacharyya
	 * 
	 * @param Mat
	 *            histograma1 .
	 * @param Mat
	 *            histograma2.
	 * @return coeficiente Beta de Bhattacharyya
	 */
	private double calcular_BhattacharyyaCoef(Mat hist1, Mat hist2) {
		double coef = 0;
		for (int i = 0; i < 256; i++) {
			coef += Math.sqrt(hist1.get(i, 0)[0] * hist2.get(i, 0)[0]);
		}
		return coef;
	}

	/**
	 * Calculo de coeficiente Alfa de Bhattacharyya (normalizado)
	 * 
	 * @param Mat
	 *            histograma1 .
	 * @param Mat
	 *            histograma2.
	 * @return coeficiente Alfa de Bhattacharyya(normalizado)
	 */
	private double coef_bhattacharya_norm(Mat h1, Mat h2) {
		double media1, media2, coef,M_Value;
		ArrayList<Double> arrayH1 = new ArrayList<Double>();
		ArrayList<Double> arrayH2 = new ArrayList<Double>();
		for (int i = 0; i < 256; i++) {
			arrayH1.add(h1.get(i, 0)[0]);
			arrayH2.add(h2.get(i, 0)[0]);
		}
		M_Value=Math.pow(256, 2);
		media1 = calcular_Media(arrayH1);
		media2 = calcular_Media(arrayH2);
		coef = 1 / Math.sqrt(media1 * media2 * M_Value );
		return coef;
	}

	/**
	 * Calcula la distancia de Bhattacharyya entre dos histogramas metodo creado
	 * por programador.
	 * 
	 * @param hist_h1
	 *            .
	 * @param hist_h2
	 *            .
	 * @return double distancia de Bhattacharyya.
	 */
	private double calc_Bhattacharyya(Mat hist1, Mat hist2) {
		double coefBhattacharyya, coefNorm, distancia;
		coefBhattacharyya = calcular_BhattacharyyaCoef(hist1, hist2);
		coefNorm = coef_bhattacharya_norm(hist1, hist2);
		distancia = Math.sqrt(1 - (coefBhattacharyya * coefNorm));
		return distancia;
	}
	
	/**
	 * 
	 */
	
	private Mat normalizarH(Mat hist) {
		double valTemp,valorSuma=0;
		for (int i = 0; i < 256; i++) {
			valTemp = hist.get(i, 0)[0];
			valTemp = valTemp / PIXS; 
			hist.put(i, 0, valTemp); 
			valorSuma += hist.get(i, 0)[0];
		}
		return hist;
	}
	/**
	 * 
	 */
	private Mat FRAME2HIST(Mat image) {
		Mat capaH = new Mat();
		ArrayList<Mat> channelsImage = new ArrayList<Mat>();
		Core.split(image, channelsImage);
		capaH = channelsImage.get(0);
		Mat hist = new Mat();
		ArrayList<Mat> valoresH = new ArrayList<Mat>();
		valoresH.add(capaH);
		Imgproc.calcHist(valoresH, new MatOfInt(0), new Mat(), hist,
				new MatOfInt(256), new MatOfFloat(0, 256), false);
		hist = normalizarH(hist);
		return hist;
	}

}