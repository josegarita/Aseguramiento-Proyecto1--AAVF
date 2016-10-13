package kMedias;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
	private List puntos;
	private Punto centro;
	private int id;
	public List getPuntos() {
		return puntos;
	}
	public void addPunto(Punto punto) {
		this.puntos.add(punto);
	}
	public Punto getCentro() {
		return centro;
	}
	public void setCentro(Punto centro) {
		this.centro = centro;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cluster(int id) {
		puntos = new ArrayList();
		this.id = id;
	}
	@Override
	public String toString() {
		String retornar = "Cluster [centro=" + centro + ", id=" + id  ;
				
			retornar += ("[Points: \n");
			for(Object p : puntos) {
				retornar+= ((Punto)p)+"\n";
			}
			retornar += "]]";
			return retornar;
	}
				
	public void clear() {
		puntos.clear();
	}
	
}
