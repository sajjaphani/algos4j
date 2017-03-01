package org.algos4j.graph.ext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * We are in a situation where building projects require other dependent project
 * to be build prior. Given a set of projects and their dependencies, this class
 * will identify if there is a build order that is possible based on the given
 * data. It is topological sort. Time: O (P + D), P is no of projects and D is
 * the number of dependent pairs.
 * 
 * @author psajja
 *
 */
public class BuildOrderProcessor {

	/**
	 * Given projects and their dependencies, this method retuns a stack of project .
	 * 
	 * @param projects
	 * 		the projects
	 * @param dependencies
	 * 		project dependencies.
	 * 
	 * @return
	 * 		stack of the projects 
	 */
	Stack<Project> findBuildOrder(String[] projects, String[][] dependencies) {
		Graph graph = buildGraph(projects, dependencies);
	
		return findBuildOrder(graph.getNodes());
	}
	
	/**
	 * Given graph nodes (projects), this method finds the build order.
	 * 
	 * @param projects
	 * 		list of projects
	 * 
	 * @return
	 * 		a stack of projects
	 */
	private Stack<Project> findBuildOrder(List<Project> projects) {
		Stack<Project> stack = new Stack<Project>();
		for (Project project : projects) {
			if (project.getState() == Project.State.NOTSTARTED) {
				if (!findBuildOrder(project, stack))
					return null;
			}
		}
		
		return stack;
	}

	/**
	 * This method traverses the graph with DFS  and updates the build order.
	 * 
	 * @param project
	 * 		current project being built
	 * @param stack
	 * 		the stack with build order
	 * 
	 * @return
	 * 		true if there is build order possible, false otherwise
	 */
	private boolean findBuildOrder(Project project, Stack<Project> stack) {
		 // There is a cycle 
		if (project.getState() == Project.State.PARTIAL)
			return false;

		if (project.getState() == Project.State.NOTSTARTED) {
			project.setState(Project.State.PARTIAL);
			List<Project> children = project.getChildren();
			for (Project child : children) {
				if (!findBuildOrder(child, stack))
					return false;
			}
			
			project.setState(Project.State.COMPLETE);
			stack.push(project);
		}
		
		return true;
	}
	
	/**
	 * Build project dependency graph.
	 * 
	 * @param projects
	 * 		given projects
	 * @param dependencies
	 * 		projects dependencies
	 * 
	 * @return
	 * 		graph
	 */
	private Graph buildGraph(String[] projects, String[][] dependencies) {
		Graph graph = new Graph();

		for (String[] dependency : dependencies) {
			String first = dependency[0];
			String second = dependency[1];
			graph.addEdge(first, second);
		}

		return graph;
	}
	
	/**
	 * Represents a project to be built, along with the dependency.
	 * This represents a node in the graph.
	 * 
	 * @author psajja
	 *
	 */
	static class Project {

		public enum State {
			COMPLETE, PARTIAL, NOTSTARTED
		};

		private List<Project> children;
		private Map<String, Project> map;
		private String name;

		private State state;

		/**
		 * Initialize the project with the given name.
		 * 
		 * @param name
		 *    	name of the project
		 */
		public Project(String name) {
			this.name = name;
			this.children = new ArrayList<Project>();
			this.map = new HashMap<String, Project>();
			this.state = State.NOTSTARTED;
		}

		/**
		 * Get the name of this project.
		 * 
		 * @return
		 * 		the project name
		 */
		public String getName() {
			return name;
		}

		/**
		 * Get the projects that depends on this project.
		 * 
		 * @return
		 * 		the dependent projects
		 */
		public List<Project> getChildren() {
			return children;
		}

		/**
		 * Add a dependent project.
		 * 
		 * @param project
		 * 		dependent project
		 */
		public void addNeighbor(Project project) {
			if (!map.containsKey(project.getName())) {
				children.add(project);
				map.put(project.getName(), project);
			}
		}

		/**
		 * Returns the current state of this project.
		 * 
		 * @return the state
		 */
		public State getState() {
			return state;
		}

		/**
		 * Set the project build status.
		 * 
		 * @param status
		 *            the build status
		 */
		public void setState(State status) {
			state = status;
		}
	}
	
	/**
	 * Represents a directed graph of projects and their dependencies.
	 * 
	 * @author psajja
	 *
	 */
	public class Graph {
		private List<Project> nodes = new ArrayList<Project>();
		private HashMap<String, Project> map = new HashMap<String, Project>();

		/**
		 * Get the project. It creates if one does not exist.
		 * 
		 * @param name
		 * 		given project name
		 * 
		 * @return
		 * 		the project with the name
		 */
		public Project getProjectNode(String name) {
			if (!map.containsKey(name)) {
				Project node = new Project(name);
				nodes.add(node);
				map.put(name, node);
			}

			return map.get(name);
		}

		/**
		 * Add an edge between two projects.
		 * 
		 * @param startName
		 * 		from project name
		 * @param endName
		 * 		to project name
		 */
		public void addEdge(String startName, String endName) {
			Project start = getProjectNode(startName);
			Project end = getProjectNode(endName);
			start.addNeighbor(end);
		}

		/**
		 * Get all the nodes of this graph.
		 * 
		 * @return
		 * 		list of project nodes
		 */
		public List<Project> getNodes() {
			return nodes;
		}
	}
}