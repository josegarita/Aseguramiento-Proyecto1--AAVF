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
	 protected static double distancia(Punto p, Punto centroid) {
	        return Math.sqrt(Math.pow((centroid.getY() - p.getY()), 2) + Math.pow((centroid.getX() - p.getX()), 2));
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
