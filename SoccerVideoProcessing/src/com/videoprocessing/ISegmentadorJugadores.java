package com.videoprocessing;

import org.opencv.core.Mat;

/**
 * @author José Mario Naranjo Leiva
 * @version 0.1
 */
public interface ISegmentadorJugadores {
	
	public Mat obtenerMascaraCampo(Mat pFrame);
	public Mat obtenerMascaraJugadores(Mat pFrame);
}
