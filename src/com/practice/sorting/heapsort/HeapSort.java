package com.practice.sorting.heapsort;

import com.practice.binaryHeap.HeapByArray;

public class HeapSort {
	int[] arr = null;

	// Constructor
	public HeapSort(int[] arr) {
		this.arr = arr;
	}// end of method

	public void sort() {
		HeapByArray hba = new HeapByArray(arr.length); // We will reuse HeapByArray class to do sorting
		for (int i = 0; i < arr.length; i++) { // Insert in Heap
			hba.insertInHeap(arr[i]);
		}
		for (int i = 0; i < arr.length; i++) { // Extract from Heap and insert sorted data in current Array
			arr[i] = hba.extractHeadOfHeap();
		}
	}// end of method

	public void printArray() {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
	}// end of method

	public static void main(String[] args) {

		int arr[] = { 10, 3, 2, 5, 8, 4, 3, 1, 2, 9, 7, 8 };
		HeapSort hs = new HeapSort(arr);

		System.out.println("User entered Array: ");
		hs.printArray();
		System.out.println("\n");

		hs.sort();

		System.out.println("\n\nAfter sorting: ");
		hs.printArray();
	}// end of method

}
