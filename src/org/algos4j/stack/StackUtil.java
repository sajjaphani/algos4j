package org.algos4j.stack;

import org.algos4j.util.StringUtil;

/**
 * Some utility methods on stack data structure.
 * 
 * @author psajja
 * 
 */
public class StackUtil {

	/**
	 * Not-instantiable.
	 */
	private StackUtil() {
	}

	/**
	 * Construct an integer stack with the given elements.
	 * 
	 * @param elements
	 * 		input array
	 * 
	 * @return
	 * 		integer stack object
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static IntStack constructStack(int[] elements) {
		if (elements == null)
			throw new NullPointerException("Input array should not be null.");
		IntStack stack = new IntStack(elements.length);
		for (int element : elements)
			stack.push(element);

		return stack;
	}

	/**
	 * Reverse the string using Stack.
	 * 
	 * @param string
	 * 		string to reverse
	 * 
	 * @return
	 * 		reversed string
	 */
	public static String reverse(String string) {

		if (StringUtil.isNullOrEmpty(string))
			return string;

		if (string.length() == 1)
			return string;

		int length = string.length();
		CharStack stack = new CharStack(length);

		for (int i = 0; i < length; i++)
			stack.push(string.charAt(i));

		char[] reversed = new char[length];

		for (int i = 0; i < length; i++)
			reversed[i] = stack.pop();

		return new String(reversed);
	}

	/**
	 * Converts the given infix representation into postfix form. This won't
	 * work for non single char operands. For simplicity, the method will work on the operators (+, -, *, / and ^).
	 * This method does not work efficiently for input which has more than one digit.
	 * 
	 * @param infix
	 * 		infix expression
	 * 
	 * @return
	 * 		postfix expression.
	 */
	public static String infixToPostfix(String infix) {
		if (StringUtil.isNullOrEmpty(infix))
			return infix;

		if (infix.length() == 1)
			return infix;
		StringBuilder postfix = new StringBuilder();
		CharStack stack = new CharStack(infix.length());

		char symbol;

		for (int i = 0; i < infix.length(); ++i) {
			symbol = infix.charAt(i);
			if (Character.isWhitespace(symbol))
				continue;
			if (Character.isLetter(symbol) || Character.isDigit(symbol))
				postfix.append(symbol);
			else if (symbol == '(') {
				stack.push(symbol);
			} else if (symbol == ')') {
				while (stack.peek() != '(') {
					postfix.append(stack.pop());
				}
				if (stack.isEmpty() || (!stack.isEmpty() && stack.peek() != '('))
					throw new IllegalArgumentException("Invalid expression: "
							+ infix);
				stack.pop();
			} else {
				while (!stack.isEmpty()
						&& getPrecedence(symbol) <= getPrecedence(stack.peek()))
					postfix.append(stack.pop());
				stack.push(symbol);
			}
		}

		while (!stack.isEmpty())
			postfix.append(stack.pop());

		return postfix.toString();
	}

	/**
	 * Get the operator precedence.
	 * 
	 * @param symbol
	 * 		given symbol, get the precedence.
	 * 
	 * @return
	 * 		returns the operator precedence
	 */
	private static int getPrecedence(char symbol) {
		if (symbol == '+' || symbol == '-')
			return 1;
		if (symbol == '*' || symbol == '/' || symbol == '%')
			return 2;
		if (symbol == '^')
			return 3;
		
		return 0;
	}

	/**
	 * This method evaluates the given postfix expression. This method assumes that the
	 * postfix expression is generates using {@link #infixToPostfix(String)}
	 * method. This method does not work efficiently for input which has more than one digit.
	 * 
	 * @param postfix
	 * 		input postfix expression
	 * 
	 * @return
	 * 		result of the given expression evaluation
	 */
	public static int evaluatePostfix(String postfix) {
		if (StringUtil.isNullOrEmpty(postfix))
			throw new IllegalArgumentException("Not a valid input.");
		IntStack stack = new IntStack(postfix.length());
		char symbol;
		for (int i = 0; i < postfix.length(); ++i) {
			symbol = postfix.charAt(i);
			if (Character.isDigit(symbol))
				stack.push(Character.getNumericValue(symbol));
			else {
				int val2 = stack.pop();
				int val1 = stack.pop();
				int result = evaluate(val1, val2, symbol);
				stack.push(result);
			}
		}
		
		return stack.pop();
	}

