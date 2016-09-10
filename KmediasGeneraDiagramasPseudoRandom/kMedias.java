package kMedias;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class kMedias {

	private static kMedias instancia= null;
	private int cantClusters = 3;    
    //Number of Points
    private int cantPuntos = 15;
    //Min and Max X and Y
    private static final int MIN_COORDENADA = 0;
    private static final int MAX_COORDENADA = 10;
    
    private List puntos;
    private List<Cluster> clusters;
    
    
    
    public static kMedias getInstance() {
        if(instancia == null) {
           instancia = new kMedias();
        }
        return instancia;
     }
    
    
    private kMedias() {
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
    
    public void cargarDatos(){
    	List centros = Punto.obtenerCentros();
    	Punto c1 = (Punto)centros.get(0);
    	Punto c2 = (Punto)centros.get(1);
    	Punto c3 = (Punto)centros.get(2);
    	puntos=Punto.obtenerPuntos(c1.getX(),c1.getY(), c2.getX(),c2.getY(), c3.getX(),c3.getY());
    	
    	for (int i = 0; i < cantClusters; i++) {
    		Cluster cluster = new Cluster(i);
    		clusters.add(cluster);
    	}
    	clusters.get(0).setCentro(c1);
    	clusters.get(1).setCentro(c2);
    	clusters.get(2).setCentro(c3);
    }
    
    private void imprimirClusters() {
    	for (int i = 0; i < cantClusters; i++) {
    		Object c = clusters.get(i);
    		System.out.println(((Cluster) c).toString());
    	}
    }
    
    public void calcular() {
    	escribirArchivos((ArrayList<Punto>) puntos);
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
    
    protected void escribirArchivos(ArrayList<Punto> puntos){
    	try { 
    		File fileX = new File("C:\\Users\\JoseR\\OneDrive\\ACS\\TAREAS\\Tarea # 2","puntosX.txt");
    		File fileY = new File("C:\\Users\\JoseR\\OneDrive\\ACS\\TAREAS\\Tarea # 2","puntosY.txt");
    		
    		String xPuntos = "", yPuntos = "";
        	for(Punto pt : puntos){
        		xPuntos+=pt.getX()+",";
        	}
        	for(Punto pt : puntos){
        		yPuntos+=pt.getY()+",";
        	}
        	xPuntos = xPuntos.substring(0, xPuntos.length()-1);
        	yPuntos = yPuntos.substring(0, yPuntos.length()-1);
    	 
			fileX.createNewFile();
			fileY.createNewFile();
			FileWriter writerX = new FileWriter(fileX);
			FileWriter writerY = new FileWriter(fileY);
			writerX.write(xPuntos);
			writerY.write(yPuntos);
			
			writerX.flush();
		    writerX.close();
		    
		    writerY.flush();
		    writerY.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//System.out.println(xPuntos);
    	
    	
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
