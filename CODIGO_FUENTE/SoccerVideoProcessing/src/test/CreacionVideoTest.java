package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class CreacionVideoTest {

	private static String _pathAlVideo = 
			"C:\\ACS\\game.avi";
	
	private static String _pathAlVideoRes = 
			"C:\\ACS\\resultadoVideo.avi";
	
	@Before
	public void setUp() throws Exception {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	 
	}
	@Test
	public void test() {
		
		VideoCapture original = new VideoCapture();
        original.open(_pathAlVideo);
        if(original.isOpened()){
	        VideoCapture resultado = new VideoCapture();
	        resultado.open(_pathAlVideoRes);
	        
	        int a = (int) original.get(Videoio.CAP_PROP_FRAME_COUNT);
	        int b = (int) resultado.get(Videoio.CAP_PROP_FRAME_COUNT);
	        assertEquals(a,b);
        }
        
	}

}
