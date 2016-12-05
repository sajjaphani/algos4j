package org.algos4j.stack;

/**
 * A test class to test the valid parenthesis length.
 * 
 * @author psajja
 *
 */
public class FindValidParenthesisLengthTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String paren1 = "(())())";
		System.out.println("For: " + paren1 + ", Valid Length: " + StackUtil.maxValidParenLength(paren1));
		
		String paren2 = ")(())()(())";
		System.out.println("For: " + paren2 + ", Valid Length: " + StackUtil.maxValidParenLength(paren2));
	}

}
