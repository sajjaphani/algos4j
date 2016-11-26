package org.algos4j.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A directed weighted graph, which uses adjacency matrix representation.
 * 
 * @author psajja
 *
 */
public class WeightedDigraphAdjMatrix extends AbstractWeightedGraph {
	
	private Edge[][] adjMatrix;
	
	/**
	 * Initialize the graph with the given number of vertices.
	 * 
	 * @param vertices
	 * 		number of vertices
	 */
	public WeightedDigraphAdjMatrix(int vertices) {
		super(vertices);
		this.adjMatrix = new Edge[vertices][vertices];
	}
	
	/**
	 * Add an edge between fromVertex and toVertex with the given weight.
	 * 
	 * @param fromVertex
	 * 		from vertex
	 * @param toVertex
	 * 		to vertex
	 * @param weight
	 * 		edge weight
	 * 
	 * @throws IllegalArgumentException
	 * 		if the vertices are outside of the permitted range
	 */
	@Override
	public void addEdge(int fromVertex, int toVertex, int weight) {
		validate(fromVertex);
		validate(toVertex);
		if (adjMatrix[fromVertex][toVertex] == null) {
			incrementEdges();
			adjMatrix[fromVertex][toVertex] = new Edge(fromVertex, toVertex, weight);
		}
	}
	
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
	@Override
	public void removeEdge(int fromVertex, int toVertex) {
		validate(fromVertex);
		validate(toVertex);
		if (adjMatrix[fromVertex][toVertex] != null) {
			decrementEdges();
			adjMatrix[fromVertex][toVertex] = null;
		}
	}
	
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
	@Override
	public boolean hasEdge(int fromVertex, int toVertex) {
		validate(fromVertex);
		validate(toVertex);
		return adjMatrix[fromVertex][toVertex] != null;
	}
	
	/**
	 * Returns the outgoing edges which happens from the given vertex.
	 * These are the adjacencies to the given vertex.
	 * The returned list cannot be modified.
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
	@Override
	public List<Edge> outEdges(int vertex) {
		validate(vertex);
		List<Edge> edges = new ArrayList<Edge>();
		
		for (int i = 0; i < vertices(); i++)
			if (adjMatrix[vertex][i] != null)
				edges.add(adjMatrix[vertex][i]);
		
		return Collections.unmodifiableList(edges);
	}
	
	/**
	 * Returns the incoming edges which incident on the given vertex.
	 * The returned list cannot be modified.
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
	@Override
	public List<Edge> inEdges(int vertex) {
		validate(vertex);
		List<Edge> edges = new ArrayList<Edge>();
		
		for (int i = 0; i < vertices(); i++)
			if (adjMatrix[i][vertex] != null)
				edges.add(adjMatrix[i][vertex]);
		
		return Collections.unmodifiableList(edges);
	}
	
	/**
	 * Prints the graph nodes in depth first search traversal. 
	 * It will traverse the graph with 0 as initial vertex.
	 * Time: O(v^2)
	 */
	@Override
	public void dfs() {
		dfs(0);
	}
	
	/**
	 * Prints the graph nodes in depth first search traversal. Starts the traversal with the given vertex.
	 * Time: O(v^2)
	 * 
	 * @param vertex
	 * 		starting vertex.
	 */
	@Override
	public void dfs(int vertex) {
		validate(vertex);
		boolean[] visited = new boolean[vertices()];
		dfs(visited, vertex);
	}
	
	/**
	 * Recursively visit all the vertices.
	 * 
	 * @param visited
	 * 		array to mark visited vertices
	 * @param vertex
	 * 		current vertex to visit
	 */
	private void dfs(boolean[] visited, int vertex) {
		System.out.println(vertex);
		visited[vertex] = true;
		for(int v = 0; v < vertices() ; v++)
			if(!visited[v] && adjMatrix[vertex][v] != null)
				dfs(visited, v);
	}
	
	/**
	 * Prints the graph nodes in breadth first search traversal. 
	 * It will traverse the graph with 0 as initial vertex.
	 * Time: O(v^2)
	 */
	@Override
	public void bfs() {
		bfs(0);
	}
	
	/**
	 * Prints the graph nodes in breadth first search traversal. Starts the traversal with the given vertex.
	 * Time: O(v^2)
	 * 
	 * @param vertex
	 * 		starting vertex.
	 */
	@Override
	public void bfs(int vertex) {
		validate(vertex);
	
		boolean[] visited = new boolean[vertices()];
		Queue<Integer> queue = new LinkedList<>();
		
		System.out.println(vertex);
		
		visited[vertex] = true;
		queue.add(vertex);

		while (!queue.isEmpty()) {
			int v = queue.remove();
			for (int i = 0; i < vertices(); i++) {
				if (adjMatrix[v][i] != null && visited[i] == false) {
					System.out.println(i);
					visited[i] = true;
					queue.add(i);
				}
			}
		}
	}
}
