package main.java.ual.eda2.DyV;

import java.util.Arrays;

public class MatrixMultiplicationDyV {
	
	static int[][] multiplyMatrixBasic(int matrixA[][], int matrixB[][]) {
		int[][] matrixC = new int[matrixA.length][matrixB[0].length];

		for (int i = 0; i < matrixA.length; i++) {
	        for (int j = 0; j < matrixB.length; j++) {
	        	matrixC[i][j] = 0;
	        	for (int k = 0; k < matrixC.length; k++) {
	        		matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
	        	}
	        }
		}
		return matrixC;
	}
	 
	public static void printMat(int[][] a, int r, int c) {
		for (int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	 
	public static void print(String display, int[][] matrix, int startRow, int startColumn, int endRow, int endColumn) {
		System.out.println(display + " =>\n");
		for (int i = startRow; i <= endRow; i++) {
			for (int j = startColumn; j <= endColumn; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	public static void addMatrix(int[][] matrixA, int[][] matrixB, int[][] matrixC, int splitIndex) {
		for (int i = 0; i < splitIndex; i++) {
			for (int j = 0; j < splitIndex; j++) {
				matrixC[i][j] = matrixA[i][j] + matrixB[i][j];
			}
		}
	}
	
	public static void initWithZeros(int a[][], int r, int c) {
	    for(int i = 0; i < r; i++) {
	    	for(int j = 0; j < c; j++) {
	    		a[i][j] = 0;
	    	}
	    }
	}

	
	public static int[][] multiplyMatrix(int[][] matrixA,int[][] matrixB) {
		int col1 = matrixA[0].length;
		int row1 = matrixA.length;
		int col2 = matrixB[0].length;
		int row2 = matrixB.length;
	 
		if (col1 != row2) {
			System.out.println("\nError: The number of columns in Matrix A  must be equal to the number of rows in Matrix B\n");
			int temp[][] = new int[1][1];
			temp[0][0] = 0;
			return temp;
		}
	 
		int[] resultMatrixRow = new int[col2];
		Arrays.fill(resultMatrixRow, 0);
		int[][] resultMatrix = new int[row1][col2];
		initWithZeros(resultMatrix, row1, col2);
	 
		if (col1 == 1) {
			resultMatrix[0][0] = matrixA[0][0] * matrixB[0][0];
		}
		else {
			int splitIndex = col1 / 2;
	 
			int[] rowVector = new int[splitIndex];
			Arrays.fill(rowVector,0);
	 
			int[][] resultMatrix00 = new int[splitIndex][splitIndex];
			int[][] resultMatrix01 = new int[splitIndex][splitIndex];
			int[][] resultMatrix10 = new int[splitIndex][splitIndex];
			int[][] resultMatrix11 = new int[splitIndex][splitIndex];
			initWithZeros(resultMatrix00, splitIndex, splitIndex);
			initWithZeros(resultMatrix01, splitIndex, splitIndex);
			initWithZeros(resultMatrix10, splitIndex, splitIndex);
			initWithZeros(resultMatrix11, splitIndex, splitIndex);
	 
			int[][] a00 = new int[splitIndex][splitIndex];
			int[][] a01 = new int[splitIndex][splitIndex];
			int[][] a10 = new int[splitIndex][splitIndex];
			int[][] a11 = new int[splitIndex][splitIndex];
			int[][] b00 = new int[splitIndex][splitIndex];
			int[][] b01 = new int[splitIndex][splitIndex];
			int[][] b10 = new int[splitIndex][splitIndex];
			int[][] b11 = new int[splitIndex][splitIndex];
			initWithZeros(a00, splitIndex, splitIndex);
			initWithZeros(a01, splitIndex, splitIndex);
			initWithZeros(a10, splitIndex, splitIndex);
			initWithZeros(a11, splitIndex, splitIndex);
			initWithZeros(b00, splitIndex, splitIndex);
			initWithZeros(b01, splitIndex, splitIndex);
			initWithZeros(b10, splitIndex, splitIndex);
			initWithZeros(b11, splitIndex, splitIndex);
			
			for (int i = 0; i < splitIndex; i++) {
				for (int j = 0; j < splitIndex; j++) {
					a00[i][j] = matrixA[i][j];
					a01[i][j] = matrixA[i][j + splitIndex];
					a10[i][j] = matrixA[splitIndex + i][j];
					a11[i][j] = matrixA[i + splitIndex][j + splitIndex];
					b00[i][j] = matrixB[i][j];
					b01[i][j] = matrixB[i][j + splitIndex];
					b10[i][j] = matrixB[splitIndex + i][j];
					b11[i][j] = matrixB[i + splitIndex][j + splitIndex];
				}
			}
	 
			addMatrix(multiplyMatrix(a00, b00), multiplyMatrix(a01, b10), resultMatrix00, splitIndex);
			addMatrix(multiplyMatrix(a00, b01), multiplyMatrix(a01, b11), resultMatrix01, splitIndex);
			addMatrix(multiplyMatrix(a10, b00), multiplyMatrix(a11, b10), resultMatrix10, splitIndex);
			addMatrix(multiplyMatrix(a10, b01), multiplyMatrix(a11, b11), resultMatrix11, splitIndex);
	 
			for (int i = 0; i < splitIndex; i++) {
				for (int j = 0; j < splitIndex; j++) {
					resultMatrix[i][j] = resultMatrix00[i][j];
					resultMatrix[i][j + splitIndex] = resultMatrix01[i][j];
					resultMatrix[splitIndex + i][j] = resultMatrix10[i][j];
					resultMatrix[i + splitIndex] [j + splitIndex] = resultMatrix11[i][j];
				}
			}
		}
		
		return resultMatrix;
	}

	public static void main(String[] args) {
		int[][] matrixA = {
				{ 1, 1, 1, 1 },
				{ 2, 2, 2, 2 },
				{ 3, 3, 3, 3 },
				{ 2, 2, 2, 2 } };

		System.out.println("Matrix A =>");
		printMat(matrixA, 4, 4);

		int[][] matrixB = {
				{ 1, 1, 1, 1 },
				{ 2, 2, 2, 2 },
				{ 3, 3, 3, 3 },
				{ 2, 2, 2, 2 } };

		System.out.println("Matrix B =>");
		printMat(matrixB, 4, 4);

		int[][] resultMatrix =  multiplyMatrix(matrixA, matrixB);
		//int[][] resultMatrix =  multiplyMatrixBasic(matrixA, matrixB);

		System.out.println("Result Matrix =>");
		printMat(resultMatrix, 4, 4);
	}

}
