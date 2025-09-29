package com.example.ciclosdevida;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
        Log.i("Ejemplo", "Estoy en on create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Ejemplo", "Estoy en on start");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Ejemplo", "Estoy en on restart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ejemplo", "Estoy en on pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ejemplo", "Estoy en on resume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ejemplo", "Estoy en on destroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ejemplo","Estoy en on stop");
    }
}