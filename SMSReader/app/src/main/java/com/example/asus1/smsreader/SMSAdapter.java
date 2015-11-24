package com.example.asus1.smsreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus1 on 22.11.2015.
 */
public class SMSAdapter extends BaseAdapter {
    LayoutInflater lInflater;
    Context context;
    ArrayList<SMSData> objects;
    public SMSAdapter(Context context, List<SMSData> arUsers) {
        this.context = context;
        this.objects = (ArrayList<SMSData>) arUsers;

        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public SMSData getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    SMSData getGood(int position) {
        return ((SMSData) getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder;
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item_adapter_sms, parent, false);
        }
        holder = new Holder();
        final SMSData p = getGood(position);

        holder.tvAdress = ((TextView) view.findViewById(R.id.tvAdress));
        holder.tvBody = ((TextView) view.findViewById(R.id.tvBody));


        holder.tvAdress.setText(p.getNumber());
        holder.tvBody.setText(p.getBody());

        return view;
    }


    private static class Holder {
        public TextView tvAdress;
        public TextView tvBody;
    }


}
