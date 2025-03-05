package ual.eda2.practica01;

public class Utilidades {

	//CLASE CON MÉTODOS ESTÁTICOS
	public static double distancia_euclidea (Puntazo p1, Puntazo p2) {
		return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
	}
	
	public static String format(double distancia) {
		return String.format("%.2f", distancia);
	}
}
