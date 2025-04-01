package main.java.ual.eda2.práctica02.pruebas;

public class EjemploRed {

//	public static void main(String[] args) {
//		Network<String> red = new Network<String>();
//		
//		red.setDirected(false);
//		red.addVertex("A");
//		red.addVertex("B");
//		red.addVertex("C");
//		red.addEdge("A", "B", 5);
//		red.addEdge("A", "C", 3);
//		
//		System.out.println(red.getWeight("A", "B"));
//		System.out.println(red.isAdjacent("A", "C"));
//		Double p1 = red.getWeight("A", "C");
//		System.out.println(p1);
//		Double p2 = red.getWeight("B", "C");
//		System.out.println(p2);
//		System.out.println(red.getNeighbors("A"));
//		
//		System.out.println(red);
//	}
	
	public static void main(String[] args) {
		
		Network2<Vertice> red = new Network2<>();
		red.setDirected(false);
		Vertice v1 = new Vertice("A");
		Vertice v2 = new Vertice("B");
	    Vertice v3 = new Vertice("C");
	    red.addVertex(v1);
	    red.addVertex(v2);
	    red.addVertex(v3);
	    red.addEdge(v1, v2, 5);
	    red.addEdge(v1, v3, 3);
	    System.out.println(red);
	}
	
	static class Vertice {
		String valor;
		double x;
		double y;
		
		public Vertice(String valor) {
			this(valor, 0, 0);
		}
		
		public Vertice(String valor, double x, double y) {
			this.valor = valor;
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int hashCode() {
			return this.valor.hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			Vertice other = (Vertice) obj;
			return this.valor.equals(other.valor);
		}
		
		public String toString() {
			return this.valor;
		}
	}

}
