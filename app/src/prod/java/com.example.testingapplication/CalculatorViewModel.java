package com.example.testingapplication;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {

    private MutableLiveData<Integer> result = new MutableLiveData<>();
    private Calculator calculator = new Calculator();

    MutableLiveData<Integer> getResult() {
        return result;
    }

    void multiply(String x, String y) {

        if (!TextUtils.isEmpty(x) && !TextUtils.isEmpty(y))
            result.setValue(calculator.multiply(Integer.valueOf(x), Integer.valueOf(y)));
    }
}
