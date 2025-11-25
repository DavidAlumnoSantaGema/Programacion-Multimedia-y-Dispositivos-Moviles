package com.example.listadecompra;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerAdapter extends BaseAdapter
{
    private Context context;
    private int[] icons;
    private String[] titles;

    public SpinnerAdapter(Context context, int[] icons, String[] titles)
    {
        this.context = context;
        this.icons = icons;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int position) {
        return icons[position];
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
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imgSpinner);
        imageView.setImageResource(icons[position]);

        TextView textView = convertView.findViewById(R.id.titleSpinner);
        textView.setText(titles[position]);

        return convertView;
    }
}
