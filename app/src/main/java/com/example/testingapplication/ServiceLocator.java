package com.example.testingapplication;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

public class ServiceLocator {

    public CalculatorViewModel getCalculatorViewModel(FragmentActivity activity) {
        return ViewModelProviders.of(activity).get(CalculatorViewModel.class);
    }
}
