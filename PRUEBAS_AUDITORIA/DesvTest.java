package BackEnd;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DesvTest {

	@Test
	public void test() {
		ArrayList<Double> arrayDiferencias = new ArrayList<>();
		arrayDiferencias.add(1d);
		arrayDiferencias.add(2d);
		arrayDiferencias.add(3d);
		DeteccionEscenas de = new DeteccionEscenas();
		double result = de.calcular_DesvEstandar(arrayDiferencias);
		assertEquals(1d, result, 0.5);
	}

}
