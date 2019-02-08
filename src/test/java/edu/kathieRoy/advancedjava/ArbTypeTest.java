package edu.kathieRoy.advancedjava;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ArbTypeTest {


    /**
     * this will test that the ArbType class works for Strings
     */
    @Test
    public void CheckStrings() {
        String testString = "thisIsATest";
        ArbType<String> arbTypeS = new ArbType<String>(testString);
        assertEquals("verify String", testString, arbTypeS.getInputStr());
    }
    //

    /**
     * this will test that the ArbType class works for Integers
     */
    @Test
    public void setCheckInteger() {
        Integer testInt = 9999;
        ArbType<Integer> arbTypeI = new ArbType<Integer>(testInt);


        assertEquals("verify Integer", testInt, arbTypeI.getInputStr());
    }

    //

    /**
     * this will test that the ArbType class works for BigDecimal
     */
    @Test
    public void setCheckBigD() {

        BigDecimal testBigD = new BigDecimal(77.85);
        ArbType<BigDecimal> arbTypeD = new ArbType<BigDecimal>(testBigD);
        assertEquals("verify BigDecimal", testBigD, arbTypeD.getInputStr());

    }
}