package com.example.listadecompra;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ListView listView;
    private ShoppingListAdapter adapter;
    private List<Item> listViewItems;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        listViewItems = new ArrayList<>();

        adapter = new ShoppingListAdapter(this, listViewItems);
        listView.setAdapter(adapter);

        spinner = findViewById(R.id.spinner);

        int[] icons =
                {
                        R.drawable.ic_launcher_background,
                        R.drawable.ic_launcher_background,
                        R.drawable.ic_launcher_background
                };
        String[] titles =
                {
                        "pan",
                        "tomate",
                        "un kilo de patatas"
                };

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, icons, titles);

        spinner.setAdapter(spinnerAdapter);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(v ->
        {
            System.out.println(listViewItems.size());
            for (int i = 0; i < listViewItems.size(); i++)
            {
                Item item = listViewItems.get(i);

                if (item.getNombre().equals(titles[spinner.getSelectedItemPosition()]))
                {
                    item.setCantidad(item.getCantidad() + 1);
                    adapter.notifyDataSetChanged();
                    return;
                }
            }
            listViewItems.add(new Item(titles[spinner.getSelectedItemPosition()]));
            adapter.notifyDataSetChanged();
        });
    }
}