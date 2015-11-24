package com.example.asus1.smsreader;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class ListSMSFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    List<SMSData> smsList;
    private ListView lvGoods;
    SwipeRefreshLayout mSwipeRefreshLayout;
    MyAsyncTaskLoad myAsyncTaskLoad;
    static private SMSAdapter smsAdapter;
    boolean sendin;
    public ListSMSFragment(boolean sendin) {
        this.sendin = sendin;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_sms, container, false);
        smsList = new ArrayList<SMSData>();
        lvGoods = (ListView) view.findViewById(R.id.lvSMS);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        myAsyncTaskLoad = new MyAsyncTaskLoad();
        myAsyncTaskLoad.execute();
        return view;
    }

    @Override
    public void onRefresh() {

        mSwipeRefreshLayout.setRefreshing(true);
        assert mSwipeRefreshLayout != null;
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                myAsyncTaskLoad = new MyAsyncTaskLoad();
                myAsyncTaskLoad.execute();
            }
        }, 500);


    }

    class MyAsyncTaskLoad extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String uriStr ="content://sms/";
            if(sendin)
                uriStr += "inbox";
            else
                uriStr += "sent";
            Uri uri = Uri.parse(uriStr);
            Cursor c = getActivity().getContentResolver().query(uri, null, null ,null,null);
            if(c.moveToFirst()) {
                for(int i=0; i < c.getCount(); i++) {
                    smsList.add(new SMSData(c.getString(c.getColumnIndexOrThrow("address")), c.getString(c.getColumnIndexOrThrow("body"))));
                    c.moveToNext();
                }
            }
            c.close();
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            smsAdapter = new SMSAdapter(getActivity(), smsList);
            lvGoods.setAdapter(smsAdapter);
            lvGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    getFragmentManager().beginTransaction().add(
                            R.id.flContainer, new UnicalSmsFragment(smsList.get(position))).addToBackStack(null).commit();
                }
            });
        }
    }
}
