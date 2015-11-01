package me.garcia.calculator;

import java.math.BigDecimal;

public class ExpressionToken {

    private String text;
    private boolean numeric = false;

    public ExpressionToken(String text) {
        super();
        this.text = text;
        try {
            new BigDecimal(this.text);
            numeric = true;
        } catch (Exception e) {
            numeric = false;
        }
    }
    
    public boolean isNumeric() {
        return numeric;
    }
    
    public String getOperator() {
        return text;
    }
    
    public BigDecimal getOperand() {
        if (isNumeric()) {
            return new BigDecimal(this.text);
        } else {
            throw new IllegalArgumentException("Cannot invoke getNumericValue() when not a number");
        }
    }
    
    @Override
    public String toString() {
        return "[" + text + " - " + isNumeric() + "]";
    }
}
