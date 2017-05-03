package org.algos4j.tree.expr;

/**
 * 
 * @author psajja
 *
 */
public class Value implements Expression {
	
	private int value;
	
	public Value(int value) {
		this.value = value;
	}
	
	@Override
	public int evaluate() {
		return value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
}
