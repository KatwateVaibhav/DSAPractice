package com.practice.graph.mst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.practice.node.UndirectedEdge;
import com.practice.node.WeightedNode;

public class Kruskal {
	ArrayList<WeightedNode> nodeList = new ArrayList<WeightedNode>();
	ArrayList<UndirectedEdge> edgeList = new ArrayList<>();

	public Kruskal(ArrayList<WeightedNode> nodeList) {
		this.nodeList = nodeList;
	}

	// Kruskal algo
	void kruskal() {
		// make disjoint sets for each node
		DisjointSet.makeSet(nodeList);

		// sort the edges in ascending order
		Comparator<UndirectedEdge> comparator = new Comparator<UndirectedEdge>() {
			@Override
			public int compare(UndirectedEdge o1, UndirectedEdge o2) {
				return o1.getWeight() - o2.getWeight();
			}
		};
		Collections.sort(edgeList, comparator);

		int cost = 0;
		for (UndirectedEdge edge : edgeList) {
			WeightedNode first = edge.getFirst();
			WeightedNode second = edge.getSecond();
			if (!DisjointSet.findSet(first).equals(DisjointSet.findSet(second))) {
				DisjointSet.union(first, second);
				cost += edge.getWeight();
				System.out.println("Taken " + edge);
			}
		}

		System.out.println("\nTotal cost of MST: " + cost);
	}

	// add a weighted undirected edge between two nodes
	public void addWeightedUndirectedEdge(int firstIndex, int secondIndex, int weight) {
		UndirectedEdge edge = new UndirectedEdge(nodeList.get(firstIndex - 1), nodeList.get(secondIndex - 1), weight);
		WeightedNode first = edge.getFirst();
		WeightedNode second = edge.getSecond();
		first.getNeighbors().add(second);
		second.getNeighbors().add(first);
		first.getWeightMap().put(second, weight);
		second.getWeightMap().put(first, weight);
		edgeList.add(edge);
	}

	public static void main(String[] args) {
		ArrayList<WeightedNode> nodeList = new ArrayList<>();

		// create 10 nodes: v1-v10
		for (int i = 0; i < 5; i++) {
			nodeList.add(new WeightedNode("" + (char) (65 + i)));
		}
		Kruskal graph = new Kruskal(nodeList);
		// add weighted edges following graph in graph.docx

		graph.addWeightedUndirectedEdge(1, 2, 10); // Add A<-> B , weight 10
		graph.addWeightedUndirectedEdge(1, 3, 20);// Add A<-> C , weight 20
		graph.addWeightedUndirectedEdge(2, 3, 30);// Add B<-> C , weight 30
		graph.addWeightedUndirectedEdge(2, 4, 5);// Add B<-> D , weight 5
		graph.addWeightedUndirectedEdge(3, 4, 15);// Add C<-> D , weight 15
		graph.addWeightedUndirectedEdge(3, 5, 6); // Add C<-> E , weight 6
		graph.addWeightedUndirectedEdge(4, 5, 8);// Add D<-> E , weight 8

		// run kruskal on graph
		System.out.println("Running Kruskal's Algo on the graph: ");
		graph.kruskal();
	}// end of method

}
