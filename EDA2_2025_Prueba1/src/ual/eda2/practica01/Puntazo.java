package ual.eda2.practica01;

public class Puntazo {
	
	private double x;
	private double y;
	
	public Puntazo(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		Puntazo otro = (Puntazo) obj;
		return this.x == otro.x && this.y == otro.y;
	}

	@Override
	public String toString() {
		return "Puntazo [x=" + this.x + ", y=" + this.y + "]";
	}
	
}
