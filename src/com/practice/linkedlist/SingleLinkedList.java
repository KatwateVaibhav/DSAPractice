package com.practice.linkedlist;

import com.practice.node.SingleNode;

/**
 * Step1 - Create blank head and tail & intialize both to them with null Step2-
 * we create blank node, we inserted value in it and reference intiaize to null
 * Step3 - linked head tail with new node
 **/
public class SingleLinkedList {
	private SingleNode head;
	private SingleNode tail;
	private int size;// denotes size of list

	public SingleNode getHead() {
		return head;
	}

	public void setHead(SingleNode head) {
		this.head = head;
	}

	public SingleNode getTail() {
		return tail;
	}

	public void setTail(SingleNode tail) {
		this.tail = tail;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
	public SingleNode createSingleLinkedList(int nodeValue) {
		head = new SingleNode();
		SingleNode node = new SingleNode();
		node.setValue(nodeValue);
		node.setNext(null);
		head = node;
		tail = node;
		size = 1;// size =1
		return head;
	}

	public void insertInLinkedList(int nodeValue, int location) {
		SingleNode node = new SingleNode();
		node.setValue(nodeValue);
		if (!existsLinkedList()) { // Linked List does not exists
			System.out.println("The linked list does not exist!!");
			return;
		} else if (location == 0) { // Insert at first position
			System.out.println("head--->" + head);
			node.setNext(head);
			head = node; // change head to new node
			System.out.println("head at first position--->" + head);
		} else if (location >= size) {// Insert at last position
			System.out.println("head--->" + head);
			node.setNext(null);// first change next to null to make it end node
			tail.setNext(node);// assign existing tail's next to newly created node
			tail = node; // make newly created node as tail
			System.out.println("adding node as tail at last position " + head);
		} else {// insert at specified location
			SingleNode tempNode = head;
			int index = 0;
			while (index < location - 1) {// loop from 0 till we reach specified node
				tempNode = tempNode.getNext();
				index++;
			} // Once loop exits tempNode currently references to node after which we should
				// insert new node
			SingleNode nextNode = tempNode.getNext(); // this is the immediate next node after new node
			tempNode.setNext(node);// update reference of tempNode to reference to new node
			node.setNext(nextNode);// update newly added nodes' next.
		}
		setSize(getSize() + 1);

	}

	private boolean searchNodeValue(int nodeValue) {
		if (existsLinkedList()) {
			SingleNode tempNode = head;
			for (int i = 0; i < getSize(); i++) {
				if (tempNode.getValue() == nodeValue) {
					System.out.println("Node Value found in Single Linked list");
					return true;
				}
				tempNode = tempNode.getNext();
			}
		}
		System.out.println("Node Value not found");
		return false;

	}

	// Deletes a node having a given value
	public void deleteNode(int location) {
		if (!existsLinkedList()) {
			System.out.println("The linked list does not exist!!");// Linked List does not exists
			return;
		} else if (location == 0) { // we want to delete first element
			head = head.getNext(); // here garbage collector will collect unreferenced node
			setSize(getSize() - 1);
			if (getSize() == 0) { // if there are no more nodes in this list
				tail = null;
			}
		} else if (location >= getSize()) { // If location is not in range or equal, then delete last node
			SingleNode tempNode = head;
			for (int i = 0; i < size - 1; i++) {
				tempNode = tempNode.getNext(); // temp node points to 2nd last node
			}
			if (tempNode == head) { // if this is the only element in the list
				tail = head = null;
				setSize(getSize() - 1);
				return;
			}
			tempNode.setNext(null);
			tail = tempNode;
			setSize(getSize() - 1);

		} else { // if any inside node is to be deleted
			SingleNode tempNode = head;
			for (int i = 0; i < location - 1; i++) {
				tempNode = tempNode.getNext(); // we need to traverse till we find the location
			}
			tempNode.setNext(tempNode.getNext().getNext()); // delete the required node
			setSize(getSize() - 1);
		} // end of else

	}// end of method

	// Deletes entire Linked List
	void deleteLinkedList() {
		System.out.println("\n\nDeleting Linked List...");
		head = null;
		tail = null;
		System.out.println("Linked List deleted successfully !");
	}

	public boolean existsLinkedList() {
		// if head is not null return true otherwise return false
		return head != null;
	}

	// Traverses Linked List
	void traverseLinkedList() {
		if (existsLinkedList()) {
			SingleNode tempNode = head;
			for (int i = 0; i < getSize(); i++) {
				System.out.print(tempNode.getValue());
				if (i != getSize() - 1) {
					System.out.print(" -> ");
				}
				tempNode = tempNode.getNext();
			}
		} else {
			System.out.println("Linked List does not exists !");
		}
		System.out.println("\n");
	}

	public static void main(String[] args) {

		SingleLinkedList list = new SingleLinkedList();
		list.createSingleLinkedList(5);
		System.out.println("Created Linked list " + list.size);
		list.traverseLinkedList();

		list.insertInLinkedList(10, 0);
		list.traverseLinkedList();

		list.insertInLinkedList(20, 2);
		list.traverseLinkedList();

		list.insertInLinkedList(30, 2);
		list.traverseLinkedList();

		list.insertInLinkedList(40, 1);
		list.traverseLinkedList();

		System.out.println();

		System.out.println("\nSearching the node having value 40...");
		list.searchNodeValue(40);

		System.out.println("\nSearching the node having value 500...");
		list.searchNodeValue(500);

		System.out.println("\n\nDeleting the node having location = 0: ");
		System.out.println("Before Deletion:");
		list.traverseLinkedList();
		list.deleteNode(0);
		System.out.println("After Deletion:");
		list.traverseLinkedList();
		System.out.println();

		System.out.println("\n\nDeleting the node having location = 2: ");
		System.out.println("Before Deletion:");
		list.traverseLinkedList();
		list.deleteNode(2);
		System.out.println("After Deletion:");
		list.traverseLinkedList();
		System.out.println();

		System.out.println("\n\nDeleting the node having location = 100: ");
		System.out.println("Before Deletion:");
		list.traverseLinkedList();
		list.deleteNode(100);
		System.out.println("After Deletion:");
		list.traverseLinkedList();
		System.out.println();

		list.deleteLinkedList();
		list.traverseLinkedList();

	}

}
