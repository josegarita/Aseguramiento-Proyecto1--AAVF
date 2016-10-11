package com.utils;

import org.opencv.core.Mat;

/**
 * @author José Mario Naranjo Leiva
 *
 */
public class ImageUtils {
	
	/**
	 * Este método cuenta la cantidad de pixeles blancos en una imagen binaria.
	 * Restricciones: La entrada es una imagen binaria 
	 * @param pImage Imagen únicamente binaria
	 * @return La cantidad de pixeles blancos de la imagen
	 */
	public static int contarPixel_Blancos(final Mat pImage)
    {
        int blancos = 0;
        double negros = 255.0;
        for (int i = 0; i < pImage.rows(); i++) {
            for (int j = 0; j < pImage.cols(); j++) {
                if (pImage.get(i, j)[0] == negros) {
                    blancos++;
                }
            }
        }
        
        return blancos;
    }
	
}
