package com.example.madproject;

import org.junit.Test;

import static org.junit.Assert.*;

public class incomecalTest {

    @Test
    public void calculateSalary() {
        double input = 60000;
        double output;
        double expected = 6000;
        double delta =.1;


        output = incomecal.calculateSalary(input);

        assertEquals(expected,output,delta);


    }
    @Test
    public void calculateNetsalary(){
        double input1 = 20000;
        double input2 = 10000;
        double output;
        double expected = 10000;
        double delta =.1;


        output = incomecal.calculateNetsalary(input1,input2);

        assertEquals(expected,output,delta);
    }

}