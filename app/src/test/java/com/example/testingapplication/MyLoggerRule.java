package com.example.testingapplication;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.logging.Logger;

public class MyLoggerRule implements TestRule {

    private Logger logger;

    public Logger getLogger() {
        return logger;
    }

    @Override
    public Statement apply(Statement base, Description description) {

        logger = Logger.getLogger(base.getClass().getSimpleName());

        //Finish it
        try {
            base.evaluate();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return base;
    }
}
