package org.algos4j.graph;

/**
 * Test class for <code>WeightedDigraphAdjList</code>.
 * 
 * @author psajja
 *
 */
public class WeightedDigraphAdjListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IWeightedGraph graph = new WeightedDigraphAdjList(4);
		graph.addEdge(0, 0, 10);
		// Error case
		// graph.addEdge(4, 0, 20);
		// Error case
		// graph.addEdge(4, 4, 40);
		graph.addEdge(3, 0, 25);
		graph.addEdge(2, 1, 11);
		graph.addEdge(2, 3, 5);
		graph.addEdge(1, 0, 9);
		graph.addEdge(2, 0, 10);
		graph.addEdge(1, 3, 13);
		System.out.println(graph);
		System.out.println("In Edges of '0': " + graph.inEdges(0));
		System.out.println("Out Edges of '0' : " + graph.outEdges(0));
		System.out.println("Depth first traversal");
		graph.dfs(2);
		System.out.println("Breadth first traversal");
		graph.bfs(2);
	}
}
