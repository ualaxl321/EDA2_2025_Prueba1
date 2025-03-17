package main.java.ual.eda2.DyV;

public class MergeSortDyV {

	// Fusiona dos submatrices de arr[].
	// La primera submatriz es arr[l..m]
	// La segunda submatriz es arr[m+1..r]
    public static void merge(int arr[], int l, int m, int r) {
    	// Encuentra los tamaños de dos submatrices que se fusionarán
        int n1 = m - l + 1;
        int n2 = r - m;
  
        /* Crear matrices temporales */
        int L[] = new int[n1];
        int R[] = new int[n2];
  
        /*Copiar datos a matrices temporales*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
  
        /* Fusionar las matrices temporales */
  
        // Índices iniciales de los subconjuntos primero y segundo
        int i = 0, j = 0;
  
        // Índice inicial de la matriz de subconjuntos fusionados
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
  
        /* Copiar los elementos restantes de L[] si los hay */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
  
        /* Copiar los elementos restantes de R[] si los hay */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
  
    // Función principal que ordena arr[l..r] usando
    // merge()
    public static void mergeSortRecursivo(int arr[], int l, int r) {
        if (l < r) {
        	// Encuentra el punto medio
        	//int m = l + (r - l) / 2;
        	int m = (l + r) / 2;
  
        	// Ordenar la primera y la segunda mitad
            mergeSortRecursivo(arr, l, m);
            mergeSortRecursivo(arr, m + 1, r);
  
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
    
    /* Función de combinación iterativa para ordenar arr[0...n-1] */
    public static void mergeSortIterativo(int arr[], int n) {
          
    	// Para el tamaño actual de las submatrices que se van a fusionar
    	// tamaño_inicial varía de
    	// 1 a n/2
        int tamaño_inicial; 
                      
	    // Para seleccionar el índice inicial de
	    // la submatriz izquierda que se fusionará
        int indice_submatriz_izquierda;
                          
          
		// Fusionar submatrices de abajo a arriba
		// Primero fusionar submatrices
		// de tamaño 1 para crear
		// submatrices ordenadas de tamaño 2, luego fusionar
		// submatrices de tamaño 2 para crear
		// submatrices ordenadas de tamaño 4, y
		// así sucesivamente.
        for (tamaño_inicial = 1; tamaño_inicial <= n-1; tamaño_inicial = 2*tamaño_inicial) {
              
        	// Elija el punto de inicio de diferentes
        	// submatrices del tamaño actual
            for (indice_submatriz_izquierda = 0; indice_submatriz_izquierda < n-1; indice_submatriz_izquierda += 2*tamaño_inicial) {
            	// Encuentra el punto final del subarreglo izquierdo
            	// . mitad+1 es el punto inicial
            	// del subarreglo derecho
                int mitad = Math.min(indice_submatriz_izquierda + tamaño_inicial - 1, n-1);
          
                int indice_submatriz_derecha = Math.min(indice_submatriz_izquierda + 2*tamaño_inicial - 1, n-1);
          
	            // Fusionar submatrices arr[indice_submatriz_izquierda...mitad]
                // & arr[mitad+1...indice_submatriz_derecha]
                merge(arr, indice_submatriz_izquierda, mitad, indice_submatriz_derecha);
            }
        }
    }
  
    /* Una función de utilidad para imprimir una matriz de tamaño n */
    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
	
	public static void main(String[] args) {
		int arr1[] = { 12, 11, 13, 5, 6, 7, 10, 4, 9 };
		int arr2[] = { 12, 11, 13, 5, 6, 7, 10, 4, 9 };
		  
        System.out.println("Array dado");
        printArray(arr1);
  
        mergeSortRecursivo(arr1, 0, arr1.length - 1);
        System.out.println("\nArray ordenado");
        printArray(arr1);

        System.out.println("\n\nArray dado");
        printArray(arr2);
        mergeSortIterativo(arr2, arr2.length);
  
        System.out.println("\nSorted array");
        printArray(arr2);
	}

}
