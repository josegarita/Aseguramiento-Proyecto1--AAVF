package com.soccergame_main;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

import com.videoprocessing.ISegmentadorJugadores;
import com.videoprocessing.SegmentadorJugadores;

/**
 * @author José Mario Naranjo Leiva
 * @version 0.1
 */
public class Main 
{
	private static String _pathAlVideo = "C:\\Users\\jonaranjo\\Downloads\\game.avi";
	private static String _pathAlVideoRes = "C:\\Users\\jonaranjo\\Downloads\\hola.avi";
	private static String _pathAImagenes = "C:\\Users\\jonaranjo\\Pictures\\frames\\";
	
	public static void main (String args[])
	{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        Mat frame = new Mat();
        VideoCapture camera = new VideoCapture();
        camera.open(_pathAlVideo);

        final Size frameSize=new Size((int)camera.get(Videoio.CAP_PROP_FRAME_WIDTH),(int)camera.get(Videoio.CAP_PROP_FRAME_HEIGHT));

        VideoWriter v = new VideoWriter(_pathAlVideoRes,1,camera.get(Videoio.CAP_PROP_FPS),frameSize,false);
   
        ISegmentadorJugadores s = new SegmentadorJugadores();
        int i = 0;
		while(true)
		{
            if (camera.read(frame))
            {
                Mat cancha = s.obtenerMascaraCampo(frame);
                Mat jugadores = s.obtenerMascaraJugadores(frame);

                Mat hier = s.obtenerBlobs(cancha, jugadores);

                v.write(hier);

                Imgcodecs.imwrite(_pathAImagenes + i +"c.jpg", hier);
                Imgcodecs.imwrite(_pathAImagenes + i +".jpg", frame);
               
                i++;
            }
            else
            {
                    break;      
            }	
        }
		System.out.println("Done");
        v.release();
	}
}
