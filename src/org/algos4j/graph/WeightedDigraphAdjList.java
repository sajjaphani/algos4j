package org.algos4j.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A directed weighted graph, which uses adjacency list representation.
 * 
 * @author psajja
 *
 */
public class WeightedDigraphAdjList extends AbstractWeightedGraph {

	private List<Edge>[] adjList;
	
	/**
	 * Initialize the graph with the given number of vertices.
	 * 
	 * @param vertices
	 * 		number of vertices
	 */
	public WeightedDigraphAdjList(int vertices) {
		super(vertices);
		
		adjList = new EdgeList[vertices];
		for (int i = 0; i < vertices; i++) {
			adjList[i] = new EdgeList();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.algos4j.graph.IWeightedGraph#isDirected()
	 */
	@Override
	public boolean isDirected() {
		return true;
	}
	
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
	@Override
	public void addEdge(int fromVertex, int toVertex, int weight) {
		validate(fromVertex);
		validate(toVertex);
		if (!hasEdge(fromVertex, toVertex)) {
			incrementEdges();
			adjList[fromVertex].add(new Edge(fromVertex, toVertex, weight));
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
		int index = getIndex(adjList[fromVertex], fromVertex, toVertex);
		if(index != -1) {
			decrementEdges();
			adjList[fromVertex].remove(index);
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
		return getIndex(adjList[fromVertex], fromVertex, toVertex) != -1;
	}
	
	/**
	 * Iterates over the edge list and returns the index of the edge given by fromVertex and toVertex.
	 * This method depends on {@link ArrayList#iterator()}.
	 * 
	 * @param list
	 * 		adjacency list
	 * @param fromVertex
	 * 		from vertex
	 * @param toVertex
	 * 		to vertex
	 * 
	 * @return
	 * 		index if found, -1 for non existing edges
	 */
	private int getIndex(List<Edge> list, int fromVertex, int toVertex) {
		Iterator<Edge> iterator = list.iterator();
		Edge current = null;
		int index = 0;
		while (iterator.hasNext()) {
			current = iterator.next();
			if (current.fromVertex() == fromVertex && current.toVertex() == toVertex)
				return index;
			index++;
		}
		
		return -1;
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
	public List<Edge> inEdges(int vertex) {
		validate(vertex);

		List<Edge> edges = new ArrayList<Edge>();
		int index = -1;
		for (int i = 0; i < vertices(); i++) {
			index = getIndex(adjList[i], i, vertex);
			if (index != -1)
				edges.add(adjList[i].get(index));
		}

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

		Iterator<Edge> iter = adjList[vertex].listIterator();
		while (iter.hasNext()) {
			int v = iter.next().toVertex();
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
		bfs(0);
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

			Iterator<Edge> iter = adjList[v].listIterator();
			while (iter.hasNext()) {
				int n = iter.next().toVertex();
				if (!visited[n]) {
					System.out.println(n);
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}
}