package com.example.togglebutton;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    TextView textView;
    ToggleButton toggleButton;
    ImageButton botonImagen;
    int pos = 0;
    int[] horas = new int[]
    {
            R.drawable.uno,
            R.drawable.dos,
            R.drawable.tres,
            R.drawable.cuatro,
            R.drawable.cinco,
            R.drawable.seis,
            R.drawable.seismedia,
            R.drawable.siete,
            R.drawable.ocho,
            R.drawable.nueve,
            R.drawable.diez,
            R.drawable.once,
            R.drawable.doce
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        textView = findViewById(R.id.texto);
        toggleButton = findViewById(R.id.boton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    textView.setText("Estado: Pulsado");
                }
                else
                {
                    textView.setText("Estado: No pulsado");
                }
            }
        });
        botonImagen = findViewById(R.id.botonimages);
        botonImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                pos = (pos + 1) % horas.length;
                botonImagen.setImageResource(horas[pos]);
            }
        });
    }
}