	/**
	 * Applies the operation on the given variables based on the symbol.
	 * 
	 * @param val1
	 * 		first value
	 * @param val2
	 * 		second value
	 * @param symbol
	 * 		operator symbol
	 * 
	 * @return
	 * 		result of the operation
	 */
	private static int evaluate(int val1, int val2, char symbol) {
		switch (symbol) {
		case '+':
			return val1 + val2;
		case '-':
			return val1 - val2;
		case '*':
			return val1 * val2;
		case '/':
			return val1 / val2;
		case '^':
			return (int) Math.pow(val1, val2);
		default:
			throw new IllegalArgumentException("Not a valid operator: "
					+ symbol);
		}
	}

	/**
	 * Evaluates the given infix expression. Expects simple integer expressions
	 * with operators (+, -, *, /).
	 * 
	 * @param infix
	 * 		given infix expression
	 * 
	 * @return
	 * 		result of the infix evaluation
	 * 
	 * @throws IllegalArgumentException
	 * 		if the input is not a valid input
	 */
	public static double evaluateExpression(String infix) {
		if (StringUtil.isNullOrEmpty(infix))
			throw new IllegalArgumentException("Input expression cannot be null or empty.");

		CharStack operatorStack = new CharStack(infix.length());
		IntStack operandStack = new IntStack(infix.length());
		char symbol;

		for (int i = 0; i < infix.length(); i++) {
			symbol = infix.charAt(i);

			if (symbol == ' ')
				continue;

			if (Character.isDigit(symbol)) {
				int start = i;
				while (i+1 < infix.length() && Character.isDigit(infix.charAt(i+1)))
					i = i + 1;	
				operandStack.push(Integer.parseInt(infix.substring(start, i+1)));
			} else if (symbol == '(')
				operatorStack.push(symbol);
			else if (symbol == ')') {
				while (operatorStack.peek() != '(')
					operandStack.push(evaluate(operandStack.pop(), operandStack.pop(), operatorStack.pop()));
				operatorStack.pop();
			} else if (isOperator(symbol)) {
				while (!operatorStack.isEmpty() && hasPrecedence(symbol, operatorStack.peek()))
					operandStack.push(evaluate(operandStack.pop(), operandStack.pop(), operatorStack.pop()));

				operatorStack.push(symbol);
			} else {
				throw new IllegalArgumentException("Invalid character found " + symbol);
			}
		}

		while (!operatorStack.isEmpty()) {
			int val1 = operandStack.pop();
			int val2 = operandStack.pop();
			operandStack.push(evaluate(val2, val1, operatorStack.pop()));
		}

		return operandStack.pop();
	}

