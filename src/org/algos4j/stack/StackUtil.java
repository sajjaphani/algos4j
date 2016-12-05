package org.algos4j.stack;

import java.util.Arrays;

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
	
	/**
	 * Given an array, it computes the next greater element
	 * for each of the input element.
	 * 
	 * @param elements
	 * 		input array
	 * 
	 * @return 
	 * 		array containing the next greater elements
	 * 
	 * @throws NullPointerException
	 * 		if the given array is null
	 */
	public static int[] computeNextGreaterElements(int[] elements) {
		if (elements == null || elements.length == 0)
			throw new NullPointerException(
					"Input array should not be null or empty");

		int[] nextElements = new int[elements.length];
		if(elements.length == 0)
			return nextElements;
		
		IntStack stack = new IntStack(elements.length);

		stack.push(0);
		int index, next;

		for (int i = 1; i < elements.length; i++) {
			next = elements[i];
			if (!stack.isEmpty()) {
				index = stack.pop();
				while (elements[index] < next) {
					nextElements[index] = next;
					if (stack.isEmpty())
						break;
					index = stack.pop();
				}
				if (elements[index] > next)
					stack.push(index);
			}
			stack.push(i);
		}

		while (!stack.isEmpty())
			nextElements[stack.pop()] = -1;
		
		return nextElements;
	}
	
	/**
	 * The stock span problem is where we have a series of n daily price quotes for a stock 
	 * and we need to calculate span of stock’s price for all n days. 
	 * Computes in linear time with additional n space.
	 * <p>
	 * The span Si of the stock’s price on a given day 'i' is defined as the
	 * maximum number of consecutive days just before the given day, for which
	 * the price of the stock on the current day is less than or equal to its
	 * price on the given day.
	 * </p>
	 * 
	 * @param price
	 * 		input price array
	 * @return
	 * 		the span array for each day
	 *  
	 * @throws NullPointerException
	 * 		if the given array is null
	 */
	public static int[] computeStockSpan(int[] price) {
		if (price == null)
			throw new NullPointerException("Price array cannot be null.");
		if (price.length == 0)
			return new int[0];

		int[] span = new int[price.length];
	
		IntStack stack = new IntStack(price.length);
		stack.push(0);
	
		span[0] = 1;

		for (int i = 1; i < price.length; i++) {

			while (!stack.isEmpty() && price[stack.peek()] <= price[i])
				stack.pop();

			span[i] = (stack.isEmpty()) ? (i + 1) : (i - stack.peek());

			stack.push(i);
		}

		return span;
	}
	
	/**
	 * Given a string containing only opening and closing parenthesis ('(' and ')'), find the
	 * maximum valid length based on matching sequence.
	 * 
	 * @param string
	 * 		input string
	 * 
	 * @return
	 * 		the valid length
	 */
	public static int maxValidParenLength(String string) {
		if (string == null)
			throw new NullPointerException("Input string cannot be null.");
		if (string.length() == 0)
			return 0;

		int n = string.length();

		IntStack stack = new IntStack(string.length());
		stack.push(-1);

		int result = 0;

		for (int i = 0; i < n; i++) {
			if (string.charAt(i) == '(')
				stack.push(i);
			else if (string.charAt(i) == ')') {
				stack.pop();
				if (!stack.isEmpty())
					result = Math.max(result, i - stack.peek());
				else
					stack.push(i);
			} else {
				throw new IllegalArgumentException("Invalid character found: " + string.charAt(i));
			}
		}

		return result;
	}
	
	/**
	 * Given a string containing a combination of opening ('{' or '[' or '(') and closing
	 * ('}' or ']' or ')') braces only, find the number of minimal parenthesis reversal to make it
	 * balanced.
	 * 
	 * @param string
	 * 		input string
	 * @param brace
	 * 		brace to be used, '{' or '[' or '('
	 * 
	 * @return
	 * 		number of reversals needed, -1 if not possible, 0 if for already balanced
	 */
	public static int minBracketReversals(String string, char brace) {
		if (string == null)
			throw new NullPointerException("Input string should not be null");
		if (brace != '{' && brace != '[' && brace != '(')
			throw new IllegalArgumentException("Not a valid brace: " + brace);
		int length = string.length();
		if (length == 0)
			return 0;

		// We cannot make odd length to be balanced
		if (length % 2 != 0)
			return -1;

		char closeBrace = getClosingBrace(brace);

		CharStack stack = new CharStack(length);
		for (int i = 0; i < length; i++) {
			if (string.charAt(i) == closeBrace && !stack.isEmpty()) {
				if (stack.peek() == brace)
					stack.pop();
				else
					stack.push(string.charAt(i));
			} else if (string.charAt(i) == brace || string.charAt(i) == closeBrace) {
				stack.push(string.charAt(i));
			} else {
				throw new IllegalArgumentException("Input string contains an illegal character: " + string.charAt(i));
			}
		}

		// Remaining length
		int reducedLength = stack.size();

		// Opening brackets at the end 
		int count = 0;
		while (!stack.isEmpty() && stack.peek() == brace) {
			stack.pop();
			count++;
		}

		return (reducedLength / 2 + count % 2);
	}
	
	/**
	 * Get the corresponding closing brace.
	 * 
	 * @param brace
	 * 		opening brace
	 * 
	 * @return
	 * 		closing brace
	 */
	private static char getClosingBrace(char brace) {
		if (brace == '{')
			return '}';
		if (brace == '[')
			return ']';
		if (brace == '(')
			return ')';
		return 0;
	}

	/**
	 * Given a histogram of n bars, finds the maximum rectangle area.
	 * 
	 * @param histogram
	 * 		given histogram
	 * 
	 * @return
	 * 		the maximum area
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static int getMaxRectangleArea(int[] histogram) {
	
		if (histogram == null)
			throw new NullPointerException("Input array should not be null.");

		// stack holds the indices of histogram, which are always in increasing order of their heights.
		IntStack stack = new IntStack(histogram.length);

		int top;
		int currentMaxArea;
		int maxArea = 0;

		int index = 0;
		while (index < histogram.length) {

			// Push the current bar is it higher than the bar on top
			if (stack.isEmpty() || histogram[stack.peek()] <= histogram[index])
				stack.push(index++);
			else {
				top = stack.pop();

				// Calculate the area with histogram[top] stack as smallest bar
				currentMaxArea = histogram[top]	* (stack.isEmpty() ? index : index - stack.peek() - 1);

				maxArea = Math.max(maxArea, currentMaxArea);
			}
		}

		// For the remaining bars on the stack, calculate the area
		while (stack.isEmpty() == false) {
			top = stack.pop();
		
			currentMaxArea = histogram[top]	* (stack.isEmpty() ? index : index - stack.peek() - 1);

			maxArea = Math.max(maxArea, currentMaxArea);
		}

		return maxArea;
	}
	
	/**
	 * Find the maximum of minimum for every window size in a given array.
	 * For every window of size from 1 to n, we  need to first compute 
	 * the window mins and finally we need to compute the max of these mins.
	 * 
	 * Time: O(n), Space: O(n)
	 * 
	 * @param array
	 *            input array
	 *            
	 * @return array contains the sizes
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static int[] maxOfMinWindow(int[] array) {
		if (array == null)
			throw new NullPointerException("Input array cannot be null.");

		int n = array.length;

		int[] result = new int[n + 1];

		int[] left = new int[n + 1];
		int[] right = new int[n + 1];

		IntStack stack = new IntStack(n);

		for (int i = 0; i < n; i++) {
			left[i] = -1;
			right[i] = n;
		}

		// fill left such that left[i] holds the max till
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && array[stack.peek()] >= array[i])
				stack.pop();

			if (!stack.isEmpty())
				left[i] = stack.peek();

			stack.push(i);
		}

		while (!stack.isEmpty())
			stack.pop();

		// fill right such that right[i] holds the max
		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && array[stack.peek()] >= array[i])
				stack.pop();

			if (!stack.isEmpty())
				right[i] = stack.peek();

			stack.push(i);
		}

		for (int i = 0; i < n; i++) {
			int len = right[i] - left[i] - 1;
			result[len] = Math.max(result[len], array[i]);
		}

		for (int i = n - 1; i >= 1; i--)
			result[i] = Math.max(result[i], result[i + 1]);

		return Arrays.copyOfRange(result, 1, result.length);
	}
}