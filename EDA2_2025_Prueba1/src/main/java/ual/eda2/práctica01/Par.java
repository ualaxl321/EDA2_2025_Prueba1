package main.java.ual.eda2.práctica01;

public class Par {
	
	private Punto p1;
	private Punto p2;
	private double distancia;
	
	public Par(Punto p1, Punto p2, double distancia) {
		this.p1 = p1;
		this.p2 = p2;
		this.distancia = distancia;
	}
	
	public Par() {
		this.p1 = null;
		this.p2 = null;
		this.distancia = Double.MAX_VALUE;
	}

	public Punto getP1() {
		return p1;
	}

	public Punto getP2() {
		return p2;
	}

	public double getDistancia() {
		return distancia;
	}

	@Override
	public String toString() {
		if(p1 == null || p2 == null) return "No existen los puntos";
		return "\nPunto 1 y su " + p1 + ". \nPunto 2 y su " + p2 + ". \nLa distancia entre el par de puntos es: " + distancia + ".";
	}
}
