package com.soccergame_main;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

import com.modelo.videoprocessing.ISegmentadorJugadores;
import com.modelo.videoprocessing.SegmentadorJugadores;

/**
 * @author José Mario Naranjo Leiva
 * @version 0.2
 * Cambios por Jose Garita
 */
public class Main  implements Runnable{
	
	private String _pathAlVideo;
	
	/*verifica si ya terminó
	 * 
	 * */
	private static boolean _verificarTermina = false;
	
	/**
	 *  video de entrada.
	 */
	//private static String _pathAlVideo = 
	//		"C:\\Users\\Manuel\\Desktop\\Sa2.avi";
	/**
	 * video resultado.
	 */
	private static String _pathAlVideoRes = 
			"C:\\Users\\Manuel\\Desktop\\work\\.metadata\\.plugins"
					+ "\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SoccerVideoProcessing\\"
					+ "uploadFiles\\resultadoVideo.avi";
	/**
	 *  cada frame donde se almacena.
	 */
	private static String _pathAImagenes = 
			"C:\\Users\\Manuel\\Desktop\\frames\\";
	
	/**
	 * @param args
	 */
	public Main (String PathIn)
	{
		_pathAlVideo = PathIn;
	}
	
	public void run() 
	{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        Mat frame = new Mat();
        VideoCapture camera = new VideoCapture();
        camera.open(_pathAlVideo);

        System.out.println(camera.isOpened());
        
        final Size frameSize = new Size((int) camera.get(
        		Videoio.CAP_PROP_FRAME_WIDTH),
        		(int) camera.get(Videoio.CAP_PROP_FRAME_HEIGHT));

        VideoWriter v = new VideoWriter(_pathAlVideoRes, 1,
        		camera.get(Videoio.CAP_PROP_FPS), frameSize, false);
   
        ISegmentadorJugadores s = new SegmentadorJugadores();
        int i = 0;
		while (true) {
            if (camera.read(frame)) {
                Mat cancha = s.obtenerMascaraCampo(frame);
                Mat jugadores = s.obtenerMascaraJugadores(frame);

                Mat hier = s.obtenerBlobs(cancha, jugadores);

                v.write(hier);

                //Imgcodecs.imwrite(_pathAImagenes + i + "c.jpg", hier);
                //Imgcodecs.imwrite(_pathAImagenes + i + ".jpg", frame);
               
                i++; 
            }
            else {
            	break;      
            }	
        }
		setVerificarTermina(true);
		System.out.println("Done");
        v.release();
	}
	
	public boolean isVerificarTermina() {
		return _verificarTermina;
	}
	public void setVerificarTermina(boolean verificarTermina) {
		this._verificarTermina = verificarTermina;
	}
}
