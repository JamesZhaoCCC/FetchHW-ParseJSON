package com.example.fetchhw;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseAdapter{
    public ArrayList<HashMap<String, String>> list;
    Activity activity;

    public static final String FIRST_COLUMN="First";
    public static final String SECOND_COLUMN="Second";
    public static final String THIRD_COLUMN="Third";

    public ListViewAdapter(Activity activity, ArrayList<HashMap<String, String>> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        LayoutInflater inflater = activity.getLayoutInflater();

        if(view == null) {
            view = inflater.inflate(R.layout.colmn_row, null);
            holder = new ViewHolder();

            holder.txtFirst = (TextView) view.findViewById(R.id.TextFirst);
            holder.txtSecond = (TextView) view.findViewById(R.id.TextSecond);
            holder.txtThird = (TextView) view.findViewById(R.id.TextThird);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        HashMap<String, String> map = list.get(i);
        holder.txtFirst.setText(map.get(FIRST_COLUMN));
        holder.txtSecond.setText(map.get(SECOND_COLUMN));
        holder.txtThird.setText(map.get(THIRD_COLUMN));

        return view;
    }

    private class ViewHolder {
        TextView txtFirst;
        TextView txtSecond;
        TextView txtThird;
    }
}
