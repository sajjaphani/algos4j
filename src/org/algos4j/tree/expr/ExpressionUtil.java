package org.algos4j.tree.expr;

import java.util.Stack;

import org.algos4j.util.StringUtil;

/**
 * 
 * @author psajja
 *
 */
public class ExpressionUtil {

	/**
	 * Not-instantiable
	 */
	private ExpressionUtil() {
	}
	
	/**
	 * Given an infix expression, this method constructs an expression tree.
	 * Expects simple integer expressions with operators (+, -, *, /).
	 * 
	 * @param infix
	 * 		given infix expression
	 * 
	 * @return
	 * 		the constructed expression 
	 * 
	 * @throws IllegalArgumentException
	 * 		if the input is not a valid input
	 */
	public static Expression constructExpression(String infix) {
		if(StringUtil.isNullOrEmpty(infix))
			return null;
		
		Stack<Character> operatorStack = new Stack<>();
		Stack<Expression> operandStack = new Stack<>();
	
		char symbol;

		for (int i = 0; i < infix.length(); i++) {
			symbol = infix.charAt(i);

			if (symbol == ' ')
				continue;

			if (Character.isDigit(symbol)) {
				int start = i;
				while (i+1 < infix.length() && Character.isDigit(infix.charAt(i+1)))
					i = i + 1;	
				operandStack.push(new Value(Integer.parseInt(infix.substring(start, i+1))));
			} else if (symbol == '(')
				operatorStack.push(symbol);
			else if (symbol == ')') {
				while (operatorStack.peek() != '(')
					operandStack.push(getExpression(operandStack.pop(), operandStack.pop(), operatorStack.pop()));
				operatorStack.pop();
			} else if (isOperator(symbol)) {
				while (!operatorStack.isEmpty() && hasHigherPrecedence(symbol, operatorStack.peek()))
					operandStack.push(getExpression(operandStack.pop(), operandStack.pop(), operatorStack.pop()));

				operatorStack.push(symbol);
			} else {
				throw new IllegalArgumentException("Invalid character found " + symbol);
			}
		}

		while (!operatorStack.isEmpty()) {
			Expression val1 = operandStack.pop();
			Expression val2 = operandStack.pop();
			operandStack.push(getExpression(val2, val1, operatorStack.pop()));
		}

		return operandStack.pop();
	}

	/**
	 * Checks whether 'op2' has higher precedence than 1op1'.
	 * 
	 * @param op1
	 * 		first operator
	 * @param op2
	 * 		second operator
	 * 
	 * @return
	 * 		true if second operator has higher precedence, false otherwise
	 */
	private static boolean hasHigherPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        return getPrecedence(op1) < getPrecedence(op2);
    }
	
	/**
	 * Check whether the given characte is an operator (+, -, *, /).
	 * 
	 * @param symbol
	 * 		given character
	 * 
	 * @return
	 * 		true if it is one of +, -, * or /
	 */
	private static boolean isOperator(char symbol) {
		return symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/';
	}
	
	/**
	 * Get the operator precedence.
	 * 
	 * @param symbol
	 * 		given symbol
	 * 
	 * @return
	 * 		returns the operator precedence
	 */
	private static int getPrecedence(char symbol) {
		if (symbol == '+' || symbol == '-')
			return 1;
		if (symbol == '*' || symbol == '/')
			return 2;
		
		return 0;
	}
	
	/**
	 * Applies the operation on the given expressions and constructs a new expression.
	 * 
	 * @param val1
	 * 		first expression
	 * @param val2
	 * 		second expression
	 * @param symbol
	 * 		operator symbol
	 * 
	 * @return
	 * 		resulting expression
	 */
	private static Expression getExpression(Expression val1, Expression val2, char symbol) {
		switch (symbol) {
		case '+':
			return new Plus(val1, val2);
		case '-':
			return new Minus(val1, val2);
		case '*':
			return new Multiply(val1, val2);
		case '/':
			return new Divide(val1, val2);
		default:
			throw new IllegalArgumentException("Not a valid operator: "	+ symbol);
		}
	}
}
