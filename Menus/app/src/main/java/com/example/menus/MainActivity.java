package com.example.menus;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        //setSupportActionBar(toolbar);

        ListView listView = findViewById(R.id.listView);
        registerForContextMenu(listView);

        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("Elemento 1");
        itemList.add("Elemento 2");
        itemList.add("Elemento 3");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            Toast.makeText(this, "Opciones...", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.action_help)
        {
            Toast.makeText(this, "Heeeeelp", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.exit)
        {
            Toast.makeText(this, "Saliendo...", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.action_mas)
        {
            Toast.makeText(this, "Submenu", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.action_info)
        {
            Toast.makeText(this, "Este es un dispositivo android de naturaleza audiovisual cuyo objetivo es de caracter orientativo y...", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.action_acercade)
        {
            Toast.makeText(this, "Acerca de qué", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            Toast.makeText(this, "Opciones...", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.action_help)
        {
            Toast.makeText(this, "Heeeeelp", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.exit)
        {
            Toast.makeText(this, "Saliendo...", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.action_mas)
        {
            Toast.makeText(this, "Submenu", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.action_info)
        {
            Toast.makeText(this, "Este es un dispositivo android de naturaleza audiovisual cuyo objetivo es de caracter orientativo y...", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.action_acercade)
        {
            Toast.makeText(this, "Acerca de qué", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}