package com.example.testingapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void multiply(View view) {

        int x = Integer.valueOf(((EditText) findViewById(R.id.editText1)).getText().toString());
        int y = Integer.valueOf(((EditText) findViewById(R.id.editText2)).getText().toString());

        Calculator calculator = new Calculator();
        TextView textView = findViewById(R.id.textView);
        textView.setText(String.valueOf(calculator.multiply(x, y)));
    }
}
