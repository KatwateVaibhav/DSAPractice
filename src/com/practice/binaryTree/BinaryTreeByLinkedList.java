package com.practice.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

import com.practice.node.BinaryNode;

public class BinaryTreeByLinkedList {

	BinaryNode root;

	// Constructor for creating a blank Binary Tree
	BinaryTreeByLinkedList() {
		this.root = null;
	}

	// inserts a new node at deepest place in Tree
	void insert(int value) {
		BinaryNode node = new BinaryNode();
		node.setValue(value);
		if (root == null) {
			root = node;
			System.out.println("Successfully inserted new node at Root !");
			return;
		}
		Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			BinaryNode presentNode = queue.remove();
			if (presentNode.getLeft() == null) {
				presentNode.setLeft(node);
				System.out.println("Successfully inserted new node !");
				break;
			} else if (presentNode.getRight() == null) {
				presentNode.setRight(node);
				System.out.println("Successfully inserted new node !");
				break;
			} else {
				queue.add(presentNode.getLeft());
				queue.add(presentNode.getRight());
			} // end of else-if
		} // end of loop
	}// end of method

	// Search for a given value in binary tree
	void search(int value) {
		Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			BinaryNode presentNode = queue.remove();
			if (presentNode.getValue() == value) {
				System.out.println("Value-" + value + " is found in Tree !");
				return;
			} else {
				if (presentNode.getLeft() != null)
					queue.add(presentNode.getLeft());
				if (presentNode.getRight() != null)
					queue.add(presentNode.getRight());
			}
		} // end of loop
		System.out.println("Value-" + value + " is not found in Tree !");
	}// end of method

	// delete node from binary tree
	void deleteNodeOfBinaryTree(int value) {
		Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			BinaryNode presentNode = queue.remove();
			// if node is found then copy deepest node here and delete deepest node.
			if (presentNode.getValue() == value) {
				presentNode.setValue(getDeepestNode().getValue());
				DeleteDeepestNode();
				System.out.println("Deleted the node !!");
				return;
			} else {
				if (presentNode.getLeft() != null)
					queue.add(presentNode.getLeft());
				if (presentNode.getRight() != null)
					queue.add(presentNode.getRight());
			}
		} // end of while loop
		System.out.println("Did not find the node!!");
	}

	// Delete deepest node
	public void DeleteDeepestNode() {
		Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
		queue.add(root);
		BinaryNode previousNode, presentNode = null;
		while (!queue.isEmpty()) {
			previousNode = presentNode;
			presentNode = queue.remove();
			if (presentNode.getLeft() == null) {
				previousNode.setRight(null);
				return;
			} else if ((presentNode.getRight() == null)) {
				presentNode.setLeft(null);
				return;
			}
			queue.add(presentNode.getLeft());
			queue.add(presentNode.getRight());
		} // end of while loop
	}// end of method

	// get last node of last level of binary tree
	public BinaryNode getDeepestNode() {
		// make an empty queue. Queue is Interface and LinkedList is class
		Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
		queue.add(root);
		BinaryNode presentNode = null;
		while (!queue.isEmpty()) {
			presentNode = queue.remove();
			if (presentNode.getLeft() != null)
				queue.add(presentNode.getLeft());
			if (presentNode.getRight() != null)
				queue.add(presentNode.getRight());
		}
		return presentNode;
	}// end of method

	// pre-order traversal of binary tree
	void preOrder(BinaryNode node) {
		if (node == null)
			return;
		System.out.print(node.getValue() + " ");
		preOrder(node.getLeft());
		preOrder(node.getRight());
	}// end of method

	// post-order traversal of binary tree
	void postOrder(BinaryNode node) {
		if (node == null)
			return;
		postOrder(node.getLeft());
		postOrder(node.getRight());
		System.out.print(node.getValue() + " ");
	}// end of method

	// in-order traversal of binary tree
	void inOrder(BinaryNode node) {
		if (node == null) {
			return;
		}
		inOrder(node.getLeft());
		System.out.print(node.getValue() + " ");
		inOrder(node.getRight());
	}// end of method

	// level order traversal of binary tree
	void levelOrder() {
		// make a queue for level order. Queue is Interface and LinkedList is class
		Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			BinaryNode presentNode = queue.remove();
			System.out.print(presentNode.getValue() + " ");
			if (presentNode.getLeft() != null) {
				queue.add(presentNode.getLeft());
			}
			if (presentNode.getRight() != null)
				queue.add(presentNode.getRight());
		}
	}// end of method

	// Delete Tree
	void deleteTree() {
		root = null;
		System.out.println("Binary Tree has been deleted successfully");
	}

		public static void main(String[] args) {
		BinaryTreeByLinkedList treeByLinkedList = new BinaryTreeByLinkedList();
		
		/* Let us create following BST 	  10 
		 * 							   /     \
		 * 							  20      30
		 * 							 /  \      / \
		 * 							40   50   60 70
		 * 						    / \   /
		 * 						   80 90  100
		 */

		// Insert 10 nodes in the tree
		System.out.println("Inserting 10 nodes to tree");
		for (int i = 1; i <= 10; i++) {
			treeByLinkedList.insert(i * 10);
		}

		System.out.println("\nLevel-order of tree:");
		treeByLinkedList.levelOrder();
		System.out.println();

		System.out.println("\nPre-order of tree:");
		treeByLinkedList.preOrder(treeByLinkedList.root);
		System.out.println();

		System.out.println("\nPost-order of tree:");
		treeByLinkedList.postOrder(treeByLinkedList.root);
		System.out.println();

		System.out.println("\nIn-order of tree:");
		treeByLinkedList.inOrder(treeByLinkedList.root);
		System.out.println();

		System.out.println("\nSearching node 50 in the tree...");
		treeByLinkedList.search(50);

		System.out.println("\nSearching node 500 in the tree...");
		treeByLinkedList.search(500);

		System.out.println("level Order before deleting");
		treeByLinkedList.levelOrder();

		System.out.println("\nDeleting node having value-5 in the tree...");
		treeByLinkedList.deleteNodeOfBinaryTree(5);

		System.out.println("\nDeleting node having value-50 in the tree...");
		treeByLinkedList.deleteNodeOfBinaryTree(50);
		treeByLinkedList.levelOrder();

		System.out.println("\n\nDeleting node having value-30 in the tree...");
		treeByLinkedList.deleteNodeOfBinaryTree(30);
		treeByLinkedList.levelOrder();

		System.out.println("\n\nDeleting node having value-80 in the tree...");
		treeByLinkedList.deleteNodeOfBinaryTree(80);
		treeByLinkedList.levelOrder();
		
		System.out.println("\n\nDeleting middle node of tree...");
		treeByLinkedList.deleteNodeOfBinaryTree(20);
		treeByLinkedList.levelOrder(); //10 40 60 70 90 100 


		System.out.println("\n\nDeleting the entire Tree");
		treeByLinkedList.deleteTree();

	}
}
