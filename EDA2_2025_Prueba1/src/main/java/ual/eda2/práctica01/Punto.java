package main.java.ual.eda2.práctica01;

public class Punto {
	
	private double x; //latitud
	private double y; //longitud
	
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getx() {
		return x;
	}

	public double gety() {
		return y;
	}
	
	public boolean equals(Object objeto) {
		if(this == objeto) return true;
		if(objeto == null) return false;
		if(this.getClass() != objeto.getClass()) return false;
		Punto otroPunto = (Punto) objeto;
		return this.x == otroPunto.x && this.y == otroPunto.y;
	}

	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
	
}
