package org.algos4j.stack;

import org.algos4j.stack.StackUtil;

/**
 *A test class to test infix conversion evaluation without creating intermediate postfix form.
 * 
 * @author psajja
 * 
 */
public class InfixExpressionEvaluationTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		String infix0 = "3+6/2";
		
		System.out.println("Infix: "+ infix0 + " Result: " + StackUtil.evaluateExpression(infix0));
		
		String infix1 = "1+2*5";
		
		System.out.println("Infix: "+ infix1 + " Result: " + StackUtil.evaluateExpression(infix1));
		
	}
}
