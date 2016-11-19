/**
 * 
 */
package BackEnd;

import static org.junit.Assert.*;

import org.junit.Test;


public class Analizar_Video_Test {

	@Test
	public void test() {
		//fail("Not yet implemented");
		//Se inicializa con la locacion de un video de prueba
		String dir_Video = "C:\\Users\\Mariano\\workspace2\\Detector-Escenas\\Vid.mp4";
		
		//se inicializa el objeto
		DeteccionEscenas detect = new DeteccionEscenas();
		
		//se ejecuta la prueba
		detect.analizarVideo(dir_Video);
		
		//bandera de que llego al final de la prueba
		System.out.println("Se ejecutó correctamente");
		assertEquals(true, 0);
	}

}
