package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.soccergame_main.VerificadorTiempo;

public class pruebaTiempo {

	VerificadorTiempo verTiem ;
	
	 protected void setUp(){
		 verTiem = new VerificadorTiempo();
	   }
	
	
	@Test
	public void testTime() throws InterruptedException {
		verTiem.main(null);
		Thread.sleep(5000);
		System.out.println(verTiem.imprimirResultado());
		assertTrue(verTiem.imprimirResultado() == "El proceso terminó antes");
	}

}
