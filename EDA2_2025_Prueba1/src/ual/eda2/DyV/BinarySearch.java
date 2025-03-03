package ual.eda2.DyV;

public class BinarySearch {

	public static int binarySearchIterative(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (key == arr[mid]) {
                return mid;
            }
            if (key < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
	
	public static int binarySearchRecursive(int arr[], int start, int end, int key) {
        int middle = (start + end) / 2;
        
        if (end < start)
        	return -1;
        
        if (key < arr[middle]){
			return binarySearchRecursive(arr, start, middle - 1, key);
		}
		
		if (key > arr[middle]){
			return binarySearchRecursive(arr, middle + 1, end, key);
		}
		
		if (key == arr[middle]){
			return middle;
		}
		
		return -1;
    }
	
	public static void main(String[] args) {
		int arr[] = { 2, 3, 4, 10, 40 };
		int n = arr.length;
		int key = 10;
		int result = binarySearchRecursive(arr, 0, n - 1, key);
		if (result == -1)
			System.out.println("(R) Element not present");
		else
			System.out.println("(R) Element found at index " + result);
		result = binarySearchIterative(arr, key);
		if (result == -1)
			System.out.println("(I) Element not present");
		else
			System.out.println("(I) Element found at index " + result);
	}
}
