package BackEnd;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;

public class abrirXmlTest {

	@Test
	public void testAbrirXml() {
		
		// ruta del archivo xml que se va abrir
		String rutaOrigen = "C:\\Users\\Luis\\Desktop\\ejemplo3.xml";;
		
		// Instancia de la clase
		XmlConverter junit = new XmlConverter(rutaOrigen);
		
		// La prueba que se desea realizar
		ArrayList<Integer> result = junit.getCortes();
		
		// Si los arreglos son nulos, entonces no se abrio el archivo
		// El resultado esperado es de tamaño 2 para el arreglo de cortes.
		assertEquals(2, result.size());
	}

}
