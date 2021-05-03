package com.practice.graph.BellmanFord;

import java.util.ArrayList;

import com.practice.node.WeightedNode;

public class PathFindingByBellmanFord {

	// Will store all the vertices
	ArrayList<WeightedNode> nodeList = new ArrayList<WeightedNode>();

	// Constructor
	public PathFindingByBellmanFord(ArrayList<WeightedNode> nodeList) {
		this.nodeList = nodeList;
		for (WeightedNode aNode : nodeList) {
			aNode.setDistance(Integer.MAX_VALUE / 10);
			/**
			 * Integer.MAX_VALUE = 2147483647 distance>max+max so if we take max value then
			 * unnecessarily it will enter in the loop and we can fall in trouble so
			 * therefore we to take max/10 such that when we add two max/10+max/10 it does
			 * not cross 2147483647.
			 */
		}
	}// end of method

	// BellmanFord from a source node
	void bellmanFord(WeightedNode sourceNode) {
		sourceNode.setDistance(0); // set source distance to zero

		for (int i = 1; i < nodeList.size(); i++) { // repeat n-1 times
			for (WeightedNode presentNode : nodeList) { // for each Vertex
				for (WeightedNode neighbor : presentNode.getNeighbors()) { // for each Neighbor
					// if distance of neighbor is greater than tentative new path then
					// update distance of neighbor with new parent as presentNode
					if (neighbor
							.getDistance() > (presentNode.getDistance() + presentNode.getWeightMap().get(neighbor))) {
						neighbor.setDistance((presentNode.getDistance() + presentNode.getWeightMap().get(neighbor)));
						neighbor.setParent(presentNode);
						System.out.println("PresentNode " + presentNode);
						System.out.println("Neighbour details");
						System.out.println("Neighbour Name: " + neighbor.getName());
						System.out.println("Old Distance: " + neighbor.getDistance());
						int newDistance = presentNode.getDistance() + presentNode.getWeightMap().get(neighbor);
						System.out.println("New distance: " + newDistance);
						System.out.println();

					}
				} // end of inner loop
			} // end of mid loop
		} // end of loop

		System.out.println("Checking for Negative Cycle ...");
		// for each edge check if update possible, if true then negative cycle is there
		// report error
		for (WeightedNode presentNode : nodeList) {
			for (WeightedNode neighbor : presentNode.getNeighbors()) {
				// if distance of neighbor is greater than tentative new path then we have a
				// negative cycle, return from here..
				if (neighbor.getDistance() > (presentNode.getDistance() + presentNode.getWeightMap().get(neighbor))) {
					System.out.println("Negative cycle found: \n");
					System.out.println("Vertex Name: " + neighbor.getName());
					System.out.println("Old Distance: " + neighbor.getDistance());
					int newDistance = presentNode.getDistance() + presentNode.getWeightMap().get(neighbor);
					System.out.println("New distance: " + newDistance);
					return;
				}
			}
		} // end of loop
		System.out.println("Negative cycle not found !");

		// Print table of node with minimum distance and shortest path from source
		System.out.println("\n\nPrinting Paths now: ");
		for (WeightedNode nodeToCheck : nodeList) {
			if (nodeToCheck.getDistance() != Integer.MAX_VALUE / 10) {
				System.out.print("Node " + nodeToCheck + ", distance: " + nodeToCheck.getDistance() + ", Path: ");
				pathPrint(nodeToCheck);
			} else {
				System.out.print("No path for node " + nodeToCheck);
			}
			System.out.println();
		} // end of loop
	}// end of method

	// Printing path
	private static void pathPrint(WeightedNode node) {
		if (node.getParent() != null) {
			pathPrint(node.getParent());
			System.out.print("->" + node);
		} else
			System.out.print(node);
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

		// create 5 nodes: A,B,C,D,E
		for (int i = 0; i < 5; i++) {
			nodeList.add(new WeightedNode("" + (char) (65 + i)));
		}

		// Constructor
		PathFindingByBellmanFord graph = new PathFindingByBellmanFord(nodeList);

		graph.addWeightedEdge(1, 3, 6); // Add A-> C , weight 6
		graph.addWeightedEdge(2, 1, 3); // Add B-> A , weight 3
		// graph.addWeightedEdge(1,4,6); //Add A-> D , weight 6
		graph.addWeightedEdge(1, 4, -6); // Add A-> D , weight -6 TEST NEGATIVE WEIGHT HERE
		graph.addWeightedEdge(4, 3, 1); // Add D-> C , weight 1
		graph.addWeightedEdge(3, 4, 2); // Add C-> D , weight 2
		graph.addWeightedEdge(4, 2, 1); // Add D-> B , weight 1
		graph.addWeightedEdge(5, 4, 2); // Add E-> D , weight 2
		graph.addWeightedEdge(5, 2, 4); // Add E-> B , weight 4

		graph.bellmanFord(nodeList.get(4));

	}// end of method

}
