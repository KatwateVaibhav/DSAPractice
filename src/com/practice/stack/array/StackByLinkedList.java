package com.practice.stack.array;

import com.practice.linkedlist.SingleLinkedList;

public class StackByLinkedList {

	SingleLinkedList list;

	// constructor
	public StackByLinkedList() {
		list = new SingleLinkedList();
	}// end of method

	public void push(int value) {
		if (list.getHead() == null) {
			list.createSingleLinkedList(value);
		} else {
			list.insertInLinkedList(value, 0);
		}
		System.out.println("Inserted " + value + " in Stack !");
	}// end of method

	public int pop() {
		int value = -1;
		if (isEmpty()) {
			System.out.println("Stack underflow error!!");
		} else {
			value = list.getHead().getValue();
			list.deleteNode(0);
		}
		return value;
	}// end of method

	public boolean isEmpty() {
		if (list.getHead() == null)
			return true;
		else
			return false;
	}// end of method

	public int peek() {
		if (!isEmpty())
			return list.getHead().getValue();
		else {
			System.out.println("The stack is empty!!");
			return -1;
		}
	}// end of method

	public void deleteStack() {
		list.setHead(null);
		System.out.println("Deleted successfully ");
	}// end of method

	public static void main(String[] args) {

		StackByLinkedList stack = new StackByLinkedList();

		System.out.println("Pushing 10 values into stack");
		for (int i = 1; i <= 10; i++) {
			stack.push(i * 10);
		}
		System.out.println();

		System.out.println("Peeking value");
		System.out.println(stack.peek());
		System.out.println();

		System.out.println("Poping 11 values from stack");
		for (int i = 1; i <= 11; i++) {
			System.out.println(stack.pop());
		}
		System.out.println();
		
		System.out.println("Delete Method");
		stack.deleteStack();
		
	}
}
