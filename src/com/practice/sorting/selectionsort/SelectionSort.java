package com.practice.sorting.selectionsort;

public class SelectionSort {

	static void selectionSort(int[] a) {
		for (int i = 0; i<a.length; i++) {
			int minimumIndex = i;
			for (int j=i+1; j<a.length; j++) {
				if (a[j] < a[minimumIndex]) //find which is the smallest element to right of 'i' in array
					minimumIndex = j;
			}//end of inner loop
			if (minimumIndex != i) { // if i is not minimum index then swap
				int temp = a[i];
				a[i] = a[minimumIndex];
				a[minimumIndex] = temp;
			}
		}//end of outer loop
	}//end of method

	
	public static void printArray(int []array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+"  ");
		}
	}//end of method

	public static void main(String[] args) {
		int array[] = {10, 3, 2, 5, 8, 4, 3, 1, 2, 9, 7, 8};
		
		System.out.println("User entered Array: ");
		printArray(array);
		
		selectionSort(array);
		
		System.out.println("\n\nAfter sorting: ");
		printArray(array);
	}//end of method

}
