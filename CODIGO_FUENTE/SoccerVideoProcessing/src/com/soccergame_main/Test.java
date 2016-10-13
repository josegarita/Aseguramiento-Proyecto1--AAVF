package com.soccergame_main;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import com.videoprocessing.*;

public class Test {
	
	public static void main(String args[])
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		ISegmentadorJugadores j = new SegmentadorJugadores();
		
		Mat frame = Imgcodecs.imread("imagenes/deteccionTest3.jpg");
		
		com.utils.ShowImage.showResult(j.obtenerMascaraCampo(frame));
	}

}
