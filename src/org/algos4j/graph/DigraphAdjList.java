package org.algos4j.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A directed graph, which implemented using adjacency list.
 * 
 * @author psajja
 *
 */
public class DigraphAdjList extends AbstractGraph {

	private List<Integer>[] adjList;
	
	/**
	 * Initialize the graph with the given number of vertices.
	 * 
	 * @param vertices
	 * 		number of vertices
	 */
	public DigraphAdjList(int vertices) {
		super(vertices);
		
		adjList = new EdgeList[vertices];
		for (int i = 0; i < vertices; i++) {
			adjList[i] = new EdgeList();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.algos4j.graph.IGraph#isDirected()
	 */
	@Override
	public boolean isDirected() {
		return true;
	}
	
	/**
	 * Add an edge between fromVertex and toVertex.
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
	public void addEdge(int fromVertex, int toVertex) {
		validate(fromVertex);
		validate(toVertex);
		if (!adjList[fromVertex].contains(toVertex)) {
			incrementEdges();
			adjList[fromVertex].add(toVertex);
		}
	}
	
	/**
	 * Removes an edge between fromVertex and toVertex.
	 * Time: O(deg(fromVertex)), where deg(fromVertex) is the number of edges in vertex fromVertex.
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
		// TODO we can get the index and remove element at that index
		if (adjList[fromVertex].contains(toVertex)) {
			decrementEdges();
			adjList[fromVertex].remove(Integer.valueOf(toVertex));
		}
	}
	
	/**
	 *  Checks whether there is an edge between fromVertex and toVertex.
	 *  Time: O(deg(fromVertex)), where deg(fromVertex) is the number of edges in vertex fromVertex.
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
		return adjList[fromVertex].contains(toVertex);
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
	public List<Integer> outEdges(int vertex) {
		validate(vertex);
		return Collections.unmodifiableList(adjList[vertex]);
	}
	
	/**
	 * Returns the incoming edges which incident on the given vertex.
	 * Time: O(v+e).
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
	public List<Integer> inEdges(int vertex) {
		validate(vertex);

		List<Integer> edges = new ArrayList<Integer>();
		for (int i = 0; i < vertices(); i++)
			if (adjList[i].contains(vertex))
				edges.add(i);
		return Collections.unmodifiableList(edges);
	}
	
	/**
	 * Prints the graph nodes in depth first search traversal. 
	 * It will traverse the graph with 0 as initial vertex.
	 * Time: O(v + e)
	 */
	@Override
	public void dfs() {
		dfs(0);
	}
	
	/**
	 * Prints the graph nodes in depth first search traversal. Starts the traversal with the given vertex.
	 * Time: O(v + e)
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

		Iterator<Integer> iter = adjList[vertex].listIterator();
		while (iter.hasNext()) {
			int v = iter.next();
			if (!visited[v])
				dfs(visited, v);
		}
	}
	
	/**
	 * Prints the graph nodes in breadth first search traversal. 
	 * It will traverse the graph with 0 as initial vertex.
	 * Time: O(v + e)
	 */
	@Override
	public void bfs() {
		dfs(0);
	}
	
	/**
	 * Prints the graph nodes in breadth first search traversal. Starts the traversal with the given vertex.
	 * Time: O(v + e)
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
			int v = queue.poll();

			Iterator<Integer> iter = adjList[v].listIterator();
			while (iter.hasNext()) {
				int n = iter.next();
				if (!visited[n]) {
					System.out.println(n);
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}
}