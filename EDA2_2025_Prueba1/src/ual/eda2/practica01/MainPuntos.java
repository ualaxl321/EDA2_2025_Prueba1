package ual.eda2.practica01;

public class MainPuntos {

	public static void main(String[] args) {
		Puntazo[] puntitos = new Puntazo[] {
			    new Puntazo(12, 450), new Puntazo(87, 223), new Puntazo(34, 567), 
			    new Puntazo(91, 189), new Puntazo(75, 342), new Puntazo(29, 654), 
			    new Puntazo(63, 278), new Puntazo(41, 499), new Puntazo(58, 371), 
			    new Puntazo(99, 105)
		};

		Parazo mejorFuerzaBruta = Algorritmicos.fuerzaBrutaV1(puntitos);
		System.out.println("Fuerza Bruta Vesión 1" + mejorFuerzaBruta);
	}

}
