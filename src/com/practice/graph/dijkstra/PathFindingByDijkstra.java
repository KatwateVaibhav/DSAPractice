package com.practice.graph.dijkstra;

import java.util.ArrayList;
import java.util.PriorityQueue;

import com.practice.node.WeightedNode;

public class PathFindingByDijkstra {
	ArrayList<WeightedNode> nodeList = new ArrayList<WeightedNode>();

//Constructor
	public PathFindingByDijkstra(ArrayList<WeightedNode> nodeList) {
		this.nodeList = nodeList;
	}

//Dijkstra from a Source Node
	void dijkstra(WeightedNode node) {
		PriorityQueue<WeightedNode> queue = new PriorityQueue<>();
		node.setDistance(0); // This will make sure that we start from this vertex in priority queue as
								// distance is min
		queue.addAll(nodeList);
		while (!queue.isEmpty()) {
			WeightedNode presentNode = queue.remove(); // remove node with minimum distance from queue
			for (WeightedNode neighbor : presentNode.getNeighbors()) { // for each neighbor
				if (queue.contains(neighbor)) { // if neighbor is not visited
					// if 'known distance' of neighbor is greater than new path then,
					// update 'distance' of neighbor
					// and also new parent as presentNode
					if (neighbor
							.getDistance() > (presentNode.getDistance() + presentNode.getWeightMap().get(neighbor))) {
						neighbor.setDistance((presentNode.getDistance() + presentNode.getWeightMap().get(neighbor)));
						neighbor.setParent(presentNode);
						// Refresh the Priority Queue
						queue.remove(neighbor);
						queue.add(neighbor);
					} // end of if block
				} // end of if block
			} // end of for loop
		} // end of while loop

		// print table of node with minimum distance and shortest path from source
		for (WeightedNode nodeToCheck : nodeList) {
			System.out.print("Node " + nodeToCheck + ", distance: " + nodeToCheck.getDistance() + ", Path: ");
			pathPrint(nodeToCheck);
			System.out.println();
		}
	}// end of method

	private static void pathPrint(WeightedNode node) {
		if (node.getParent() != null) {
			pathPrint(node.getParent());
			System.out.print("->" + node);
		} else
			System.out.print(node);
	}// end of method

// add a weighted directed edge between two nodes
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
			System.out.println(nodeList.toString());
		}

		// Constructor
		PathFindingByDijkstra graph = new PathFindingByDijkstra(nodeList);

		graph.addWeightedEdge(1, 3, 6); // Add A-> C , weight 6
		graph.addWeightedEdge(1, 4, 3); // Add A-> D , weight 3
		graph.addWeightedEdge(2, 1, 3); // Add B-> A , weight 3
		graph.addWeightedEdge(3, 4, 2); // Add C-> D , weight 2
		graph.addWeightedEdge(4, 3, 1); // Add D-> C , weight 1
		graph.addWeightedEdge(4, 2, 1); // Add D-> B , weight 1
		graph.addWeightedEdge(5, 2, 4); // Add E-> B , weight 4
		graph.addWeightedEdge(5, 4, 2); // Add E-> D , weight 2

		System.out.println("Printing Dijkstra from source: E");
		graph.dijkstra(nodeList.get(4));
	}// end of method

}
