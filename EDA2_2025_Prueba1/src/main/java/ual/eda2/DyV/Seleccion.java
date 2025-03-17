package main.java.ual.eda2.DyV;

public class Seleccion {
			
	private static int reorganiza(int[] T, int ini, int fin) {
		int pivot = T[ini]; // usa el primer elemento como pivote
		int i = ini - 1;
		int j = fin + 1;
		while (true) {
			do { // busca elemento menor o igual que el pivote
				j--;
			} while (T[j] > pivot);
			do { // busca elemento mayor o igual que el pivote
				i++;
			} while (T[i] < pivot);
			if (i < j) {
				int temp = T[i]; // intercambio
				T[i] = T[j];
				T[j] = temp;
			} else {
				return j;
			}
		}
	}
	
	private static int seleccion(int[] T, int ini, int fin, int s) {
		// caso directo
		if (ini == fin) {
			return T[ini]; // elemento en la pos. k-esima
		}
		// reorganiza los elementos y retorna la posicion
		// del ultimo elemento menor que el pivote
		int p = reorganiza(T, ini, fin);
		int k = p - ini + 1; // offset del primer elemento
		// mayor que el pivote divide
		if (s <= k) {
			return seleccion(T, ini, p, s);
		} else {
			return seleccion(T, p+1, fin, s-k);
		}
	}

	public static int seleccionDyV(int[] T, int s) {
		return seleccion(T, 0, T.length-1, s);
	}

    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

	public static void main(String[] args) {
		int [] arr = {55, 88, 22, 66, 44, 11, 33, 77, 99, 66};
		printArray(arr);
		int k = 3;
		System.out.println( "k-th (k=" + k + ") smallest element in array : "
				+ seleccionDyV(arr, k));
	}
}
