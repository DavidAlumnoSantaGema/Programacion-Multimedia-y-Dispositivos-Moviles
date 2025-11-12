package com.example.gestioncuentavar;

import android.graphics.Color;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
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

        String[] opciones = {"Enrique","Adriana","Pablo","Eustaquio"};
        AutoCompleteTextView autoCompleteNombreCamarero = findViewById(R.id.autoCompleteNombreCamarero);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, opciones);
        autoCompleteNombreCamarero.setAdapter(adapter);

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
        RadioGroup radioGroup = findViewById(R.id.radioGroupMetodoPago);
        RatingBar ratingBar = findViewById(R.id.ratingBar);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                textViewCalculo.setText("");


                String textoCuenta = textViewCuenta.getText().toString();
                if (textoCuenta.isEmpty())
                {
                    textViewCalculo.setTextColor(Color.RED);
                    textViewCalculo.append("No se ha introducido la cuenta");
                    return;
                }
                float num = Float.parseFloat(textoCuenta);

                if (num <= 0)
                {
                    textViewCalculo.setTextColor(Color.RED);
                    textViewCalculo.append("Cuenta inválida");
                    return;
                }

                int id = radioGroup.getCheckedRadioButtonId();

                if (id == -1)
                {
                    textViewCalculo.setTextColor(Color.RED);
                    textViewCalculo.append("No se ha introducido un método de pago");
                    return;
                }

                textViewCalculo.setTextColor(Color.BLACK);
                textViewCalculo.append("Cuenta: " + textViewCuenta.getText() + "€\n");

                if (checkBoxPropina.isChecked())
                {
                    textViewCalculo.append("Propina: " + seekbarPropina.getProgress() + "%\n");
                    float res = num + (num * seekbarPropina.getProgress() / 100);
                    textViewCalculo.append("Total: " + res + "€\n");
                }

                RadioButton radioButton = findViewById(id);
                textViewCalculo.append("Método de pago elegido: " + radioButton.getText() +'\n');

                textViewCalculo.append("Calificación del servicio: " + ((int) ratingBar.getRating()) + " estrellas.");

                String nombreCamarero = autoCompleteNombreCamarero.getText().toString();
                if (!nombreCamarero.isEmpty())
                {
                    textViewCalculo.append("\nCamarero que ha atendido: "+nombreCamarero);
                }
            }
        });
    }
}