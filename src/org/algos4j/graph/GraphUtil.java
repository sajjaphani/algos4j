package org.algos4j.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Utilities on graph data structures.
 * 
 * @author psajja
 *
 */
public class GraphUtil {

	/**
	 * Non-instatiable.
	 */
	private GraphUtil() {
	}
	
	/**
	 * Visits and prints the graph vertices in depth first order.
	 * Non-recursive version, starting vertex: '0'.
	 * 
	 * <pre>
	 * Time: O(v+e) for Adjacency list representation, 
	 *       O(v^2) for Adjacency matrix representation.
	 * </pre>
	 * 
	 * @param graph
	 * 		graph to traverse
	 * 
	 * @throws NullPointerException
	 * 		if the graph is null
	 */
	public static void dfs(IGraph graph) {
		if(graph == null)
			throw new NullPointerException("Graph can not be null.");
		
		boolean[] visited = new boolean[graph.vertices()];
		Stack<Integer> stack = new Stack<>();
		System.out.println(0);
		visited[0] = true;
		stack.push(0);
		
		while(!stack.isEmpty()) {
			int vertex = getUnvisitedVertex(graph, stack.pop(), visited);
			if(vertex != -1) {
				System.out.println(vertex);
				visited[vertex] = true;
				stack.push(vertex);
			}
		}
	}

	/**
	 * Get the un-visited adjacent vertex.
	 * 
	 * @param graph
	 * 		the graph
	 * @param visited
	 * 		vertex states 
	 * @param pop
	 * 		the vertex
	 * 
	 * @return
	 * 		first un-visited vertex or -1
	 */
	private static int getUnvisitedVertex(IGraph graph, int vertex, boolean[] visited) {
		List<Integer> adjacencies = graph.outEdges(vertex);
		for (int adj : adjacencies) {
			if (!visited[adj])
				return adj;
		}
		
		return -1;
	}
	
	/**
	 * This method orders the vertices in a directed acyclic graph
	 *  where each node comes before all nodes to which it has adjacencies.
	 *  A variant of DFS.
	 * 
	 * @param graph
	 * 		given graph
	 * 
	 * @throws NullPointerException
	 * 		if the graph is null
	 */
	public static void topologicalSort(IGraph graph) {
		if (graph == null)
			throw new NullPointerException("Graph can not be null.");

		// The graph should be directed graph
		if(!graph.isDirected())
			throw new IllegalArgumentException("Given graph should be directed graph.");
		
		int vertices = graph.vertices();
		Stack<Integer> stack = new Stack<>();

		boolean visited[] = new boolean[vertices];

		for (int vertex = 0; vertex < vertices; vertex++)
			if (visited[vertex] == false)
				topologicalSort(graph, vertex, visited, stack);

		while (!stack.empty())
			System.out.print(stack.pop() + " ");
	}

	/**
	 * Recursively visits the vertices.
	 * 
	 * @param graph
	 * 		the graph
	 * @param vertex
	 * 		current vertex
	 * @param visited
	 * 		visited states
	 * @param stack
	 * 		the stack
	 */
	private static void topologicalSort(IGraph graph, int vertex, boolean[] visited, Stack<Integer> stack) {
		visited[vertex] = true;

		for (int adj : graph.outEdges(vertex)) {
			if (!visited[adj])
				topologicalSort(graph, adj, visited, stack);
		}

		stack.push(vertex);
	}
	
	/**
	 * Do the validation.
	 * 
	 * @param graph
	 * 		given graph
	 * @param fromVertex
	 * 		from vertex
	 * @param toVertex
	 * 		to vertex
	 */
	private static void validate(IGraph graph, int fromVertex, int toVertex) {
		if (graph == null)
			throw new NullPointerException("Graph can not be null.");

		if(fromVertex < 0 || fromVertex >= graph.vertices())
			throw new IllegalArgumentException("Invalid from vertex.");
	
		if(toVertex < 0 || toVertex >= graph.vertices())
			throw new IllegalArgumentException("Invalid to vertex.");
	}
	
	/**
	 * Checks whether there is a path between fromVertex to toVertex.
	 * Based on DFS.
	 * 
	 * @param graph
	 * 		given graph
	 * @param fromVertex
	 * 		from vertex
	 * @param toVertex
	 * 		to vertex
	 * 
	 * @return
	 * 		true if there is a path, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the graph is null
	 * @throws IllegalArgumentException
	 * 		if the given vertices are outside of the range
	 */
	public static boolean hasPath(IGraph graph, int fromVertex, int toVertex) {
		validate(graph, fromVertex, toVertex);
	
		boolean[] visited = new boolean[graph.vertices()];
		hasPath(graph, fromVertex, visited);
	
		return visited[toVertex];
	}

