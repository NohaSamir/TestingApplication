package com.example.testingapplication.servicelocator;

import com.example.testingapplication.Multiplier;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Just a utility class that can set the default {@link ServiceLocator}.
 */
public class ServiceLocatorSetter {
    public static void mockTheServiceLocator(final Multiplier multiplier) {
        ServiceLocator.ServiceLocatorProvider.setServiceLocator(new ServiceLocator() {
            @Override
            public Multiplier getMultiplier() {
                return multiplier;
            }
        });
    }

    public static void reset() {
        ServiceLocator.ServiceLocatorProvider.reset();
    }

}
