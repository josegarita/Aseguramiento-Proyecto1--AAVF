package com.videoprocessing;

import org.opencv.core.Mat;

/**
 * @author Jos� Mario Naranjo Leiva
 * @version 0.1
 */
public interface ISegmentadorJugadores {
	/**
	 * M�todo que obtiene la m�scara con el campo de juego detectado en un imagen.
	 * La imagen debe contener la zona de juego bien delimitada, la cancha debe ser verde 
	 * y preferiblemente sin ning�n tipo de estorbo o espuria. 
	 * @param pFrame. Una imagen del campo de juego y los jugadores
	 * @return Una imagen binaria con la cancha detectada
	 */
	public Mat obtenerMascaraCampo(Mat pFrame);
	/**
	 * M�todo que obtiene la m�scara con los jugadores ubicados en la cancha.
	 * Los jugadores son bien visibles, no hay espurias o obst�culos en la imagen
	 * @param pFrame Un imagen del campo de juego y los jugadores
	 * @return Una imagen binaria con la m�scara de jugadores
	 */
	public Mat obtenerMascaraJugadores(Mat pFrame);
	/**
	 * Este m�todo realiza una operaci�n AND l�gico entre las m�scaras de jugadores y 
	 * la cancha para obtener los blobs de los jugadores.
	 * @param pCancha M�cara binaria con la cancha detectada
	 * @param pJugadores M�scara binaria con los blos de los jugadores 
	 * @return Una imagen binaria con los blobs de los jugadores detectados
	 */
	public Mat obtenerBlobs(Mat pCancha, Mat pJugadores);
}
