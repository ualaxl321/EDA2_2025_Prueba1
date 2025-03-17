package main.java.ual.eda2.práctica01;

import java.util.Comparator;

public class CompareX implements Comparator<Punto>{

		public int compare(Punto p1, Punto p2) {
			return Double.compare(p1.getx(), p2.getx());
		}

}
