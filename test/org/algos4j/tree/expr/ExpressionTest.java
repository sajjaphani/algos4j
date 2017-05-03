package org.algos4j.tree.expr;

/**
 * @author psajja
 *
 */
public class ExpressionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Expression expression = new Multiply(new Divide(new Value(15), new Value(5)),
				new Plus(new Value(3), new Value(4)));

		System.out.println("Expression: " + expression);
		System.out.println("Result: " + expression.evaluate());
	}
}
