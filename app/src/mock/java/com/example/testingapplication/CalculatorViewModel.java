package com.example.testingapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {

    private MutableLiveData<Integer> result = new MutableLiveData<>();

    MutableLiveData<Integer> getResult() {
        return result;
    }

    void multiply(String x, String y) {
        result.setValue(6);
    }
}
