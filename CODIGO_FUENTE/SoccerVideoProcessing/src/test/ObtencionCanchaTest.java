package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import com.videoprocessing.ISegmentadorJugadores;
import com.videoprocessing.SegmentadorJugadores;

public class ObtencionCanchaTest {

	ISegmentadorJugadores _s;
	Mat _frame;
	Mat _res;
	
	@Before
	public void setUp() throws Exception {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		_s = new SegmentadorJugadores();
		_frame = Imgcodecs.imread("imagenes/deteccionTest3.jpg");
		_res = _s.obtenerMascaraCampo(_frame);
	}

	@Test
	public void testObtenerMascaraCampo_DimensionesCorrectas() {
		
		assertTrue(_res.width() > 0 & _res.height() > 0);
			
	}
	
	@Test
	public void testDetectarPixelesBlancos()
	{
		assertTrue(com.utils.ImageUtils.contarPixel_Blancos(_res) > 0);
	}

}
