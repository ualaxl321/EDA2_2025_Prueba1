package ual.eda2.practica01;

public class Parazo {

	private Puntazo p1;
	private Puntazo p2;
	private double distancia;
	
	public Parazo(Puntazo p1, Puntazo p2, double distancia) {
		this.p1 = p1;
		this.p2 = p2;
		this.distancia = distancia;
	}
	
	public Parazo() {
		this.p1 = null;
		this.p2 = null;
		this.distancia = Double.MAX_VALUE;
	}

	public Puntazo getP1() {
		return p1;
	}

	public Puntazo getP2() {
		return p2;
	}
	
	public double getDistancia() {
		return distancia;
	}

	@Override
	public String toString() {
		if(p1 == null || p2 == null) return "No hay puntazos";
		return "Parazo [p1=" + this.distancia  + ", p2=" + this.p2 + ", distancia=" + this.distancia + "]";
	}
	
}
