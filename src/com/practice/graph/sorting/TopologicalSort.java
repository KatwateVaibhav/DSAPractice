package com.practice.graph.sorting;

import java.util.ArrayList;
import java.util.Stack;

import com.practice.node.GraphNode;

public class TopologicalSort {
	ArrayList<GraphNode> nodeList = new ArrayList<GraphNode>();

	// Constructor
	public TopologicalSort(ArrayList<GraphNode> nodeList) {
		this.nodeList = nodeList;
	}

	void topologicalSort() {
		Stack<GraphNode> stack = new Stack<>();
		for (GraphNode node : nodeList) { // if a node is unvisited then run topologicalSort on it
			if (!node.isVisited())
				topologicalVisit(node, stack);
		}
		while (!stack.isEmpty()) {
			System.out.print(stack.pop().getName() + " ");
		}
	}

	// Topological visit by a source node
	void topologicalVisit(GraphNode node, Stack<GraphNode> stack) {
		for (GraphNode neighbor : node.getNeighbors()) { // if neighbor is not visited then recursive call to it
			if (!neighbor.isVisited()) {
				topologicalVisit(neighbor, stack);
			}
		}
		node.setVisited(true);
		stack.push(node);
	} // end of method

	// add a directed edge between two nodes
	public void addDirectedEdge(int i, int j) {
		GraphNode first = nodeList.get(i - 1);
		GraphNode second = nodeList.get(j - 1);
		first.getNeighbors().add(second);
	} // end of method

	public static void main(String[] args) {
		ArrayList<GraphNode> nodeList = new ArrayList<>();

		for (int i = 1; i < 11; i++) {
			nodeList.add(new GraphNode("v" + i, i - 1));
		}

		TopologicalSort ts = new TopologicalSort(nodeList);
		ts.addDirectedEdge(1, 2);
		ts.addDirectedEdge(2, 5);
		ts.addDirectedEdge(2, 4);
		ts.addDirectedEdge(3, 7);
		ts.addDirectedEdge(4, 3);
		ts.addDirectedEdge(4, 8);
		ts.addDirectedEdge(5, 6);
		ts.addDirectedEdge(6, 4);
		ts.addDirectedEdge(6, 9);
		ts.addDirectedEdge(7, 10);
		ts.addDirectedEdge(8, 7);
		ts.addDirectedEdge(8, 10);
		ts.addDirectedEdge(9, 8);
		ts.addDirectedEdge(9, 10);

		ts.topologicalSort();
	}

}
