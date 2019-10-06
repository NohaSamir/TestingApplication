package com.example.testingapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private EditText editText1;
    private EditText editText2;


    public CalculatorViewModel calculatorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.textView);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

        calculatorViewModel = ViewModelProviders.of(this).get(CalculatorViewModel.class);

        //Test fail in case i observe my result in on create, because it called before setViewModel()
        /*
        calculatorViewModel.getResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                resultTextView.setText(String.valueOf(integer));
            }
        });
        */
    }


    public void multiply(View view) {

        String x = editText1.getText().toString();
        String y = editText2.getText().toString();

        calculatorViewModel.multiply(x, y);

        calculatorViewModel.getResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                resultTextView.setText(String.valueOf(integer));
            }
        });
    }


    @VisibleForTesting
    public void setViewModel(CalculatorViewModel viewModel) {
        calculatorViewModel = viewModel;

        //Test fail in case I observe my result in setViewModel(), because it Cannot invoke observe on a background thread
        /*calculatorViewModel.getResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                resultTextView.setText(String.valueOf(integer));
            }
        });*/
    }
}
