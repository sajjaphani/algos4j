package org.algos4j.stack;

/**
 * A test class to test the number of bracket reversals needed to make the parenthesis balanced.
 * 
 * @author psajja
 *
 */
public class BracketReversalsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//String braces = "}{}{}{}";
		// String braces = "()()(())";
		String braces = "]][][[[]]][[";
		
		int reversals = StackUtil.minBracketReversals(braces, '[');
		System.out.println("Input: " + braces);
		if (reversals == -1)
			System.out.println("Balancing is not possible");
		else if (reversals == 0)
			System.out.println("String is already balanced");
		else
			System.out.println(reversals + " brace reversals are needed");
	}
}
