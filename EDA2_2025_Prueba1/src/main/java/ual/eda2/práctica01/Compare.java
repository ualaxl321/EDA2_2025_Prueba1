package main.java.ual.eda2.práctica01;

import java.util.Comparator;

public class Compare implements Comparator<Punto> {

	@Override
	public int compare(Punto o1, Punto o2) {
		int comparar = Double.compare(o1.gety(), o2.gety());
		return comparar == 0 ? Double.compare(o1.getx(), o2.getx()) : comparar;
	}
	
}
