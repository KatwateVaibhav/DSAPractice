package com.practice.graph.BFS;

import java.util.ArrayList;
import java.util.Stack;

import com.practice.node.GraphNode;

/*
 * As outer loop will execute for set of vertices V. 
 * And for the inner loop it will be executed for set of edges which is "2E"in the case for undirected graph 
 * but ignoring constant so that is "E".  And the complexity is not "O(V*E)" because we are not traversing the edges 
 * again for any other vertex. So the complexity becomes time required to visit all the edges + time required to visit all the vertex.***
 * */
public class DFSAlgo {

	ArrayList<GraphNode> nodeList = new ArrayList<GraphNode>();

	public DFSAlgo(ArrayList<GraphNode> nodeList) {

		this.nodeList = nodeList;
	}

	void dfs() {
		// if a node is unvisited then run dfs on it
		for (GraphNode node : nodeList) {
			if (!node.isVisited())
			dfsVisit(node);
			dfsVisitRecursive(node);
		}
	}

	// dfs traversal by a source node
	void dfsVisit(GraphNode node) {
		// make an empty stack
		Stack<GraphNode> stack = new Stack<>();
		// add source node to stack
		stack.push(node);
		// while queue is not empty
		while (!stack.isEmpty()) {
			// pop a node from stack
			GraphNode presentNode = stack.pop();
			// mark node as visited
			presentNode.setVisited(true);
			// print the node
			System.out.print(presentNode.getName() + " ");
			// for each neighbor of present node
			for (GraphNode neighbor : presentNode.getNeighbors()) {
				// if neighbor is not visited then add it to queue
				if (!neighbor.isVisited()) {

					stack.push(neighbor);
					neighbor.setVisited(true);
				}
			}

		}
		System.out.println("\n");
	}

	// dfs traversal by a source node
	void dfsVisitRecursive(GraphNode node) {
		// mark node as visited
		node.setVisited(true);
		// print the node
		System.out.print(node.getName() + " ");
		// for each neighbor of present node
		for (GraphNode neighbor : node.getNeighbors()) {
			// if neighbor is not visited
			if (!neighbor.isVisited()) {
				// recursive call to dfs function
				dfsVisitRecursive(neighbor);
			}
		}
	}

	// add an undirected edge between two nodes
	public void addUndirectedEdge(int i, int j) {
		GraphNode first = nodeList.get(i - 1);
		GraphNode second = nodeList.get(j - 1);
		first.getNeighbors().add(second);
		second.getNeighbors().add(first);
	}

	public static void main(String[] args) {

		ArrayList<GraphNode> nodeList = new ArrayList<>();

		// create 10 nodes: v1-v10
		for (int i = 1; i < 11; i++) {
			nodeList.add(new GraphNode("V" + i));
		}

		// Constructor
		DFSAlgo graph = new DFSAlgo(nodeList);

		// add edges following graph in graph.docx
		graph.addUndirectedEdge(1, 2);
		graph.addUndirectedEdge(1, 4);
		graph.addUndirectedEdge(2, 3);
		graph.addUndirectedEdge(2, 5);
		graph.addUndirectedEdge(3, 6);
		graph.addUndirectedEdge(3, 10);
		graph.addUndirectedEdge(4, 7);
		graph.addUndirectedEdge(5, 8);
		graph.addUndirectedEdge(6, 9);
		graph.addUndirectedEdge(7, 8);
		graph.addUndirectedEdge(8, 9);
		graph.addUndirectedEdge(9, 10);

		// dfs from v1
		System.out.println("Printing DFS from source: V1");
		graph.dfs();
	}

}

/**
 * Your iterative and recursive dfs functions produce different outputs because
 * they operate differently when a node is connected to multiple nodes. To take
 * your example, 0 is connected to 1 and 2. The recursive function will call
 * dfsrecursive on 1 as it's the first node in adjacency list and thus 1 will
 * appear before 2. In the iterative version, both 1 and 2 will be pushed on the
 * stack in order. Since 2 was pushed last, it will be popped before 1. Hence, 2
 * will be printed before 1. Obviously, this change in order also affects other
 * nodes as the two algorithms diverge. I don't really see any problem with
 * this, but if it bothers you, you can try reversing the order in which you
 * push adjacent nodes to the stack. That should fix this problem.
 */
