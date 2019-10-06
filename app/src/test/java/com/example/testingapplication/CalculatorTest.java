package com.example.testingapplication;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

//Test against multiple values
@RunWith(Parameterized.class)
public class CalculatorTest {

    @Rule
    public MyLoggerRule myLoggerRule = new MyLoggerRule();

    private int num1, num2, result;

    //Test with static value
    //if we write any normal test inside parametrized test ,
    //it will run for every parameter in @Parameterized.Parameters
    @Test
    public void multiply() {
        Calculator calculator = new Calculator();
        int x = calculator.multiply(1, 2);
        Assert.assertEquals(2, x);

        //Using rule
        Logger logger = myLoggerRule.getLogger();
        logger.warning("WARRRRRRRRRRRNING");
    }

    //******************************************************************

    /**
     * Test against multiple values
     */

    //Create constructor
    public CalculatorTest(int num1, int num2, int result) {
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
    }

    //Define multi tests
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]
                {
                        {1, 2, 2},
                        {3, 2, 6},
                        {5, 5, 25},
                        {5, 5, 25}
                };
        return Arrays.asList(data);
    }

    @Test
    public void testAgainstMultiNumbers()
    {
        Calculator calculator = new Calculator();
        Assert.assertEquals(result , calculator.multiply(num1 , num2));
    }


    //******************************************************************



}