package com.practice.sorting.mergesort;

public class MergeSort {

	int[] array;
	int[] tempMergeArr;
	int length;

	public static void main(String[] args) {
		int[] inputArr = { 10, 3, 2, 5, 8, 4, 3, 1, 2, 9, 7, 8 };
		MergeSort mergeSort = new MergeSort();

		System.out.println("User entered Array: ");
		MergeSort.printArray(inputArr);

		long start = System.nanoTime();
		mergeSort.mergeSortArr(inputArr);
		long end = System.nanoTime();
		System.out.println("\n\nTime to execute this algo: " + (end - start));

		System.out.println("\nAfter sorting: ");
		MergeSort.printArray(inputArr);
	}

	private void mergeSortArr(int[] inputArr) {
		this.array = inputArr;
		this.length = inputArr.length;
		this.tempMergeArr = new int[length];
		divideArray(0, length - 1);

	}

	private void divideArray(int lowerIndex, int higherIndex) {
		if (lowerIndex < higherIndex) {
			int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
			// Sort Left side of Array
			divideArray(lowerIndex, middle);
			// Sort Right side of Array
			divideArray(middle + 1, higherIndex);
			merge(lowerIndex, middle, higherIndex);
		}
	}

	private void merge(int lowerIndex, int middle, int higherIndex) {

		for (int i = lowerIndex; i <= higherIndex; i++) {
			tempMergeArr[i] = array[i]; // Copy all data from Original array to temp array
		}
		// Values taken in i,j,k as we dont want to change original value passed in
		// parameters of this method//
		int i = lowerIndex;
		int j = middle + 1;
		int k = lowerIndex;

		while (i <= middle && j <= higherIndex) {
			if (tempMergeArr[i] <= tempMergeArr[j]) {
				array[k] = tempMergeArr[i]; // copy minimum value to original array K'th position
				i++;
			} else {
				array[k] = tempMergeArr[j];
				j++;
			}
			k++;
		}
		while (i <= middle) {
			array[k] = tempMergeArr[i];//copy maximum value to original array at k'th position
			k++;
			i++;
		}

	}

	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "  ");
		}
	}// end of method

}
