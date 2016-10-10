package com.soccergame_main;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
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
	public static void main (String args[])
	{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        Mat frame = new Mat();
        VideoCapture camera = new VideoCapture();
        camera.open("C:\\Users\\jonaranjo\\Downloads\\game.avi");
       
        int i = 0;
        
        final Size frameSize=new Size((int)camera.get(Videoio.CAP_PROP_FRAME_WIDTH),(int)camera.get(Videoio.CAP_PROP_FRAME_HEIGHT));

	
        VideoWriter v = new VideoWriter("C:\\Users\\jonaranjo\\Downloads\\hola.avi",1,camera.get(Videoio.CAP_PROP_FPS),frameSize,false);
		
        
        ISegmentadorJugadores s = new SegmentadorJugadores();
        
	while(true)
	{
            if (camera.read(frame))
            {
                Mat cancha = s.obtenerMascaraCampo(frame);
                Mat jugadores = s.obtenerMascaraJugadores(frame);

                Mat hier = s.obtenerBlobs(cancha, jugadores);

                v.write(hier);
                

                //Imgcodecs.imwrite("C:\\Users\\jonaranjo\\Pictures\\frames\\"+ i +"c.jpg", hier);
                //Imgcodecs.imwrite("C:\\Users\\jonaranjo\\Pictures\\frames\\"+ i +".jpg", frame);
               
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
