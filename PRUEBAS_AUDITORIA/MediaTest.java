package BackEnd;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MediaTest {

	@Test
	public void Mediatest() {
		ArrayList<Double> arrayDiferencias = new ArrayList<>();
		arrayDiferencias.add(1d);
		arrayDiferencias.add(2d);
		arrayDiferencias.add(3d);
		DeteccionEscenas de = new DeteccionEscenas();
		double result = de.calcular_Media(arrayDiferencias);
		assertEquals(2d, result, 0.01);
	}

}
