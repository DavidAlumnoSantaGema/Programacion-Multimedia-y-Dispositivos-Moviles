package com.example.listadecompra;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ListView listView;
    private ShoppingListAdapter adapter;
    private List<Item> listViewItems;
    private Spinner spinner;
    Item lastPressedItem;


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
                        R.drawable.pan,
                        R.drawable.tomate,
                        R.drawable.patatas,
                        R.drawable.platanos,
                        R.drawable.jabon_manos
                };
        int[] titles =
                {
                        R.string.pan,
                        R.string.tomate,
                        R.string.kiloPatata,
                        R.string.platanos,
                        R.string.jabonManos
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

                if (item.getNombre() == titles[spinner.getSelectedItemPosition()])
                {
                    item.setCantidad(item.getCantidad() + 1);
                    adapter.notifyDataSetChanged();
                    return;
                }
            }
            listViewItems.add(new Item
                            (titles[spinner.getSelectedItemPosition()],
                            icons[spinner.getSelectedItemPosition()]));

            adapter.notifyDataSetChanged();
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                lastPressedItem = (Item) adapter.getItem(position);
            }
        });

        registerForContextMenu(listView);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        if (lastPressedItem != null)
        {
            if (id == R.id.action_add)
            {
                lastPressedItem.setCantidad(lastPressedItem.getCantidad() + 1);
            }
            else if (id == R.id.action_remove)
            {
                if (lastPressedItem.getCantidad() > 1)
                {
                    lastPressedItem.setCantidad(lastPressedItem.getCantidad() - 1);
                }
                else
                {
                    adapter.Remove(lastPressedItem);
                }
            }
            adapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }
}