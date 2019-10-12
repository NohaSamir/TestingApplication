package com.example.testingapplication.servicelocator;

import androidx.fragment.app.FragmentActivity;

import com.example.testingapplication.CalculatorViewModel;

/**
 * Just a utility class that can set the default {@link ServiceLocator}.
 */
public class ServiceLocatorSetter {
    /**
     * Just calls {@link ServiceLocator.ServiceLocatorProvider#setServiceLocator(ServiceLocator)}.
     */
    public static void mockTheServiceLocator(final CalculatorViewModel viewModel) {
        ServiceLocator.ServiceLocatorProvider.reset();
        ServiceLocator.ServiceLocatorProvider.setServiceLocator(new ServiceLocator() {
            @Override
            public CalculatorViewModel getCalculatorViewModel(FragmentActivity activity) {
                return viewModel;
            }
        });
    }

}
