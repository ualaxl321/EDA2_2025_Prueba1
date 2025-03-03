package ual.eda2.DyV;

public class MergeSortDyV {

	// Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    public static void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
  
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
  
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
  
        // Initial index of merged subarray array
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
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
  
    // Main function that sorts arr[l..r] using
    // merge()
    public static void mergeSortRecursive(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            //int m = l + (r - l) / 2;
        	int m = (l + r) / 2;
  
            // Sort first and second halves
            mergeSortRecursive(arr, l, m);
            mergeSortRecursive(arr, m + 1, r);
  
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
    
    /* Iterative mergesort function to sort arr[0...n-1] */
    public static void mergeSortIterative(int arr[], int n) {
          
        // For current size of subarrays to
        // be merged curr_size varies from 
        // 1 to n/2
        int curr_size; 
                      
        // For picking starting index of 
        // left subarray to be merged
        int left_start;
                          
          
        // Merge subarrays in bottom up 
        // manner. First merge subarrays 
        // of size 1 to create sorted 
        // subarrays of size 2, then merge
        // subarrays of size 2 to create 
        // sorted subarrays of size 4, and
        // so on.
        for (curr_size = 1; curr_size <= n-1; curr_size = 2*curr_size) {
              
            // Pick starting point of different
            // subarrays of current size
            for (left_start = 0; left_start < n-1; left_start += 2*curr_size) {
                // Find ending point of left 
                // subarray. mid+1 is starting 
                // point of right
                int mid = Math.min(left_start + curr_size - 1, n-1);
          
                int right_end = Math.min(left_start + 2*curr_size - 1, n-1);
          
                // Merge Subarrays arr[left_start...mid]
                // & arr[mid+1...right_end]
                merge(arr, left_start, mid, right_end);
            }
        }
    }
  
    /* A utility function to print array of size n */
    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
	
	public static void main(String[] args) {
		int arr1[] = { 12, 11, 13, 5, 6, 7, 10, 4, 9 };
		int arr2[] = { 12, 11, 13, 5, 6, 7, 10, 4, 9 };
		  
        System.out.println("Given Array");
        printArray(arr1);
  
        mergeSortRecursive(arr1, 0, arr1.length - 1);
        System.out.println("\nSorted array");
        printArray(arr1);

        System.out.println("\n\nGiven Array");
        printArray(arr2);
        mergeSortIterative(arr2, arr2.length);
  
        System.out.println("\nSorted array");
        printArray(arr2);
	}

}
