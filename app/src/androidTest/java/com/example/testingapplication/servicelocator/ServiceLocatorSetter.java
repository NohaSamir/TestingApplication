package com.example.testingapplication.servicelocator;

import androidx.fragment.app.FragmentActivity;

import com.example.testingapplication.CalculatorViewModel;
import com.example.testingapplication.Multiplier;

/**
 * Just a utility class that can set the default {@link ServiceLocator}.
 */
public class ServiceLocatorSetter {
    /**
     * Just calls {@link ServiceLocator.ServiceLocatorProvider#setServiceLocator(ServiceLocator)}.
     */
    public static void mockTheServiceLocator(final Multiplier multiplier) {
        ServiceLocator.ServiceLocatorProvider.reset();
        ServiceLocator.ServiceLocatorProvider.setServiceLocator(new ServiceLocator() {
            @Override
            public Multiplier getMultiplier() {
                return multiplier;
            }
        });
    }

}
