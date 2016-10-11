package metricas;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * @author Jos� Mario Naranjo Leiva
 * @version 0.1
 *
 */
public class Test {
	
	public static void main(String[] args)
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    // Cargar las im�genes por probar 
        Mat manualFrame = Imgcodecs.imread("C:\\Users\\jonaranjo\\Pictures\\frames_1\\frame1.jpg");
        Mat automaticFrame = Imgcodecs.imread("C:\\Users\\jonaranjo\\Pictures\\frames_1\\0c.jpg");
        // Convertir las im�genes a im�genes binarias
        Imgproc.cvtColor(manualFrame, manualFrame, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(automaticFrame, automaticFrame, Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(manualFrame, manualFrame, 0, 255, Imgproc.THRESH_BINARY);
        
        System.out.println(Metricas.indiceDice(automaticFrame, manualFrame));
	}
}
