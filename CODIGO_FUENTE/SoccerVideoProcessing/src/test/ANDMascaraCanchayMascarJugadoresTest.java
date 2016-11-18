package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import com.modelo.videoprocessing.ISegmentadorJugadores;
import com.modelo.videoprocessing.SegmentadorJugadores;

public class ANDMascaraCanchayMascarJugadoresTest 
{
	
	private Mat mascaraCancha;
	private Mat mascaraJugadores;
	private Mat res;
	
	@Before
	public void setUp() throws Exception
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		ISegmentadorJugadores j = new SegmentadorJugadores();
		
		mascaraCancha = Imgcodecs.imread("imagenes/mascaraCancha.jpg");
		mascaraJugadores = Imgcodecs.imread("imagenes/mascaraJugadores.jpg");
		
		res = j.obtenerBlobs(mascaraCancha, mascaraJugadores);
	}

	@After
	public void tearDown() throws Exception
	{
		mascaraCancha.release();
		mascaraJugadores.release();
		res.release();
	}

	
	@Test
	public void testMismasDimensiones()
	{
		assertTrue(res.size().equals(mascaraCancha.size()));
	}
	
	@Test
	public void testImageNoVacia()
	{
		assertTrue(res.width() != 0 && res.height() !=0);
	}

}
