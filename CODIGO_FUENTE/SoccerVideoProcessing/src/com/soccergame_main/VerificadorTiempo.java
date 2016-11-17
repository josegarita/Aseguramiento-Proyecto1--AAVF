package com.soccergame_main;

import java.awt.geom.CubicCurve2D;
import java.util.MissingFormatArgumentException;

/**
 * @author José Rodolfo Garita
 * @version 0.1
 */

public class VerificadorTiempo implements  Runnable {
	private static String mensajeFinal ="nada";
	@SuppressWarnings("deprecation") /*Se sabe que no debe usarse el stop, pero no lo detiene el interrupt*/
	public void run() {
		int tiempo = 0;
		Main main = new Main();
		Thread hiloEjecución = new Thread(main);
		
		hiloEjecución.start();
		while(true){
			try {
				if (tiempo==30) {
					hiloEjecución.stop();
					mensajeFinal = "Se termina el proceso";
					break;
				}
				if(main.isVerificarTermina()){
					mensajeFinal = "El proceso terminó antes";
					break;
				}
				tiempo++;
				System.out.println(tiempo);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
			
			
		}
	}
	
	public static String imprimirResultado(){
		//System.out.println(mensajeFinal);
		return mensajeFinal;
	}
	
	 public static void main(String args[]) throws InterruptedException {
		 Thread thMain = new Thread(new VerificadorTiempo());
		 thMain.start();
		Thread.sleep(5000);
		System.out.println(imprimirResultado());
	
	 }
}
