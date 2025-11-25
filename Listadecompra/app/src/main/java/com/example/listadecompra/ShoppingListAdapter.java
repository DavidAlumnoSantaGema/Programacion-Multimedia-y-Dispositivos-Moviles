package com.example.listadecompra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListAdapter extends BaseAdapter
{
    private Context context;
    private List<Item> items;
    private int selectedPos = -1;


    public ShoppingListAdapter(Context context, List<Item> items)
    {
        this.context = context;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopping, parent, false);
        }

        Item item = (Item) getItem(position);

        ImageView imageView = convertView.findViewById(R.id.imgList);
        TextView titulo = convertView.findViewById(R.id.titleList);
        TextView cantidad = convertView.findViewById(R.id.cantidadList);
        Button button = convertView.findViewById(R.id.buttonList);

        //imageView.setImageResource(item);
        titulo.setText(item.getNombre());
        cantidad.setText(String.valueOf(item.getCantidad()));


        button.setOnClickListener(v ->
        {
            if (item.getCantidad() > 1)
            {
                item.setCantidad(item.getCantidad() - 1);
            }
            else
            {
                items.remove(position);
            }
            notifyDataSetChanged();
        });

        return convertView;
    }
}
