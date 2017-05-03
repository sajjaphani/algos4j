package org.algos4j.tree.expr;

/**
 * 
 * @author psajja
 *
 */
public class Plus implements Expression {

	private Expression left;
	private Expression right;
	
	public Plus(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public int evaluate() {
		return left.evaluate() + right.evaluate();
	}

	@Override
	public String toString() {
		return "(" + left.toString() + " + " + right.toString() + ")";
	}
}