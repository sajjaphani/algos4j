package org.algos4j.stack;

import org.algos4j.stack.StackUtil;

/**
 *A test class to test infix to postfix conversion.
 * 
 * @author psajja
 * 
 */
public class InfixPostfixTest {

	public static void main(String[] args) {
		System.out.println("Infix: " + "a + b * c");
		System.out.println("Postfix: " + StackUtil.infixToPostfix("a + b * c"));
		
		System.out.println("Infix: " + "5 * 6");
		System.out.println("Postfix: " + StackUtil.infixToPostfix("5 * 6"));
		
	}
}
