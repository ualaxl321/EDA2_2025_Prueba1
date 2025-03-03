package ual.eda2.DyV;

public class MinMaxRecursive {

	// Class Pair is used to return two values from getMinMax()
    static class Pair {
        int min;
        int max;
    }
    
    static Pair getMinMaxRecursive(int arr[], int low, int high) {
        Pair minmax = new Pair();
        Pair mml = new Pair();
        Pair mmr = new Pair();
        int mid;
 
        // If there is only one element
        if (low == high) {
            minmax.max = arr[low];
            minmax.min = arr[low];
            return minmax;
        }
 
        // If there are two elements
        if (high == low + 1) {
            if (arr[low] > arr[high]) {
                minmax.max = arr[low];
                minmax.min = arr[high];
            }
            else {
                minmax.max = arr[high];
                minmax.min = arr[low];
            }
            return minmax;
        }
 
        // If there are more than 2 elements
        mid = (low + high) / 2;
        mml = getMinMaxRecursive(arr, low, mid);
        mmr = getMinMaxRecursive(arr, mid + 1, high);
 
        // compare minimums of two parts
        if (mml.min < mmr.min) {
            minmax.min = mml.min;
        }
        else {
            minmax.min = mmr.min;
        }
 
        // compare maximums of two parts
        if (mml.max > mmr.max) {
            minmax.max = mml.max;
        }
        else {
            minmax.max = mmr.max;
        }
 
        return minmax;
    }

	public static void main(String[] args) {
		int arr[] = {100, 11, 445, 1, 330, 300};
		int arrSize = 6;
		Pair minmax = getMinMaxRecursive(arr, 0, arrSize - 1);
		System.out.printf("\nMinimum element is %d", minmax.min);
		System.out.printf("\nMaximum element is %d", minmax.max);
	}

}