	/**
	 * This method visits the vertices (DFS) from the given source vertex to find the path.
	 *  
	 * @param graph
	 * 		the graph
	 * @param vertex
	 * 		current vertex
	 * @param visited
	 * 		states of the vertices
	 */
	private static void hasPath(IGraph graph, int vertex, boolean[] visited) {
		visited[vertex] = true;
		for (int adj : graph.outEdges(vertex))
			if (!visited[adj])
				hasPath(graph, adj, visited);
	}
	
	/**
	 * Checks whether there is a path between fromVertex to toVertex.
	 * 
	 * @param graph
	 * 		given graph
	 * @param fromVertex
	 * 		from vertex
	 * @param toVertex
	 * 		to vertex
	 * 
	 * @return
	 * 		true if there is a path, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the graph is null
	 * @throws IllegalArgumentException
	 * 		if the given vertices are outside of the range
	 */
	static boolean hasPath1(IGraph graph, int fromVertex, int toVertex) {
		validate(graph, fromVertex, toVertex);
	
		boolean[] visited = new boolean[graph.vertices()];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(fromVertex);
		
		while (!queue.isEmpty()) {
			int u = queue.poll();
		
			Iterator<Integer> iter = graph.outEdges(u).iterator();
			while (iter.hasNext()) {
				int n = iter.next();
				if (!visited[n]) {
					if(n == toVertex)
						return true;
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Checks whether there is a path between fromVertex to toVertex.
	 * Returns the path between from and to vertices.
	 * 
	 * @param graph
	 * 		given graph
	 * @param fromVertex
	 * 		from vertex
	 * @param toVertex
	 * 		to vertex
	 * 
	 * @return
	 * 		a list containing the path sequence
	 * 
	 * @throws NullPointerException
	 * 		if the graph is null
	 * @throws IllegalArgumentException
	 * 		if the given vertices are outside of the range
	 */
	public static List<Integer> path(IGraph graph, int fromVertex, int toVertex) {
		validate(graph, fromVertex, toVertex);
		boolean[] visited = new boolean[graph.vertices()];
		int[] edgeTo = new int[graph.vertices()];

		pathTo(graph, fromVertex, visited, edgeTo);

		List<Integer> path = new ArrayList<Integer>();

		if (visited[toVertex]) {
			for (int x = toVertex; x != fromVertex; x = edgeTo[x])
				path.add(x);

			path.add(fromVertex);
			
			Collections.reverse(path);
		}
		
		return path;

	}

	/**
	 * This method visits the vertices (DFS) from the given source vertex to find the path.
	 * Updates the path in edgeTo array.
	 *  
	 * @param graph
	 * 		the graph
	 * @param vertex
	 * 		current vertex
	 * @param visited
	 * 		states of the vertices
	 */
	private static void pathTo(IGraph graph, int fromVertex, boolean[] visited, int[] edgeTo) {
		visited[fromVertex] = true;
		for (int w : graph.outEdges(fromVertex)) {
			if (!visited[w]) {
				edgeTo[w] = fromVertex;
				pathTo(graph, w, visited, edgeTo);
			}
		}
	}
	
	/**
	 * This method computes the connected components of the given graph.
	 * 
	 * @param graph
	 * 		given graph
	 * 
	 * @return 
	 * 		an array of lists containing the connected components
	 * 
	 * @throws NullPointerException
	 * 		if the graph is null
	 */
	public static List<Integer>[] connectedComponents(IGraph graph) {
		if(graph == null)
			throw new NullPointerException("Graph can not be null.");
		
		List<List<Integer>> components = new ArrayList<>();
		boolean[] visited = new boolean[graph.vertices()];
		for (int vertex = 0; vertex < graph.vertices(); vertex++) {
			if (!visited[vertex]) {
				List<Integer> component = new ArrayList<>();
				connectedComponents(graph, vertex, component, visited);
				components.add(component);
			}
		}

		@SuppressWarnings("unchecked")
		List<Integer>[] comps = new List[0];

		return components.toArray(comps);

	}

	/**
	 * Traverse (DFS) and add the vertices to current component.
	 * 
	 * @param graph
	 * 		given graph
	 * @param vertex
	 * 		current vertex
	 * @param component 
	 * 		component list
	 * @param visited 
	 * 		vertices states
	 */
	private static void connectedComponents(IGraph graph, int vertex, List<Integer> component, boolean[] visited) {
		visited[vertex] = true;
		component.add(vertex);
		for (int adj : graph.outEdges(vertex))
			if (!visited[adj])
				connectedComponents(graph, adj, component, visited);
	}
	
	/**
	 * This method checks whether there is a cycle in the given graph.
	 * <pre>Assumption: the graph has no self loops</pre>
	 * 
	 * @param graph
	 * 		given graph
	 * 
	 * @return 
	 * 		true if there is a cycle, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the graph is null
	 */
	public static boolean hasCycle(IGraph graph) {
		if(graph == null)
			throw new NullPointerException("Graph can not be null.");
		
		boolean[] visited = new boolean[graph.vertices()];
		for (int vertex = 0; vertex < graph.vertices(); vertex++) {
			if (!visited[vertex]) {
				if (hasCycle(graph, vertex, vertex, visited))
					return true;
			}
		}

		return false;
	}

	/**
	 * Traverses (DFS) and check whether there is a cycle.
	 * 
	 * @param graph
	 * 		given graph
	 * @param vertex
	 * 		current vertex
	 * @param landingVertex
	 * 		landing vertex
	 * @param visited
	 * 		states of vertices
	 */
	private static boolean hasCycle(IGraph graph, int vertex, int landingVertex, boolean[] visited) {
		visited[vertex] = true;
		for (int adj : graph.outEdges(vertex)) {
			if (!visited[adj]) {
				if (hasCycle(graph, adj, vertex, visited))
					return true;
			} else if (adj != landingVertex)
				return true;
		}

		return false;
	}
	
	/**
	 * This method checks whether the given graph is bipartite.
	 * This is also known as graph with two coloring problem.
	 * 
	 * @param graph
	 * 		given graph
	 * 
	 * @return 
	 * 		true if the graph is bipartite, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the graph is null
	 */
	public static boolean isBipartite(IGraph graph) {
		if(graph == null)
			throw new NullPointerException("Graph can not be null.");
		
		boolean[] visited = new boolean[graph.vertices()];
		boolean[] color = new boolean[graph.vertices()];
		for (int vertex = 0; vertex < graph.vertices(); vertex++) {
			if (!visited[vertex]) {
				if (!isBipartite(graph, vertex, visited, color))
					return false;
			}
		}

		return true;
	}
	
	/**
	 * Traverses (DFS) and check whether the graph can be colored with two colors.
	 * 
	 * @param graph
	 * 		given graph
	 * @param vertex
	 * 		current vertex
	 * @param landingVertex
	 * 		landing vertex
	 * @param visited
	 * 		states of vertices
	 * @param color
	 * 		color of vertices
	 */
	private static boolean isBipartite(IGraph graph, int vertex, boolean[] visited, boolean[] color) {
		visited[vertex] = true;
		for (int adj : graph.outEdges(vertex)) {
			if (!visited[adj]) {
				color[adj] = !color[vertex];
				if (!isBipartite(graph, adj, visited, color))
					return false;
			} else if (color[adj] == color[vertex])
				return false;
		}

		return true;
	}
	
	/**
	 * Given a projects and their dependencies, this method finds a build order
	 * so that all the projects can built successfully. All the project 
	 * dependencies must be built before building the project itself. This
	 * method assumes that the dependencies data is a 2d array.
	 * This is incomplete one...
	 * 
	 * @param noOfProjects
	 * 		number of projects
	 * @param dependencies
	 * 		project dependencies
	 * 
	 * @return
	 * 		array containing build order if possible
	 * 
	 * @throws NullPointerException
	 * 		if dependencies array is null or any of the entry is null
	 * @throws IllegalArgumentException
	 * 		if invalid project count or invalid dependencies data provided
	 */
	static int[] findBuildOrder(int noOfProjects, int[][] dependencies) {
		if(noOfProjects <=0 )
			throw new IllegalArgumentException("Invalid project count");
	
		if(dependencies == null)
			throw new NullPointerException("Project dependencies can not be null.");
		
		@SuppressWarnings("unused")
		IGraph graph = buildGraph(noOfProjects, dependencies);
		
		// TODO do a topological sort here
		
		return null;
	}

	/**
	 * This method builds a directed graph based on the data.
	 * 
	 * @param noOfProjects
	 * 		no of projects (vertices)
	 * @param dependencies
	 * 		dependencies (edge data)
	 * 
	 * @return
	 * 		directed graph based on dependencies
	 */
	private static IGraph buildGraph(int noOfProjects, int[][] dependencies) {
		IGraph graph = new DigraphAdjList(noOfProjects);
		for (int[] dependency : dependencies) {
			if (dependency == null)
				throw new NullPointerException("Dependency entry cannot be null.");
			if (dependency.length != 2)
				throw new IllegalArgumentException("Dependency must be 2 elements, from -> to");

			graph.addEdge(dependency[0], dependency[1]);
		}

		return graph;
	}
}