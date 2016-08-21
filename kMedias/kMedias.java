package kMedias;

import java.util.ArrayList;
import java.util.List;

public class kMedias {

	
	private int cantClusters = 3;    
    //Number of Points
    private int cantPuntos = 15;
    //Min and Max X and Y
    private static final int MIN_COORDENADA = 0;
    private static final int MAX_COORDENADA = 10;
    
    private List puntos;
    private List<Cluster> clusters;
    
    
    public static void main(String[] args) {
    	
    	kMedias kmeans = new kMedias();
    	kmeans.inicializar();
    	kmeans.calcular();
    }
    
    
    public kMedias() {
    	this.puntos = new ArrayList();
    	this.clusters = new ArrayList();    	
    }
    
    
    public void inicializar() {
    	//Create Points
    	puntos = Punto.crearRandomPuntos(MIN_COORDENADA, MAX_COORDENADA, cantPuntos);
    	
    	//Create Clusters
    	//Set Random Centroids
    	for (int i = 0; i < cantClusters; i++) {
    		Cluster cluster = new Cluster(i);
    		Punto centroid = Punto.crearRandomPunto(MIN_COORDENADA,MAX_COORDENADA);
    		cluster.setCentro(centroid);
    		clusters.add(cluster);
    	}
    	
    	//Print Initial state
    	//--plotClusters();
    }
    
    private void imprimirClusters() {
    	for (int i = 0; i < cantClusters; i++) {
    		Object c = clusters.get(i);
    		System.out.println(((Cluster) c).toString());
    	}
    }
    
    public void calcular() {
        boolean finalizado = false;
        //int iterador = 0;
        
        // Add in new data, one at a time, recalculating centroids with each new one. 
        while(!finalizado) {
        	//Clear cluster state
        	limpiarClusters();
        	
        	List<Punto> ultimosCentroids = getCentroids();
        	
        	//Assign points to the closer cluster
        	asignarCluster();
            
            //Calculate new centroids.
        	calcularCentroids();
        	
        	//iterador++;
        	
        	List currentCentroids = getCentroids();
        	
        	//Calculates total distance between new and old Centroids
        	double distance = 0;
        	for(int i = 0; i < ultimosCentroids.size(); i++) {
        		distance += Punto.distancia(((Punto)ultimosCentroids.get(i)),((Punto)currentCentroids.get(i)));
        	}
        	//System.out.println("#################");
        	//System.out.println("Iteration: " + iteration);
        	//System.out.println("Centroid distances: " + distance);
        	imprimirClusters();
        	        	
        	if(distance == 0) {
        		finalizado = true;
        	}
        }
    }
    
    private void limpiarClusters() {
    	for(Object cluster : clusters) {
    		((Cluster)cluster).clear();
    	}
    }
    
    
    private List<Punto> getCentroids() {
    	List centroids = new ArrayList(cantClusters);
    	for(Object cluster : clusters) {
    		Punto aux = ((Cluster)cluster).getCentro();
    		Punto punto = new Punto(aux.getX(),aux.getY());
    		centroids.add(punto);
    	}
    	return centroids;
    }
    
    private void asignarCluster() {
        double max = Double.MAX_VALUE;
        double min = max; 
        int cluster = 0;                 
        double distance = 0.0; 
        
        for(Object punto : puntos) {
        	min = max;
        	Punto puntoVar = (Punto) punto;
            for(int i = 0; i < cantClusters; i++) {
            	Cluster c = (Cluster)clusters.get(i);
                distance = Punto.distancia(puntoVar, c.getCentro());
                if(distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            puntoVar.setConjuntoDeNumeros(cluster);
            ((Cluster) clusters.get(cluster)).addPunto(puntoVar);
        }
    }
    
    private void calcularCentroids() {
        for(Object cluster : clusters) {
            double sumX = 0;
            double sumY = 0;
            List list = ((Cluster)cluster).getPuntos();
            int n_points = list.size();
            
            for(Object point : list) {
            	sumX += ((Punto)point).getX();
                sumY += ((Punto)point).getY();
            }
            
            Punto centroid = ((Cluster)cluster).getCentro();
            if(n_points >= 0) {
            	double newX = sumX / n_points;
            	double newY = sumY / n_points;
                centroid.setX(newX);
                centroid.setY(newY);
            }
        }
    }
    
    
}
