package kMedias;

public class Main {

	
public static void main(String[] args) {
    	
    	kMedias kmeans = kMedias.getInstance();
    	//kmeans.inicializar(); datos totalmente al azar
    	kmeans.cargarDatos();//n�meros pseudo random
    	kmeans.calcular();
    }
}
