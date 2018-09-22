/*
* Name: Hongyu Dai (Nelson) 
* ID:	V00815253
* Date: 2016-02-27
* Filename: ArithExpression.java
* Details: CSC115 Assignment 3
* This class is the engine of the calculator
*/ 

import java.util.regex.Pattern;
import java.util.Scanner;
import javax.swing.plaf.basic.DefaultMenuLayout;
import java.util.regex.Matcher;


public class ArithExpression {

	private TokenList postfixTokens;
	private TokenList infixTokens;


	public ArithExpression(String word) {
		if (Tools.isBalancedBy("()", word)) {
			tokenizeInfix(word);
			infixToPostfix();
		} else {
			throw new InvalidExpressionException("Parentheses unbalanced");
		}
	}

	
	private void tokenizeInfix(String express) {
		infixTokens = new TokenList(express.length());
		Pattern opParenPattern = Pattern.compile("[-+*/^()]");
		Matcher opMatcher = opParenPattern.matcher(express);

		String matchedBit, nonMatchedBit;
		int lastNonMatchIndex = 0;
		String lastMatch = "";
		while (opMatcher.find()) {
			matchedBit = opMatcher.group();
			nonMatchedBit = express.substring(lastNonMatchIndex, opMatcher.start());
			nonMatchedBit = nonMatchedBit.trim();
			if (matchedBit.charAt(0) == '-') {
				if (opMatcher.start() == 0 || !lastMatch.equals(")") && nonMatchedBit.equals("")) {
					continue;
				}
			}
			if (nonMatchedBit.length() != 0) {
				infixTokens.append(nonMatchedBit);
			}
			lastNonMatchIndex = opMatcher.end();
			infixTokens.append(matchedBit);
			lastMatch = matchedBit;
		}
		if (lastNonMatchIndex < express.length()) {
			nonMatchedBit = express.substring(lastNonMatchIndex, express.length());
			nonMatchedBit = nonMatchedBit.trim();
			infixTokens.append(nonMatchedBit);
		}
	}

	public static boolean isOperator(String op) {
		switch (op) {
		case "+":
		case "-":
		case "/":
		case "*":
		case "^":
			return true;
		default:
			return false;
		}
	}

	
	
	
	
	/*
	 * A helper method to determing the percedence of operaters
	 */		
		private boolean precedence(String stacktop, String currentOp) {
		switch (currentOp) {
		case "+":
		case "-":
			switch (stacktop) {
			case "+":
			case "-":
			case "*":
			case "/":
			case "^":
				return false;
			default:
			}
		case "*":
		case "/":
			switch (stacktop) {
			case "+":
			case "-":
				return true;
			case "*":
			case "/":
			case "^":
				return false;
			default:
			}
		case "^":
			switch (stacktop) {
			case "+":
			case "-":
			case "*":
			case "/":
				return true;
			case "^":
				return false;
			default:
			}
		default:
		}
		return true;

	}
	/*
	 *Change infix expression to postfix expression
	 */
	private void infixToPostfix() {
		postfixTokens = new TokenList();
		StringStack intopos = new StringStack();
		for (int i = 0; i < infixTokens.size(); i++) { //determing each character
			String currentStr = infixTokens.get(i);
			if (isOperator(currentStr)) { // if the character is an operator 
				if (intopos.isEmpty()) {  //push into stack if the stack is empty
					intopos.push(currentStr);
				} else {
					while (!intopos.isEmpty()) { //if the stack is not empty then compare those operators 
						String stacktop = intopos.pop();
						if (precedence(stacktop, currentStr)) { //if it has higher precedence then push it into stack
							intopos.push(stacktop);
							break;
						} else {
							postfixTokens.append(stacktop);  //if it is not then put it in the end of the postfix expression
						}
					}
					intopos.push(currentStr);  //push the current operator into stack
				}
			}else if(currentStr.equals("(")){  // ( 
				intopos.push(currentStr);
			}else if(currentStr.equals(")")){ // )
				while(!intopos.isEmpty()){
					String stacktop = intopos.pop();
					if(stacktop.equals("(")) break;
					postfixTokens.append(stacktop);
				}
			}else{                                  //determing if the user inputs invalid expression like "a b &"
				try{
					double isdouble = Double.parseDouble(currentStr);
					postfixTokens.append(currentStr);
				}catch (NumberFormatException x){
					throw new InvalidExpressionException("Invalid input");
				}
			}
		}
		while(!intopos.isEmpty()){                 //place everthing that is still in the stack to the end of postfix expression 
			postfixTokens.append(intopos.pop());
		}
	}
	/*
	 *Get infix expression
	 */
	public String getInfixExpression() {
		String tmp = "";
		for (int i = 0; i < infixTokens.size() - 1; i++) {
			tmp += infixTokens.get(i);
			tmp += " ";
		}
		tmp += infixTokens.get(infixTokens.size() - 1);
		return tmp;

	}
	
	 /*
	 *Get postfix expression
	 */
	public String getPostfixExpression() {
		String tmp = "";
		for (int i = 0; i < postfixTokens.size() - 1; i++) {
			tmp += postfixTokens.get(i);
			tmp += " ";
		}
		tmp += postfixTokens.get(postfixTokens.size() - 1);
		return tmp;
	}

 	 /*
	 * The calculation after getting postfix expression 
	 */
	public double evaluate() {
		StringStack eva = new StringStack();
		for(int i =0; i < postfixTokens.size(); i++ ){
			String current = postfixTokens.get(i);
			double oprand1 = 0;
			double oprand2 = 0;
			double result = 0;
			switch(current){
				case "+":                                       //"+"
					 oprand1 = Double.parseDouble(eva.pop());
					 oprand2 = Double.parseDouble(eva.pop());
					 result = oprand1 + oprand2;
					eva.push(Double.toString(result));
					break;
				case "-":                                      //"-"
					 oprand1 = Double.parseDouble(eva.pop());
					 oprand2 = Double.parseDouble(eva.pop());
					 result = oprand2 - oprand1;
					eva.push(Double.toString(result));
					break;
				case "*":                                     //"*"
					 oprand1 = Double.parseDouble(eva.pop());
					 oprand2 = Double.parseDouble(eva.pop());
					 result = oprand1 * oprand2;
					eva.push(Double.toString(result));
					break;
				case "/":                                     //"/"
					 oprand1 = Double.parseDouble(eva.pop());
					 oprand2 = Double.parseDouble(eva.pop());
					 result = oprand2 / oprand1;
					eva.push(Double.toString(result));
					break;
				case "^":
					 oprand1 = Double.parseDouble(eva.pop());
					 oprand2 = Double.parseDouble(eva.pop());
					 result = java.lang.Math.pow(oprand2, oprand1);
					eva.push(Double.toString(result));
					break;
				default: 
					eva.push(current);
			}
		}
		return Double.parseDouble(eva.pop());

	}
 	 /*
	 * To get the user input to do the calculation 
	 */
	public static void main(String[] args) {
		Scanner aScanner = new Scanner(System.in);
		while(true){
			System.out.print("Please enter an arithmetic expression: ");
			String aString = aScanner.nextLine();
			try{
				try{
				ArithExpression a = new ArithExpression(aString);
				//System.out.println("you entered:  " + getInfixExpression(a));
				//System.out.println("The postfix is:  " + getPostfixExpression(a));
				System.out.println(a.evaluate());
				}catch (StackEmptyException x){
					System.out.println("Stack is empty");
				}	
			}catch (InvalidExpressionException x){
				System.out.println("Invalid expression");
			}
		}

	}

}
