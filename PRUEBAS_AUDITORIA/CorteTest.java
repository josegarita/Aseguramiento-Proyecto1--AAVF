package BackEnd;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CorteTest {

	@Test
	public void test() {
		ArrayList<Double> arrayDiferencias = new ArrayList<>();
		arrayDiferencias.add(1d);
		arrayDiferencias.add(2d);
		arrayDiferencias.add(3d);
		DeteccionEscenas de = new DeteccionEscenas();
		boolean result = de.esCorte(3, 3, 55);
		assertEquals(true, result);
	}

}
