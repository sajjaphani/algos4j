package org.algos4j.stack;

import org.algos4j.stack.StackUtil;

/**
 *A test class to test infix to postfix conversion.
 * 
 * @author psajja
 * 
 */
public class PostfixEvaluationTest {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		String infix0 = "3+6/2";
		String postfix0 = StackUtil.infixToPostfix(infix0);
		
		System.out.println("Infix: "+ infix0 + " Postfix: " + postfix0 + " Result: " + StackUtil.evaluatePostfix(postfix0));
		
		String infix1 = "1+2*5";
		String postfix1 = StackUtil.infixToPostfix(infix1);
		
		System.out.println("Infix: "+ infix1 + " Postfix: " + postfix1 + " Result: " + StackUtil.evaluatePostfix(postfix1));
		
	}
}
