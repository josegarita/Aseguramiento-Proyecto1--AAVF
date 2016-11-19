package metricas;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import com.utils.ImageUtils;

/**
 * @author José Mario Naranjo Leiva
 * @version 0.1
 */
public class Metricas {
	
	/**
	 * Este método calcula el índice de Dice para mostrar la similitud de dos imágenes binarias.
	 * Estas imágenes binarias contienen blobs blancos que corresponden a la segmentación de una imagen.
	 * Restricciones : Ambas entradas son imágenes binarias, del mismo tipo y tamaño.
	 * @param S imagen con las regiones automáticamente segmetadas. 
	 * @param G imagen con las regiones manualmente segmentadas. 
	 * @return Índice de Dice para ambas imágenes binarias
	 */
	public static double indiceDice(Mat S, Mat G)
	{
		Mat interseccion = new Mat();
		interseccion.create(S.size(), S.type());
		
		Core.bitwise_and(S, G, interseccion);
		
		int G_pixel_cant = ImageUtils.contarPixel_Blancos(G);
		int S_pixel_cant = ImageUtils.contarPixel_Blancos(S);
		int Inter_pixel_cant = ImageUtils.contarPixel_Blancos(interseccion);

		return (double)(2 * Inter_pixel_cant) / (G_pixel_cant + S_pixel_cant);
	}
	
}
