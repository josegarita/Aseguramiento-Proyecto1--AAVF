package BackEnd;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class contieneTest {

	@Test
	public void contienetest() {

		int holgura = 10;
		
		//Frames donde si hay cortes
		ArrayList<Integer> arrayCortes = new ArrayList<Integer>();
		arrayCortes.add(12);
		arrayCortes.add(17);
		arrayCortes.add(22);
		arrayCortes.add(27);
		
		// Frames donde no hay corte
		ArrayList<Integer> arrayNoCortes = new ArrayList<Integer>();
		arrayNoCortes.add(32);
		arrayNoCortes.add(37);
		arrayNoCortes.add(42);
		arrayNoCortes.add(47);
		
		// Groundtruth donde si hay cortes
		ArrayList<Integer> arrayGT = new ArrayList<Integer>();
		arrayGT.add(14);
		arrayGT.add(19);
		arrayGT.add(24);
		arrayGT.add(29);
		
		// Groundtruth donde no hay cortes
		ArrayList<Integer> arrayNGT = new ArrayList<Integer>();
		arrayNGT.add(34);
		arrayNGT.add(39);
		arrayNGT.add(44);
		arrayNGT.add(49);
		
		GroundTruth junit = new GroundTruth(holgura, arrayCortes, arrayNoCortes,
				arrayGT, arrayNGT);
		boolean result = junit.contiene(10, holgura, "Corte");
		assertEquals(false, result);
	}
}
