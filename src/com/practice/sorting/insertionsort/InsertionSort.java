package com.practice.sorting.insertionsort;

public class InsertionSort {
/*
 *  10, 3, 2, 5, 8, 4, 3, 1, 2, 9, 7, 8
 *      | 
 *     tmp
 *     
 *  10, 3, 2, 5, 8, 4, 3, 1, 2, 9, 7, 8
 *         |
 *         tmp   
 * */
	
	
	static void insertionSort(int[] A) {
		for (int i = 1; i < A.length; i++) {
			int tmp = A[i], j = i; // 'j' is index of array and tmp points in incremental way.
			while (j > 0 && A[j - 1] > tmp) {

				A[j] = A[j - 1];
				j--;
			}
			A[j] = tmp;
		} // end of for loop
	}// end of method

	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "  ");
		}
	}// end of method

	public static void main(String[] args) {

		int array[] = { 10, 3, 2, 5, 8, 4, 3, 1, 2, 9, 7, 8 };

		System.out.println("User entered Array: ");
		InsertionSort.printArray(array);

		long start = System.nanoTime();
		InsertionSort.insertionSort(array);
		long end = System.nanoTime();
		System.out.println("\n\nTime to execute this algo: " + (end - start));

		System.out.println("\nAfter sorting: ");
		InsertionSort.printArray(array);
	}// end of method

}
