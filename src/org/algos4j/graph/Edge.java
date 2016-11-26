package org.algos4j.graph;

/**
 * Represents a weighted edge. The overridden {@link #hashCode()} and {@link #equals(Object)} implementation 
 * are based on end vertices, weight is not considered.
 * 
 * @author psajja
 *
 */
public final class Edge {
	
	private final int fromVertex;
	private final int toVertex;
	private final int weight;
	
	/**
	 * Create an edge between from and to vertices with the given weight.
	 * 
	 * @param from
	 * 		from vertex
	 * @param to
	 * 		to vertex
	 * 
	 * @param weight
	 * 		given weight
	 */
	public Edge(int from, int to, int weight) {
		this.fromVertex = from;
		this.toVertex = to;
		this.weight = weight;
	}
	
	/**
	 * Returns the starting vertex of the edge.
	 * 
	 * @return
	 * 		starting vertex
	 */
	public int fromVertex() {
		return fromVertex;
	}
	
	/**
	 * Returns the ending vertex of the edge.
	 * 
	 * @return
	 * 		starting vertex
	 */
	public int toVertex() {
		return toVertex;
	}
	
	/**
	 * Returns the weight of the edge.
	 * 
	 * @return
	 * 		edge weight
	 */
	public int getWeight() {
		return weight;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 17;
		hash = hash * 31 + fromVertex;
		hash = hash * 31 + toVertex;

		return hash;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Edge))
			return false;
		Edge p = (Edge) obj;

		return fromVertex == p.fromVertex && toVertex == p.toVertex;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + fromVertex +  "->" + toVertex + ")" + " ["  + weight + "]";
	}
}

