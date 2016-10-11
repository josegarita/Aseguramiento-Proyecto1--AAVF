package com.videoprocessing;

import org.opencv.core.Mat;

/**
 * @author José Mario Naranjo Leiva
 * @version 0.1
 */
public interface ISegmentadorJugadores {
	/**
	 * 
	 * @param pFrame
	 * @return
	 */
	public Mat obtenerMascaraCampo(Mat pFrame);
	/**
	 * 
	 * @param pFrame
	 * @return
	 */
	public Mat obtenerMascaraJugadores(Mat pFrame);
	/**
	 * 
	 * @param pCancha
	 * @param pJugadores
	 * @return
	 */
	public Mat obtenerBlobs(Mat pCancha, Mat pJugadores);
}
