package com.example.madproject;

import static org.junit.Assert.*;

import org.junit.Test;

public class class_payementsTest {

    @Test
    public void totalsum()
    {
        int input1=2000;
        int input2=10;
        int output;
        int expected =20000;
        double delta = .1;
        output = class_payements.tot(input1,input2);
        assertEquals(expected,output,delta);



    }
}