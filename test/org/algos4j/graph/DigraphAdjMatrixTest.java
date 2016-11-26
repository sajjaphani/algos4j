package org.algos4j.graph;

/**
 * Test class for <code>DigraphAdjMatrix</code>.
 * 
 * @author psajja
 *
 */
public class DigraphAdjMatrixTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IGraph graph = new DigraphAdjMatrix(4);
		graph.addEdge(0, 0);
		// Error case
		// graph.addEdge(4, 0);
		// Error case
		// graph.addEdge(4, 4);
		graph.addEdge(3, 0);
		graph.addEdge(2, 1);
		graph.addEdge(2, 3);
		graph.addEdge(2, 0);
		System.out.println(graph);
		System.out.println("In Edges  of '0': " + graph.inEdges(0));
		System.out.println("Out Edges  of '0': " + graph.outEdges(0));
		System.out.println("Depth first traversal");
		graph.dfs(3);
		System.out.println("Breadth first traversal");
		graph.bfs(3);
	}
}
