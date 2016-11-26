package org.algos4j.graph;

import java.util.ArrayList;

/**
 * An abstract un-directed weighted graph.
 * 
 * @author psajja
 *
 */
public abstract class AbstractWeightedGraph implements IWeightedGraph {

	private final int vertices;
	private int edges;
	
	/**
	 * Initialize the graph with the given number of vertices.
	 * 
	 * @param vertices
	 * 		number of vertices
	 */
	protected AbstractWeightedGraph(int vertices) {
		if (vertices <= 0)
			throw new IllegalArgumentException("Number of vertices should be > 0");
		this.vertices = vertices;
		this.edges = 0;
	}
	
	@Override
	public int vertices() {
		return vertices;
	}
	
	@Override
	public int edges() {
		return edges;
	}
	
	/**
	 * Validates the given vertex.
	 * 
	 * @param vertex
	 * 		second vertex
	 * 
	 * @throws IllegalArgumentException
	 * 		if the given vertex is not in range
	 */
	protected void validate(int vertex) {
		if(vertex < 0 || vertex >= vertices) {
			throw new IllegalArgumentException("Invalid vertex: should be between (0 & " + (vertices -1) + ")");
		}
	}
	
	/**
	 * Increment the current number of edges by one.
	 * 
	 */
	protected void incrementEdges() {
		edges++;
	}
	
	/**
	 * Decrement the current number of edges by one.
	 * 
	 */
	protected void decrementEdges() {
		edges++;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
	
		sb.append("Vertices: " + vertices() + ", Edges: " + edges() + System.getProperty("line.separator"));
		sb.append("{");
		
		for (int v = 0; v < vertices(); v++) {
			sb.append("\t" + v + ":");
			for (Edge e : outEdges(v)) {
				sb.append(" " + e.toVertex());
			}

			if (v < vertices() - 1)
				sb.append(", ");
			sb.append(System.getProperty("line.separator"));
		}
	
		sb.append("}");
		
		return sb.toString();
	}
	
	/**
	 * 
	 * A class used to create adjacency list, which extends from ArrayList.
	 * 
	 * @author psajja
	 *
	 */
	static class EdgeList extends ArrayList<Edge> {
		private static final long serialVersionUID = 5258069540147766132L;
	}
}
