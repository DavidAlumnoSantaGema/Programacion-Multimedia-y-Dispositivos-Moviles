package com.example.animacionconbotones;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    Animation animacion;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.texto);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        texto = findViewById(R.id.cajaTexto);
        Button botonTranslate = findViewById(R.id.bottonTranslate);
        botonTranslate.setOnClickListener(v ->
        {
            detenerAnimacionActual();
            animacion = AnimationUtils.loadAnimation(this, R.anim.translate);
            texto.startAnimation(animacion);
        });
        Button botonRotate = findViewById(R.id.bottonRotate);
        botonRotate.setOnClickListener(v ->
        {
            detenerAnimacionActual();
            animacion = AnimationUtils.loadAnimation(this, R.anim.rotate);
            texto.startAnimation(animacion);
        });
        Button detener = findViewById(R.id.bottonDetener);
        detener.setOnClickListener(v ->
        {
            detenerAnimacionActual();
        });
    }

    private  void detenerAnimacionActual()
    {
        if (animacion != null)
        {
            texto.clearAnimation();
            animacion.cancel();
            animacion = null;
        }
    }
}