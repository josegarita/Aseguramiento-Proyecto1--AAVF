package com.modelo.videoprocessing;

import org.opencv.core.Mat;

/**
 * Esta interfaz indica los métodos para la implementación del algoritmo de segmentación.
 * El algoritmo se encuentra especificacido en la sección 2 del documento de especificación del proyecto.
 * Ubicación en el repositorio: Aseguramiento-Proyecto1--AAVF\master(ESPECIFICACIONES\Enunciados\Proyecto1.pdf
 * @author José Mario Naranjo Leiva
 * @version 0.1
 */
public interface ISegmentadorJugadores {
	/**
	 * Método que obtiene la máscara con el campo de juego detectado en un imagen.
	 * La imagen debe contener la zona de juego bien delimitada, la cancha debe ser verde 
	 * y preferiblemente sin ningún tipo de estorbo o espuria. 
	 * @param pFrame. Una imagen del campo de juego y los jugadores
	 * @return Una imagen binaria con la cancha detectada
	 */
	public Mat obtenerMascaraCampo(Mat pFrame);
	/**
	 * Método que obtiene la máscara con los jugadores ubicados en la cancha.
	 * Los jugadores son bien visibles, no hay espurias o obstáculos en la imagen
	 * @param pFrame Un imagen del campo de juego y los jugadores
	 * @return Una imagen binaria con la máscara de jugadores
	 */
	public Mat obtenerMascaraJugadores(Mat pFrame);
	/**
	 * Este método realiza una operación AND lógico entre las máscaras de jugadores y 
	 * la cancha para obtener los blobs de los jugadores.
	 * @param pCancha Mácara binaria con la cancha detectada
	 * @param pJugadores Máscara binaria con los blos de los jugadores 
	 * @return Una imagen binaria con los blobs de los jugadores detectados
	 */
	public Mat obtenerBlobs(Mat pCancha, Mat pJugadores);
}
