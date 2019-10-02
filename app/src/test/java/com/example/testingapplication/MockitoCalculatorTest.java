package com.example.testingapplication;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class MockitoCalculatorTest {

    @Mock
    public Calculator calculator;


    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testCalculator()
    {
        //Now if we path 1 , 2 => it will return 5
        //otherwise it will return 0
        /*
        when(calculator.multiply(1 , 2)).thenReturn(5);
        Assert.assertEquals(5 , calculator.multiply(1 , 3));
        */
        when(calculator.multiply(Mockito.anyInt() , Mockito.anyInt())).thenReturn(5);
        Assert.assertEquals(5 , calculator.multiply(1 , 3));

    }
}
