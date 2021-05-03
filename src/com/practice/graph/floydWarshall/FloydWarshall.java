package com.practice.graph.floydWarshall;

import java.util.ArrayList;

import com.practice.node.WeightedNode;

public class FloydWarshall {

	ArrayList<WeightedNode> nodeList = new ArrayList<WeightedNode>();

	// Constructor
	public FloydWarshall(ArrayList<WeightedNode> nodeList) {
		this.nodeList = nodeList;
	}

	void floydWarshall() {
		int size = nodeList.size();
		int[][] V = new int[size][size]; // Creating 2D array for vertices.

		// Initializing Distance table from adjacency list
		for (int row = 0; row < size; row++) { // Row iteration
			WeightedNode first = nodeList.get(row);
			for (int col = 0; col < size; col++) { // Column iteration
				WeightedNode second = nodeList.get(col);
				if (row == col)
					V[row][col] = 0;
				else if (first.getWeightMap().containsKey(second)) { // we have direct edge between i & j
					V[row][col] = first.getWeightMap().get(second);
				} else { // no direct edge between i & j, so mark it as infinity [divided by 10 to avoid
							// arithmetic overflow]
					V[row][col] = Integer.MAX_VALUE / 10;
				}
			}
		} // end of loop

		// Floyd Warshall's Algorithm
		for (int viaNode = 0; viaNode < nodeList.size(); viaNode++) { // viaNode is node thorugh which we can reach from source to destination
			for (int source = 0; source < nodeList.size(); source++) {
				for (int destination = 0; destination < nodeList.size(); destination++) {
					if (V[source][destination] > V[source][viaNode] + V[viaNode][destination]) { // if update possible, then update path
						V[source][destination] = V[source][viaNode] + V[viaNode][destination]; // this will keep a track on path
					}
				}
			}
		} // end of loop

		// Print table of node with minimum distance and shortest path from each source
		for (int i = 0; i < size; i++) {
			System.out.print("Printing distance list for node " + nodeList.get(i) + ": ");
			for (int j = 0; j < size; j++) {
				System.out.print(V[i][j] + " ");
			}
			System.out.println();
		}

	}// end of method

	// add a weighted edge between two nodes
	public void addWeightedEdge(int i, int j, int d) {
		WeightedNode first = nodeList.get(i - 1);
		WeightedNode second = nodeList.get(j - 1);
		first.getNeighbors().add(second);
		first.getWeightMap().put(second, d);
	}// end of method

	public static void main(String[] args) {
		ArrayList<WeightedNode> nodeList = new ArrayList<>();

		// Create 4 Vertices: A,B,C,D
		for (int i = 0; i < 4; i++) {
			nodeList.add(new WeightedNode("" + (char) (65 + i)));
		}

		FloydWarshall graph = new FloydWarshall(nodeList);
		graph.addWeightedEdge(1, 4, 1);// Add A-> D , weight 1
		graph.addWeightedEdge(1, 2, 8);// Add A-> B , weight 8
		graph.addWeightedEdge(2, 3, 1);// Add B-> C , weight 1
		graph.addWeightedEdge(3, 1, 4);// Add C-> A , weight 4
		graph.addWeightedEdge(4, 2, 2);// Add D-> B , weight 2
		graph.addWeightedEdge(4, 3, 9);// Add D-> C , weight 9

		System.out.println("Printing Floyd-Warshall from each source:");
		graph.floydWarshall();
	}// end of method

}
