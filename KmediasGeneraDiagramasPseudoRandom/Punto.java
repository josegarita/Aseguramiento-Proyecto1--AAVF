package kMedias;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Punto {
	 private double x;
	 private double y;
	 private int conjuntoDeNumeros;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getConjuntoDeNumeros() {
		return conjuntoDeNumeros;
	}
	public void setConjuntoDeNumeros(int conjuntoDeNumeros) {
		this.conjuntoDeNumeros = conjuntoDeNumeros;
	}
	public Punto(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	 
	protected static Punto crearRandomPunto(int min, int max) {
    	Random r = new Random();
    	double x = min + (max - min) * r.nextDouble();
    	double y = min + (max - min) * r.nextDouble();
    	return new Punto(x,y);
    	
    }
	
	protected static Punto crearRandomPuntoXY(double px, double py) {
    	Random r = new Random();
    	double x = (px-5) + ((px+5) - (px-5)) * r.nextDouble();
    	double y = (py-5) + ((py+5) - (py-5)) * r.nextDouble();
    	return new Punto(x,y);
    	
    }
	
	 protected static double distancia(Punto p, Punto centroid) {
	        return Math.sqrt(Math.pow((centroid.getY() - p.getY()), 2) + Math.pow((centroid.getX() - p.getX()), 2));
	    }
	 protected static List obtenerPuntos(double c1x,double c1y, double c2x, double c2y, double c3x, double c3y){
		 List puntos = new ArrayList(15);
		 for(int i = 0; i < 5; i++) {
	    		puntos.add(crearRandomPuntoXY(c1x,c1y));
	    	}
		 
		 for(int i = 0; i < 5; i++) {
	    		puntos.add(crearRandomPuntoXY(c2x,c2y));
	    	}
		 for(int i = 0; i < 5; i++) {
	    		puntos.add(crearRandomPuntoXY(c3x,c3y));
	    	}
		 return puntos;
		 
	 }
	 
	 protected static List obtenerCentros(){
		 List puntos = new ArrayList(3);
		 puntos.add(new Punto(15, 15));
		 puntos.add(new Punto(15, -15));
		 puntos.add(new Punto(-15, -15));
		 return puntos;
		 
		 
	 }
	 
	 
	 protected static List crearRandomPuntos(int min, int max, int number) {
	    	List points = new ArrayList(number);
	    	for(int i = 0; i < number; i++) {
	    		points.add(crearRandomPunto(min,max));
	    	}
	    	return points;
	    }
	@Override
	public String toString() {
		return "Punto [x=" + x + ", y=" + y + "]";
	}
	 
	 
}
