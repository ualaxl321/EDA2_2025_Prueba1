package main.java.ual.eda2.práctica02.pruebas;
import java.util.Iterator;
import java.util.HashMap;
import java.util.HashSet;


public class Network2<Vertex> implements Iterable<Vertex> {

	private boolean directed; 	// directed = false (unDirected), directed = true (DiGraph)
	
	protected HashMap<Vertex, HashMap<Vertex, Double>> adjacencyMap; 
	
	public Network2(){
		this.directed = true;
		this.adjacencyMap = new HashMap<Vertex, HashMap<Vertex, Double>>();
	}
	
  	public void setDirected(boolean uD_Or_D) {
  		this.directed = uD_Or_D;
  	}

  	public boolean getDirected() {
  		return this.directed;
  	}

  	public boolean isEmpty() {
    	return this.adjacencyMap.isEmpty();
  	} 

  	public void clear() {
		this.adjacencyMap.clear();
	}

  	public int numberOfVertices() {
    	return this.adjacencyMap.size();
  	} 

  	public int numberOfEdges() {
  		int count = 0;
  		for (HashMap<Vertex, Double> itMap : this.adjacencyMap.values())
  			count += itMap.size();
  		return count;
  	} 

  	public boolean containsVertex(Vertex vertex) {
    	return this.adjacencyMap.containsKey(vertex);
  	} 
  	
  	public boolean containsEdge(Vertex v1, Vertex v2) {
  		HashMap<Vertex, Double> neighbors = this.adjacencyMap.get(v1);
  		if (neighbors == null) return false;
    	return neighbors.containsKey(v2);
  	} 

  	public double getWeight (Vertex v1, Vertex v2) {
  		HashMap<Vertex, Double> neighbors = this.adjacencyMap.get(v1);
  		if (neighbors == null) return -1;
  		Double weight = neighbors.get(v2);
  		return weight == null ? -1 : weight;
   	} 

  	public double setWeight (Vertex v1, Vertex v2, double w) {
  		HashMap<Vertex, Double> neighbors = this.adjacencyMap.get(v1);
  		if (neighbors == null) return -1;
  		Double oldWeight = neighbors.get(v2);
  		return oldWeight == null ? -1 : neighbors.put(v2, w);
	}

  	public boolean isAdjacent (Vertex v1, Vertex v2) {
  		HashMap<Vertex, Double> neighbors = this.adjacencyMap.get(v1);
  		return neighbors == null ? false : neighbors.containsKey(v2);
	}

  	public boolean addVertex(Vertex vertex) {
        if (this.adjacencyMap.containsKey(vertex)) return false;
        this.adjacencyMap.put(vertex, new HashMap<Vertex, Double>());
        return true;
  	} 

  	public boolean addEdge(Vertex v1, Vertex v2, double w) {
  		if (!containsVertex(v1) || !containsVertex(v2)) return false;
  		this.adjacencyMap.get(v1).put(v2, w);
       	if (!this.directed) {
        	this.adjacencyMap.get(v2).put(v1, w);
       	}
    	return true;
  	} 

  	public boolean removeVertex(Vertex vertex) {
        if (!containsVertex(vertex)) return false;
        for (HashMap<Vertex, Double> itMap: this.adjacencyMap.values()) {
        	itMap.remove(vertex);
        } 
        this.adjacencyMap.remove(vertex);
        return true;
   	} 

  	public boolean removeEdge (Vertex v1, Vertex v2) {
    	if (!containsEdge(v1,v2)) return false;
    	this.adjacencyMap.get(v1).remove(v2);
    	if (!this.directed) {
        	this.adjacencyMap.get(v2).remove(v1);    		
    	}
    	return true;
  	} 
  	
  	public HashSet<Vertex> vertexSet() {
    	return new HashSet<Vertex>(this.adjacencyMap.keySet());
  	}

  	public HashSet<Vertex> getNeighbors(Vertex v) {
  		HashMap<Vertex, Double> neighbors = this.adjacencyMap.get(v);
  		return neighbors == null ? null : new HashSet<>(neighbors.keySet());
  	}

  	@Override
  	public String toString() {
    	return this.adjacencyMap.toString();
  	} 

	@Override
	public Iterator<Vertex> iterator() {
        return this.adjacencyMap.keySet().iterator();
  	} 

 }