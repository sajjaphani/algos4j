package org.algos4j.graph;

/**
 * Test class for <code>GraphAdjMatrix</code>.
 * 
 * @author psajja
 *
 */
public class GraphAdjMatrixTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IGraph graph = new GraphAdjMatrix(4);
		graph.addEdge(0, 0);
		// Error case
		// graph.addEdge(4, 0);
		// Error case
		// graph.addEdge(4, 4);
		graph.addEdge(2, 3);
		graph.addEdge(3, 0);
		graph.addEdge(2, 0);
		graph.addEdge(2, 1);
		System.out.println(graph);
		System.out.println("In Edges  of '0': " + graph.inEdges(0));
		System.out.println("Out Edges  of '0': " + graph.outEdges(0));
		System.out.println("Depth first traversal");
		graph.dfs();
		System.out.println("Breadth first traversal");
		graph.bfs();
	}
}
