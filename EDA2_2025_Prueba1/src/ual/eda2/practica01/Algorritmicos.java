package ual.eda2.practica01;

public class Algorritmicos {
	
	public static Parazo fuerzaBrutaV1(Puntazo[] puntos) {
		if(puntos == null) throw new IllegalArgumentException("Array nulo");
		Parazo el_mejor = new Parazo();
		
		for (int i = 0; i < puntos.length; i++) {
			for (int j = 0; j < puntos.length; j++) {
				if(i == j) continue;
				double distancia = Utilidades.distancia_euclidea(puntos[i], puntos[j]);
				if(distancia < el_mejor.getDistancia()) el_mejor = new Parazo(puntos[i], puntos[j], distancia);
			}
			
		}
		
		return el_mejor;
	}
	
	
	public static Parazo fuerzaBrutaV2(Puntazo[] puntos) {
		if(puntos == null) throw new IllegalArgumentException("Array nulo");
		Parazo el_mejor = new Parazo();
		for (int i = 0; i < puntos.length - 1; i++) {
			for (int j = i + 1; j < puntos.length; j++) {
				double distancia = Utilidades.distancia_euclidea(puntos[i], puntos[j]);
				if(distancia < el_mejor.getDistancia()) el_mejor = new Parazo(puntos[i], puntos[j], distancia);
			}
			
		}
		
		return el_mejor;
	}
}
