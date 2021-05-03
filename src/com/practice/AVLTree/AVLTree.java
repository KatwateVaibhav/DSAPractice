package com.practice.AVLTree;

import java.util.LinkedList;
import java.util.Queue;

import com.practice.node.BinaryNode;

public class AVLTree {
	BinaryNode root;
	public BinaryNode getRoot() {
		return root;
	}

	
	// Constructor
	AVLTree() {
		root = null;
	}

	
	// Insert values in AVL Tree
	void insert(int value) {
		root = insert(root, value);
	}// end of method

	
	// Helper Method
	BinaryNode insert(BinaryNode currentNode, int value) {
		// THIS ELSE_IF BLOCK IS BST CONDITION
		if (currentNode == null) {
			System.out.println("Successfully inserted " + value + " in AVL Tree");
			return createNewNode(value);
		} else if (value <= currentNode.getValue()) {
			currentNode.setLeft(insert(currentNode.getLeft(), value));
		} else {
			currentNode.setRight(insert(currentNode.getRight(), value));
		}

		// THIS IS WHERE WE WILL DO AVL SPECIFIC WORK
		int balance = checkBalance(currentNode.getLeft(), currentNode.getRight());
		if (balance > 1) {
			if (checkBalance(currentNode.getLeft().getLeft(), currentNode.getLeft().getRight()) > 0) {
				currentNode = rightRotate(currentNode);// LL Condition
			} else {
				currentNode.setLeft(leftRotate(currentNode.getLeft())); // LR Condition
				currentNode = rightRotate(currentNode);
			}
		} else if (balance < -1) {
			if (checkBalance(currentNode.getRight().getRight(), currentNode.getRight().getLeft()) > 0) {
				currentNode = leftRotate(currentNode);// RR Condition

			} else {
				currentNode.setRight(rightRotate(currentNode.getRight()));// RL Condition
				currentNode = leftRotate(currentNode);

			}
		}

		if (currentNode.getLeft() != null) {
			currentNode.getLeft().setHeight(calculateHeight(currentNode.getLeft()));
		}
		if (currentNode.getRight() != null) {
			currentNode.getRight().setHeight(calculateHeight(currentNode.getRight()));
		}
		currentNode.setHeight(calculateHeight(currentNode));
		return currentNode;

	}// end of method

	
	/* Helper Method
	 * 				40
				   /  \
	 * 			  30   50
	 * 			 /
	 * 		   20
	 *		   / 
	 * 		 10
	 * 
	 * 
	*/			
	private BinaryNode leftRotate(BinaryNode currentNode) {
		BinaryNode newRoot = currentNode.getRight();
		currentNode.setRight(currentNode.getRight().getLeft());
		newRoot.setLeft(currentNode);
		currentNode.setHeight(calculateHeight(currentNode));
		newRoot.setHeight(calculateHeight(newRoot));
		return newRoot;
	}// end of method

	
	// Helper Method
	private BinaryNode rightRotate(BinaryNode currentNode) {
		BinaryNode newRoot = currentNode.getLeft();
		currentNode.setLeft(currentNode.getLeft().getRight()); //make null to currentDisbalanced left node and break the link
		newRoot.setRight(currentNode);
		currentNode.setHeight(calculateHeight(currentNode));
		newRoot.setHeight(calculateHeight(newRoot));
		return newRoot;
	}// end of method

	
	// Helper Method
	private int checkBalance(BinaryNode rootLeft, BinaryNode rootRight) {
		if((rootLeft == null) && (rootRight == null)) { //if current node is a leaf node then no need to check balance of its children
			return 0;
		}else if (rootLeft == null) {
			return -1 * (rootRight.getHeight() + 1); // if left node node is not there then simply return right node's
														// height + 1
														// we need to make it -1 because blank height is considered
														// having height as '-1'
		} else if (rootRight == null) {
			return rootLeft.getHeight() + 1;
		} else {
			return rootLeft.getHeight() - rootRight.getHeight(); // +1 is not required, as both right and left child
																	// exits and 1 gets nullified
		}
	}// end of method

	
	// Calculate height of Node
	private int calculateHeight(BinaryNode currentNode) {
		if (currentNode == null) {
			return 0;
		}
		return 1 + Math.max((currentNode.getLeft() != null ? currentNode.getLeft().getHeight() : -1),
				(currentNode.getRight() != null ? currentNode.getRight().getHeight() : -1));

	}// end of method

	
	// creates a new blank new node
	public BinaryNode createNewNode(int value) {
		BinaryNode node = new BinaryNode();
		node.setValue(value);
		node.setHeight(0);// Since this is a leaf node, its height is 0
		return node;
	}// end of method

	
	// Level order traversal of BST
	void levelOrderTraversal() {
		Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
		queue.add(root);
		System.out.println("Printing Level order traversal of AVL Tree...");
		if (root == null) {
			System.out.println("Tree does not exists !");
			return;
		}
		while (!queue.isEmpty()) {
			BinaryNode presentNode = queue.remove();
			System.out.print(presentNode.getValue() + " ");
			if (presentNode.getLeft() != null)
				queue.add(presentNode.getLeft());
			if (presentNode.getRight() != null)
				queue.add(presentNode.getRight());
		}
	}// end of method

	
	// Deleting a node from BST
	public void deleteNodeOfBST(int value) {
		System.out.println("Deleting " + value + " from AVL Tree ...");
		root = deleteNodeOfAVL(root, value);
	}

	
	// Helper Method for delete
	public BinaryNode deleteNodeOfAVL(BinaryNode currentNode, int value) {
		// THIS ELSE_IF BLOCK IS BST CONDITION
		if (currentNode == null)
			return null;
		if (value < currentNode.getValue()) {
			currentNode.setLeft(deleteNodeOfAVL(currentNode.getLeft(), value));
		} else if (value > currentNode.getValue()) {
			currentNode.setRight(deleteNodeOfAVL(currentNode.getRight(), value));
		} else { // If currentNode is the node to be deleted
			//System.out.println("currentNode is the node to be deleted");
			if (currentNode.getLeft() != null && currentNode.getRight() != null) { // if nodeToBeDeleted have both children //2 //40
				BinaryNode temp = currentNode;//10
				BinaryNode minNodeForRight = minimumElement(temp.getRight());// Finding minimum element from right subtree //30
				currentNode.setValue(minNodeForRight.getValue()); // Replacing current node with minimum node from right subtree
				currentNode.setRight(deleteNodeOfAVL(currentNode.getRight(), minNodeForRight.getValue()));// Deleting minimum node from right now
			} else if (currentNode.getLeft() != null) {// if nodeToBeDeleted has only left child
				currentNode = currentNode.getLeft();
			} else if (currentNode.getRight() != null) {// if nodeToBeDeleted has only right child
				currentNode = currentNode.getRight();
			} else { // if nodeToBeDeleted do not have child (Leaf node)
				//System.out.println("This node is leaf node");
				currentNode = null;
			}
			return currentNode;// if it is a leaf node,then no need to do balancing for this node, do only for its ancestors
		}

		// THIS IS WHERE WE WILL DO AVL SPECIFIC WORK
		int balance = checkBalance(currentNode.getLeft(), currentNode.getRight());
		if (balance > 1) {
			if (checkBalance(currentNode.getLeft().getLeft(), currentNode.getLeft().getRight()) > 0) {
				currentNode = rightRotate(currentNode);// LL Condition
			} else {
				currentNode.setLeft(leftRotate(currentNode.getLeft())); // LR Condition
				currentNode = rightRotate(currentNode);
			}
		} else if (balance < -1) {
			if (checkBalance(currentNode.getRight().getRight(), currentNode.getRight().getLeft()) > 0) {
				currentNode = leftRotate(currentNode);// RR Condition
			} else {
				currentNode.setRight(rightRotate(currentNode.getRight()));// RL Condition
				currentNode = leftRotate(currentNode);
			}
		}

		if (currentNode.getLeft() != null) {
			currentNode.getLeft().setHeight(calculateHeight(currentNode.getLeft()));
		}
		if (currentNode.getRight() != null) {
			currentNode.getRight().setHeight(calculateHeight(currentNode.getRight()));
		}
		currentNode.setHeight(calculateHeight(currentNode));
		return currentNode;

	}// end of method

	
	// Get minimum element in binary search tree
	public static BinaryNode minimumElement(BinaryNode root) {
		if (root.getLeft() == null)
			return root;
		else {
			return minimumElement(root.getLeft());
		}
	}// end of method
	
	
	void printTreeGraphically() {
		Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
		Queue<Integer> level = new LinkedList<Integer>();
		
		int CurrentLevel = 1;
		boolean previousLevelWasAllNull = false;
		queue.add(root);
		level.add(1);
		
		System.out.println("\nPrinting Level order traversal of Tree...");
		if(root == null) {
			System.out.println("Tree does not exists !");
			return;
		}
		
		while (!queue.isEmpty()) {
			if(CurrentLevel == level.peek()) { //if we are in the same level
				if(queue.peek()==null) {
					queue.add(null);level.add(CurrentLevel+1);
				}else {
					queue.add(queue.peek().getLeft());level.add(CurrentLevel+1);
					queue.add(queue.peek().getRight());level.add(CurrentLevel+1);
					previousLevelWasAllNull = false;
				}
				System.out.print(queue.remove() + "  ");level.remove();
			}else { //level has changed
				System.out.println("\n");
				CurrentLevel++;
				if(previousLevelWasAllNull == true) {
					break;
				}
				previousLevelWasAllNull = true;
			}
		}//end of loop
	}//end of method

