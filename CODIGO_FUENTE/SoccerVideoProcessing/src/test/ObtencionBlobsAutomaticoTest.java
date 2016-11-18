package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.modelo.videoprocessing.ISegmentadorJugadores;
import com.modelo.videoprocessing.SegmentadorJugadores;

import metricas.Metricas;

public class ObtencionBlobsAutomaticoTest
{
	private Mat in_image;
	private Mat gt_image;
	private Mat blob_image;

	@Before
	public void setUp() throws Exception
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		ISegmentadorJugadores j = new SegmentadorJugadores();
		
		in_image = Imgcodecs.imread("imagenes/deteccionBLOBS_auto_test.jpg");
		gt_image = Imgcodecs.imread("imagenes/deteccionBLOBS_GT_test.png");
		
		Mat cancha = j.obtenerMascaraCampo(in_image);
		Mat jugadores = j.obtenerMascaraJugadores(in_image);
		
		blob_image = j.obtenerBlobs(cancha, jugadores);
		
		Imgproc.cvtColor(gt_image, gt_image, Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(gt_image, gt_image, 0, 255, Imgproc.THRESH_BINARY);
        
	}
	@Test
	public void testIndice()
	{
		assertTrue(Metricas.indiceDice(blob_image, gt_image) >= 0.5);
	}
	
	@Test
	public void testDetectarPixelesBlancos()
	{
		assertTrue(com.utils.ImageUtils.contarPixel_Blancos(blob_image) > 0);
	}
	
	

}
