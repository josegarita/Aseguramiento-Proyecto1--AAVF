package metricas;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import com.utils.ImageUtils;

/**
 * @author Jos� Mario Naranjo Leiva
 * @version 0.1
 */
public class Metricas {
	
	/**
	 * Este m�todo calcula el �ndice de Dice para mostrar la similitud de dos im�genes binarias.
	 * Estas im�genes binarias contienen blobs blancos que corresponden a la segmentaci�n de una imagen.
	 * Restricciones : Ambas entradas son im�genes binarias, del mismo tipo y tama�o.
	 * @param S imagen con las regiones autom�ticamente segmetadas. 
	 * @param G imagen con las regiones manualmente segmentadas. 
	 * @return �ndice de Dice para ambas im�genes binarias
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
