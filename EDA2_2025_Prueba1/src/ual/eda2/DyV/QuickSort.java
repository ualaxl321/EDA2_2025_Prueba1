package ual.eda2.DyV;

public class QuickSort {

	// Una función de utilidad para intercambiar dos elementos
    public static void swap(int[] matriz, int i, int j) {
        int temp = matriz[i];
        matriz[i] = matriz[j];
        matriz[j] = temp;
    }
 
    /* Esta función toma el último elemento como pivote, coloca
     * el elemento pivote en su posición correcta en la matriz
     * ordenada y coloca todos los elementos más pequeños 
     * (más pequeños que el pivote) a la izquierda del pivote 
     * y todos los elementos más grandes a la derecha
     * del pivote. 
     */
    public static int partition(int[] matriz, int indiceInicial, int indiceFinal) {
 
        // pivote
        int pivot = matriz[indiceFinal];
 
        // Índice del elemento más pequeño e
        // indica la posición correcta
        // del pivote encontrado hasta el momento
        int i = (indiceInicial - 1);
 
        for (int j = indiceInicial; j <= indiceFinal - 1; j++) {
 
        	// Si el elemento actual es más pequeño
        	// que el pivote
            if (matriz[j] < pivot) {
 
            	// Incrementar el índice del elemento más pequeño
                i++;
                swap(matriz, i, j);
            }
        }
        swap(matriz, i + 1, indiceFinal);
        return (i + 1);
    }
 
    /* La función principal que implementa QuickSort
    matriz[] --> Matriz a ordenar,
    indiceInicial --> Índice inicial,
    indiceFinal --> Índice final
    */
    public static void quickSort(int[] matriz, int indiceInicial, int indiceFinal) {
        if (indiceInicial < indiceFinal) {
 
        	// pi está particionado el índice, matriz[p]
        	// ahora está en el lugar correcto
            int pi = partition(matriz, indiceInicial, indiceFinal);
 
			// Ordenar por separado los elementos antes
			// de la partición y después de la partición
            quickSort(matriz, indiceInicial, pi - 1);
            quickSort(matriz, pi + 1, indiceFinal);
        }
    }
 
    // Función para imprimir una matriz
    public static void printmatriz(int[] matriz) {
        for (int i = 0; i < matriz.length; i++)
            System.out.print(matriz[i] + " ");
        	System.out.println();
    }
    
	public static void main(String[] args) {
		int matriz[] = { 12, 11, 13, 5, 6, 7, 10, 4, 9, 15, 1 };
		  
        System.out.println("Matriz dada");
        printmatriz(matriz);
  
        quickSort(matriz, 0, matriz.length - 1);
  
        System.out.println("\nMatriz ordenada");
        printmatriz(matriz);
	}

}
