package com.example.gestioncuentavar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView textViewPropina = findViewById(R.id.textViewPropina);
        SeekBar seekbarPropina = findViewById(R.id.seekbarPropina);

        seekbarPropina.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                textViewPropina.setText("Porcentaje de propina: " + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button button = findViewById(R.id.botonCalcular);
        TextView textViewCalculo = findViewById(R.id.textViewCalculo);
        EditText textViewCuenta = findViewById(R.id.editTextCuenta);
        CheckBox checkBoxPropina = findViewById(R.id.checkBoxPropina);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                textViewCalculo.setText("");


                String textoCuenta = textViewCuenta.getText().toString();
                if (textoCuenta.isEmpty())
                {
                    textViewCalculo.append("No se ha introducido una cuenta");
                    return;
                }
                float num = Float.parseFloat(textoCuenta);

                if (num <= 0)
                {
                    textViewCalculo.append("Cuenta inválida");
                    return;
                }
                textViewCalculo.append("Cuenta: " + textViewCuenta.getText() + "€\n");

                if (checkBoxPropina.isChecked())
                {
                    textViewCalculo.append("Propina: " + seekbarPropina.getProgress() + "%\n");
                    float res = num + (num * seekbarPropina.getProgress() / 100);
                    textViewCalculo.append("Total: " + res + '\n');
                }
            }
        });
    }
}