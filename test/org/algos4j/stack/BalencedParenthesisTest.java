package org.algos4j.stack;

/**
 * A test class to test whether the given string contains parenthesis is balanced or not.
 * 
 * @author psajja
 *
 */
public class BalencedParenthesisTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String input1 = "[][](([])){}";
		if(StackUtil.hasBalencedParenthesis(input1))
			System.out.println("Input " + input1 + " is balanced");
		else
			System.out.println("Input " + input1 + " is no balanced");
		
		String input2 = "[][](([{])){}";
		if(StackUtil.hasBalencedParenthesis(input2))
			System.out.println("Input " + input2 + " is balanced");
		else
			System.out.println("Input " + input2 + " is no balanced");
	}

}
