package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import metricas.Metricas;

public class ObtencionBlobs_GroundTruth {

	private Mat manualFrame;
	private Mat automaticFrame;
	
	@Before
	public void setUp() throws Exception {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    // Cargar las imágenes por probar 
        manualFrame = Imgcodecs.imread("imagenes/manual.jpg");
        automaticFrame = Imgcodecs.imread("imagenes/automatica.jpg");
        // Convertir las imágenes a imágenes binarias
        Imgproc.cvtColor(manualFrame, manualFrame, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(automaticFrame, automaticFrame, Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(manualFrame, manualFrame, 0, 255, Imgproc.THRESH_BINARY);
	}

	@Test
	public void testObtenerBlobs() {
		assertTrue(Metricas.indiceDice(automaticFrame, manualFrame) >= 0.5);
	}

}
