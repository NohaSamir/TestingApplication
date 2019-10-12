package com.example.testingapplication.servicelocator;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.testingapplication.CalculatorViewModel;

// Converted it from a class to an interface
public interface ServiceLocator {

    CalculatorViewModel getCalculatorViewModel(FragmentActivity activity);

    /**
     * The default implementation for the interface {@link ServiceLocator}.
     */
    class DefaultServiceLocatorImpl implements ServiceLocator {
        @Override
        public CalculatorViewModel getCalculatorViewModel(FragmentActivity activity) {
            return ViewModelProviders.of(activity).get(CalculatorViewModel.class);
        }
    }

    /**
     * A Utils class which handles the {@link ServiceLocator}, it is a group of static methods and
     * variables which contains the current dependency injection container (as an instance of
     * {@link ServiceLocator}). Made this class because interface in old Java versions, doesn't
     * support static properties nor static methods.
     */
    class ServiceLocatorProvider {
        private static ServiceLocator instance = null;

        /**
         * Gets the current instance of the {@link ServiceLocator}, if it is already set, just gets
         * it. If it is still not set, it creates the default one.
         * <b>This is set <i>ONLY ONE TIME</i></b>
         *
         * @return The current {@link ServiceLocator}, and creates one if none available now.
         */
        public static ServiceLocator getInstance() {
            if (instance == null) {
                instance = new DefaultServiceLocatorImpl();
            }
            return instance;
        }

        /**
         * Sets the {@link ServiceLocator}, should <b><i>ONLY BE USED IN TESTS</i></b>.
         * So it is made package private so shouldn't be abused from the application itself.
         * So, the test package should be the same as
         * <code>com.example.testingapplication.servicelocator</code>
         *
         * @param serviceLocator The default {@link ServiceLocator} to set instead of the
         *                       {@link DefaultServiceLocatorImpl}
         */
        static void setServiceLocator(ServiceLocator serviceLocator) {
            if (instance != null) { throw new IllegalArgumentException("Already set before"); }
            instance = serviceLocator;
        }

        /**
         * Resets the singleton, very dangerous shouldn't be used except in the tests package.
         * So also made package private like {@link #setServiceLocator(ServiceLocator)}
         */
        static void reset() {
            instance = null;
        }

    }

}

