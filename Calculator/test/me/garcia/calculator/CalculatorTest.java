package me.garcia.calculator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class CalculatorTest {

    Calculator calculator = new Calculator();
    
    @Test
    public void canDoSimpleAddition() {
        assertEquals(new BigDecimal("20"), calculator.evaluate("13+7"));
        assertEquals(new BigDecimal("70"), calculator.evaluate("67+3"));
        assertEquals(new BigDecimal("35"), calculator.evaluate("30 + 3 + 2"));
    }


    @Test
    public void canDoSimpleSubtraction() {
        assertEquals(new BigDecimal("6"), calculator.evaluate("13-7"));
        assertEquals(new BigDecimal("64"), calculator.evaluate("67-3"));
    }
    
    @Test
    public void canDoComplexStuff() {
//        assertEquals(new BigDecimal("19"), calculator.evaluate("2+3*4+5"));
//        assertEquals(new BigDecimal("17"), calculator.evaluate("2+3*4+15/5"));
//        assertEquals(new BigDecimal("14"), calculator.evaluate("2+3*(5+15)/5"));
        Calculator calculator = new Calculator(2);
        assertEquals(new BigDecimal("34.85"), calculator.evaluate("2+3*(5+15^2)/(5+2*(4+4))"));
    }
}
