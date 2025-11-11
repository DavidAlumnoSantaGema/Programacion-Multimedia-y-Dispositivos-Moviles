package com.example.edittext;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        String[] opciones = {"toma","tomate","tomalo","iaiao"};

        AutoCompleteTextView text = findViewById(R.id.autocomplete);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, opciones);
        text.setAdapter(adapter);

        Spinner spinner = findViewById(R.id.spinner);
        String[] values = {"C#","C++","C","Java","Python","Lua","GDScript","Scratch","Javascript","Angular","NodeJS","SQL","COBOL"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Has seleccionado el valor: "+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "No has seleccionado nada", Toast.LENGTH_SHORT).show();
            }
        });

        CheckBox checkBox = findViewById(R.id.mujer);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                    Toast.makeText(MainActivity.this, "Mujer: si", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Mujer: no", Toast.LENGTH_SHORT).show();
            }
        });

        RadioGroup group = findViewById(R.id.radioGroup);
        Button buttonCheck = findViewById(R.id.buttonCheck);

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = group.getCheckedRadioButtonId();
                if (selectedId != -1)
                {
                    RadioButton selected = findViewById(selectedId);
                    Toast.makeText(MainActivity.this, selected.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Switch switchPipsas = findViewById(R.id.pipsas);

        switchPipsas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Toast.makeText(MainActivity.this, "Llegaron las pipsas", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "A tomar por culo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SeekBar seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                System.out.println(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                Toast.makeText(MainActivity.this, "Nooo no me toques aaaaa", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                Toast.makeText(MainActivity.this, "Gracias por respetar mis limites", Toast.LENGTH_SHORT).show();
            }
        });

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView ratingRes = findViewById(R.id.estrellasRes);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
            {
                if (rating == 12)
                {
                    ratingRes.setText("La bandera europea tiene 12 estrellas");
                    ratingRes.setTextColor(Color.rgb(4, 122, 0));
                }
                else
                {
                    ratingRes.setText("La bandera europea no tiene " + rating + " estrellas");
                    ratingRes.setTextColor(Color.rgb(255, 0, 0));
                }
            }
        });

        ProgressBar pBar = findViewById(R.id.barraFibonacci);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
        {
            int progress = 0;
            int a = 1;
            int b = 0;
            @Override
            public void run()
            {
                int total = a+b;
                b = a;
                a = total;
                if (progress <= 100)
                {
                    progress += total;
                    pBar.setProgress(progress);
                    new Handler(Looper.getMainLooper()).postDelayed(this, 200);
                }
            }
        }, 200);
    }
}