package org.algos4j.graph;

import java.util.List;

/**
 * Interface defines a weighted graph.
 * Graph traversals may not visit all the nodes if there is no connectivity between some nodes.
 * Particular implementations has to take care if it is required to visit all the nodes.
 * 
 * @author psajja
 *
 */
public interface IWeightedGraph {
	
	/**
	 * Returns the number of vertices in the graph.
	 * 
	 * @return
	 * 		number of vertices
	 */
	public int vertices();
	
	/**
	 * Returns the number of edges in the graph.
	 * 
	 * @return
	 * 		number of edges
	 */
	public int edges();
	
	/**
	 * Indicates whether this graph is a directed graph.
	 * 
	 * @return
	 * 		true if it is directed graph, false otherwise
	 */
	public boolean isDirected();
	
	/**
	 * Add an edge between fromVertex and toVertex with the given weight.
	 * 
	 * @param fromVertex
	 * 		from vertex
	 * @param toVertex
	 * 		to vertex
	 * @param weight
	 * 		given weight
	 * 
	 * @throws IllegalArgumentException
	 * 		if the vertices are outside of the permitted range
	 */
	public void addEdge(int fromVertex, int toVertex, int weight);
	
	/**
	 * Removes an edge between fromVertex and toVertex.
	 * 
	 * @param fromVertex
	 * 		from vertex
	 * @param toVertex
	 * 		to vertex
	 * 
	 * @throws IllegalArgumentException
	 * 		if the vertices are outside of the permitted range
	 */
	public void removeEdge(int fromVertex, int toVertex);
	
	/**
	 *  Checks whether there is an edge between fromVertex and toVertex.
	 * 
	 * @param fromVertex
	 * 		from vertex
	 * @param toVertex
	 * 		to vertex
	 * 
	 * @return
	 * 		true if there is an edge, false otherwise
	 * 
	 * @throws IllegalArgumentException
	 * 		if the vertices are outside of the permitted range
	 */
	public boolean hasEdge(int fromVertex, int toVertex);
	
	/**
	 * Returns the outgoing edges which happens from the given vertex.
	 * These are the adjacencies to the given vertex.
	 * 
	 * @param vertex
	 * 		given vertex
	 * 
	 * @return
	 * 		list of edges
	 * 
	 * @throws IllegalArgumentException
	 * 		if the vertex is outside of the permitted range
	 */
	public List<Edge> outEdges(int vertex);
	
	/**
	 * Returns the incoming edges which incident on the given vertex.
	 * 
	 * @param vertex
	 * 		given vertex
	 * 
	 * @return
	 * 		list of edges
	 * 
	 * @throws IllegalArgumentException
	 * 		if the vertex is outside of the permitted range
	 */
	public List<Edge> inEdges(int vertex);
	
	/**
	 * Prints the graph nodes in depth first search traversal. 
	 * The start vertex is implementation dependent.
	 * 
	 */
	public void dfs();
	
	/**
	 * Prints the graph nodes in depth first search traversal. 
	 * Starts the traversal with the given vertex.
	 * 
	 * @param vertex
	 * 		starting vertex.
	 */
	public void dfs(int vertex);
	
	/**
	 * Prints the graph nodes in breadth first search traversal. 
	 * Start vertex is implementation dependent.
	 * 
	 */
	public void bfs();
	
	/**
	 * Prints the graph nodes in breadth first search traversal. 
	 * Starts the traversal with the given vertex.
	 * 
	 * @param vertex
	 * 		starting vertex.
	 */
	public void bfs(int vertex);
}
