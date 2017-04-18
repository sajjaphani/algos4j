package org.algos4j.stack;

/**
 * A test class to test whether the given string contains duplicate parenthesis or not.
 * 
 * @author psajja
 *
 */
public class DuplicateParenthesisTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String input1 = "(((A)+(B)+C*(D+C)))";
		System.out.println(input1);
		System.out.println("Has Duplicates: " + StackUtil.hasDuplicateParenthesis(input1));
		
		System.out.println();
		String input2 = "((A+C)+(B+D))";
		System.out.println(input2);
		System.out.println("Has Duplicates: " + StackUtil.hasDuplicateParenthesis(input2));
		
		System.out.println();
		String input3 = "(A)+(B)";
		System.out.println(input3);
		System.out.println("Has Duplicates: " + StackUtil.hasDuplicateParenthesis(input3));
	
	}
}
