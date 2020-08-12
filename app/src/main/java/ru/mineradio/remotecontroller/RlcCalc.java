package ru.mineradio.remotecontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Math.sqrt;

public class RlcCalc extends AppCompatActivity {

    double inductanceValue=0;
    double capacitanceValue=0;
    double frequencyValue=0;
    double resistanceValue=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rlc_calc);

        final EditText inductanceEdit = findViewById(R.id.inductanceInput);
        final EditText capacitanceEdit = findViewById(R.id.capacitanceInput);
        final EditText frequencyEdit = findViewById(R.id.frequencyInput);
        final EditText resistanceEdit = findViewById(R.id.resistanceInput);

        inductanceEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // сохраняем текст, введенный до нажатия Enter в переменную
                    inductanceValue = Double.parseDouble(inductanceEdit.getText().toString())*1e-6;
                    Log.d("inductance", String.valueOf(inductanceValue));
                    calculationResult();
                    return true;
                }
                return false;
            }
        });

        capacitanceEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // сохраняем текст, введенный до нажатия Enter в переменную
                    capacitanceValue = Double.parseDouble(capacitanceEdit.getText().toString())*1e-6;
                    Log.d("capacitance", String.valueOf(capacitanceValue));
                    calculationResult();
                    return true;
                }
                return false;
            }
        });

        frequencyEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // сохраняем текст, введенный до нажатия Enter в переменную
                    frequencyValue = Double.parseDouble(frequencyEdit.getText().toString());
                    Log.d("frequency", String.valueOf(frequencyValue));
                    calculationResult();
                    return true;
                }
                return false;
            }
        });

    }

    void calculationResult()
    {
        if (inductanceValue != 0.0 && capacitanceValue != 0.0) {
            frequencyValue = 1 / (2 * 3.14 * sqrt(inductanceValue * capacitanceValue));

        } else if (inductanceValue != 0.0 && frequencyValue != 0.0) {
            capacitanceValue = (frequencyValue * frequencyValue) / (4 * Math.PI * Math.PI * inductanceValue);

        } else if (capacitanceValue != 0.0 && frequencyValue != 0.0) {
            inductanceValue = (frequencyValue * frequencyValue) / (4 * Math.PI * Math.PI * capacitanceValue);

        } else if((inductanceValue != 0.0) && (capacitanceValue != 0.0) && (frequencyValue != 0.0)){
            Toast.makeText(getApplicationContext(), "delete interesting position", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "not enough data", Toast.LENGTH_SHORT).show();
        }

        final EditText inductanceEdit = findViewById(R.id.inductanceInput);
        final EditText capacitanceEdit = findViewById(R.id.capacitanceInput);
        final EditText frequencyEdit = findViewById(R.id.frequencyInput);
        //display update
        inductanceEdit.setText(String.valueOf(inductanceValue*1e+6));
        capacitanceEdit.setText(String.valueOf(capacitanceValue*1e+6));
        frequencyEdit.setText(String.valueOf(frequencyValue));

    }
}