	/**
	 * Checks whether operator 2 has higher precedence.
	 * 
	 * @param op1
	 * 		first operator
	 * @param op2
	 * 		second operator
	 * 
	 * @return
	 * 		true if second operator has higher precedence, false otherwise
	 */
	private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        return getPrecedence(op1) < getPrecedence(op2);
    }
	
	/**
	 * Check whether the given characte is an operator (+, -, *, /).
	 * 
	 * @param symbol
	 * @return
	 */
	private static boolean isOperator(char symbol) {
		return symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/';
	}

	/**
	 * Checks whether the given string has balanced parenthesis.
	 * 
	 * @param string
	 * 		input string containing the symbols
	 * 
	 * @return
	 * 		true if the string contains balanced parenthesis, false otherwise
	 */
	public static boolean hasBalencedParenthesis(String string) {
		if (StringUtil.isNullOrEmpty(string))
			return true;

		CharStack stack = new CharStack(string.length());
		char symbol;
		for (int i = 0; i < string.length(); ++i) {
			symbol = string.charAt(i);
			if (symbol == '{' || symbol == '(' || symbol == '[')
				stack.push(symbol);
			else if (symbol == '}' || symbol == ')' || symbol == ']') {
				if (stack.isEmpty() || !isMatchedPair(stack.pop(), symbol))
					return false;
			}
		}

		return stack.isEmpty();
	}

	/**
	 * Checks whether the pair of parenthesis are matching.
	 * 
	 * @param symbol1
	 * 		opening symbol
	 * @param symbol2
	 * 		closing symbol
	 * 
	 * @return
	 * 		true if the symbols matches, false otherwise
	 */
	private static boolean isMatchedPair(char symbol1, char symbol2) {
		if (symbol1 == '(' && symbol2 == ')')
			return true;
		if (symbol1 == '{' && symbol2 == '}')
			return true;
		if (symbol1 == '[' && symbol2 == ']')
			return true;

		return false;
	}

	/**
	 * Given a linked stack, it reverses the elements in the stack.
	 * This can also reverse a linked list representation of stack {@link LinkedIntStack}. 
	 * Time: O(n^2)
	 * 
	 * @param stack
	 * 		input stack
	 * 
	 * @throws NullPointerException
	 * 		if the stack is null
	 */
	public static void reverse(IntStack stack) {
		if (stack == null)
			throw new NullPointerException("Stack cannot be null.");

		if (!stack.isEmpty()) {
			int top = stack.pop();
			reverse(stack);
			moveToBottom(stack, top);
		}
	}

	/**
	 * Moves the top of the element to bottom.
	 * 
	 * @param stack
	 * 		input stack
	 * @param element
	 * 		current top element
	 */
	private static void moveToBottom(IntStack stack, int element) {
		if (stack.isEmpty())
			stack.push(element);
		else {
			int top = stack.pop();
			moveToBottom(stack, element);
			stack.push(top);
		}
	}

	/**
	 * Sort the given stack. Recursive solution. 
	 * This should also work for {@link LinkedIntStack}. 
	 * Time: O(n^2)
	 * 
	 * @param stack
	 * 		given stack
	 * 
	 * @throws NullPointerException
	 * 		if the input stack is null
	 */
	public static void sort(IntStack stack) {
		if (stack == null)
			throw new NullPointerException("Stack cannot be null.");

		if (!stack.isEmpty()) {
			int top = stack.pop();
			sort(stack);
			sortedInsert(stack, top);
		}
	}

	/**
	 * Push element in sorted order.
	 * 
	 * @param stack
	 * 		stack
	 * @param element
	 * 		element to push
	 */
	private static void sortedInsert(IntStack stack, int element) {
		if (stack.isEmpty() || element > stack.peek())
			stack.push(element);
		else {
			int top = stack.pop();
			sortedInsert(stack, element);
			stack.push(top);
		}
	}
	
	/**
	 * Sort the given stack. Iterative solution. 
	 * This should also work for {@link LinkedIntStack}. O(n^2)
	 * 
	 * @param stack
	 * 		given stack
	 * 
	 * @return
	 * 		new stack which is in sorted order
	 * 
	 * @throws NullPointerException
	 * 		if the input stack is null
	 */
	public static IntStack sortIterative(IntStack stack) {
		if (stack == null)
			throw new NullPointerException("Stack cannot be null.");
		IntStack sortedStack = new IntStack(stack.size());
		while (!stack.isEmpty()) {
		
			int temp = stack.pop();
			while (!sortedStack.isEmpty() && sortedStack.peek() > temp)
				stack.push(sortedStack.pop());
		
			sortedStack.push(temp);
		}
		
		return sortedStack;
	}
}
