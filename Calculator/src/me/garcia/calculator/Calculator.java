package me.garcia.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Calculator {
    
    private static final Map<String, Integer> orderOfOperations = new HashMap<>();
    
    static {
        orderOfOperations.put(")", -1);
        orderOfOperations.put("(", 3);
        orderOfOperations.put("^", 2);
        orderOfOperations.put("*", 1);
        orderOfOperations.put("/", 1);
        orderOfOperations.put("+", 0);
        orderOfOperations.put("-", 0);
    }
    
    private int significantDigits = 0;
    
    public Calculator() {
    }
    
    public Calculator(int digits) {
        significantDigits = digits;
    }
    
    public BigDecimal evaluate(String expression) {
        Iterable<ExpressionToken> tokens = tokenizeOperatorsAndOperands(expression);
        Stack<BigDecimal> operands = new Stack<>();
        Stack<String> operators = new Stack<>();
        BigDecimal operand2;
        BigDecimal operand1;
        for (ExpressionToken token : tokens) {
            if (token.isNumeric()) {
                operands.push(token.getOperand());
            } else {
                if (operators.isEmpty()) {
                    operators.push(token.getOperator());
                } else {
                    String currentOperator = token.getOperator();
                    if (currentOperator.equals(")")) {
                        while (!operators.peek().equals("(")) {
                            operand2 = operands.pop();
                            operand1 = operands.pop();
                            String operator = operators.pop();
                            BigDecimal tempResult = calculate(operand1, operand2, operator);
                            operands.push(tempResult);
                        }
                        operators.pop();
                    } else {
                        String topOperator = operators.peek();
                        if (currentHasLowerPrescendenceThanTop(currentOperator, topOperator)) {
                            operand2 = operands.pop();
                            operand1 = operands.pop();
                            topOperator = operators.pop();
                            BigDecimal tempResult = calculate(operand1, operand2, topOperator);
                            operands.push(tempResult);
                            operators.push(token.getOperator());
                        } else {
                            operators.push(token.getOperator());
                        }
                    }
                }
            }
        }
        BigDecimal finalResult = null;
        while (!operators.isEmpty()) {
            operand2 = operands.pop();
            operand1 = operands.pop();
            String operator = operators.pop();
            finalResult = calculate(operand1, operand2, operator);
            operands.push(finalResult);
        }
        return finalResult;
    }
    
    private boolean currentHasLowerPrescendenceThanTop(String current, String compareAgainst) {
        if (compareAgainst.equals("(")) {
            return false;
        } else {
            return orderOfOperations.get(current) < orderOfOperations.get(compareAgainst);
        }
    }
    
    private BigDecimal calculate(BigDecimal operand1, BigDecimal operand2, String operator) {
        switch (operator) {
        case "+": 
            return operand1.add(operand2);
        case "-": 
            return operand1.subtract(operand2);
        case "*": 
            return operand1.multiply(operand2);
        case "/": 
            return operand1.divide(operand2, significantDigits, RoundingMode.HALF_UP);
        case "^": 
            return operand1.pow(operand2.intValue());
        }
        return null;
    }
    
    private Iterable<ExpressionToken> tokenizeOperatorsAndOperands(String expression) {
        List<ExpressionToken> result = new LinkedList<>();
        char[] rawCharacters = expression.toCharArray();
        String tempNumber = "";
        for (char character : rawCharacters) {
            if (character >= '0' && character <= '9') {
                tempNumber += character;
            } else if (character != ' '){
                if (tempNumber.length() > 0) {
                    result.add(new ExpressionToken(tempNumber));
                }
                tempNumber = "";
                result.add(new ExpressionToken("" + character));
            }
        }
        if (tempNumber.length() > 0) {
            result.add(new ExpressionToken(tempNumber));
        }
        return result;
    }
}
