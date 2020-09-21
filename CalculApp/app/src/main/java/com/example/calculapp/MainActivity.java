package com.example.calculapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private TextView tv1;
    private Spinner sp1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.txt_num1);
        et2 = (EditText)findViewById(R.id.txt_num2);
        tv1 = (TextView)findViewById(R.id.txt_view);
        sp1 = (Spinner)findViewById(R.id.sp1);

        String[] opciones = {"sumar", "restar", "multiplicar", "dividir"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        sp1.setAdapter(adapter);
    }

    // METODE PER AL BOTÓ

    public void Calcular(View view){
        String String1 = et1.getText().toString();
        String String2 = et2.getText().toString();

        int int1 = Integer.parseInt(String1);
        int int2 = Integer.parseInt(String2);


        String selection = sp1.getSelectedItem().toString();

        if(selection.equals("sumar")) {
            int suma = int1 + int2;
            String resultado = String.valueOf(suma);
            tv1.setText(resultado);
        } else if (selection.equals("restar")) {
            int resta = int1 - int2;
            String resultado = String.valueOf(resta);
            tv1.setText(resultado);
        } else if (selection.equals("multiplicar")) {
            int multiplica = int1 * int2;
            String resultado = String.valueOf(multiplica);
            tv1.setText(resultado);
        } else if (selection.equals("dividir")) {
            if (int2 != 0 ) {
                int divide = int1 / int2;
                String resultado = String.valueOf(divide);
                tv1.setText(resultado);
            } else {
                Toast.makeText(this, "No se puede dividir por 0", Toast.LENGTH_LONG).show();
            }
        }
    }
}