	public static void main(String[] args) {

		// Constructor
		AVLTree tree = new AVLTree();
		
		// Insert values in AVL Tree
		tree.insert(30);
		tree.insert(10);
		tree.insert(5);
		tree.insert(3);
		tree.insert(4);
		tree.insert(50);
		tree.insert(65);
		tree.insert(1);
		
		tree.levelOrderTraversal();
		tree.printTreeGraphically();
		/*
		 *		 10
		 * 		/   \
		 * 	  4 	 50
		 *   / \    /  \
		 *  3   5   30 65
		 * / \
		 * 1  null null null null null null null
		 * 
		 * null null null null null null null null null
		 */
		
		tree.deleteNodeOfBST(5);//LL Condition
		tree.printTreeGraphically();
/*			10   
		   /   \
		  3     50   
		 / \    / \
		1   4  30   65   
       /\  /\  /\   /\
	null null  null  null  null  null  null  null  
*/

		
		tree.insert(2);
		tree.printTreeGraphically();
		
		/*		10   
			   /  \
			  3    50   
			/  \   /  \
		   1   4  30   65   
		  / \
		null  2   null  null  null  null  null  null  

		null  null  null  null  null  null  null  null  null  
*/
		tree.deleteNodeOfBST(4);//LR Condition
		tree.printTreeGraphically();
		
		/*	      10   
				/   \
			   2      50   
             /   \    /  \
			1    3   30   65   

		null  null  null  null  null  null  null  null  

*/		
		tree.insert(20);
		tree.deleteNodeOfBST(65);//RR Condition
		tree.printTreeGraphically();
		/* 10   
		   / \
		 2    30   
        / \   / \
	   1   3 20   50   

	null  null  null  null  null  null  null  null  

*/		
		tree.insert(40);
		tree.deleteNodeOfBST(20);//RL Condition
		tree.printTreeGraphically();
		
		  /* 10   
			/  \
		   2    40   
          / \   / \
		 1   3 30 50   

		null  null  null  null  null  null  null  null
		 */ 

		
		tree.deleteNodeOfBST(10);//RL Condition
		tree.printTreeGraphically();
		/*
		 *     30
		 *    /  \
		 *   2    40
		 *  / \  /  \
		 * 1  3  null 50
		 * 
		 * null null null null null null null
		 */		
		
	}// end of method

}
