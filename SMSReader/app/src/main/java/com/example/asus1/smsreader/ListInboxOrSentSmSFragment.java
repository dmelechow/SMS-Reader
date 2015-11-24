package com.example.asus1.smsreader;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;


public class ListInboxOrSentSmSFragment extends Fragment {


    public ListInboxOrSentSmSFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_inbox_or_sent_sms, container, false);


        TabHost tabs = (TabHost) view.findViewById(android.R.id.tabhost);

        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("tag1");

        spec.setContent(R.id.tab1);
        spec.setIndicator("Входящие");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Исходящие");
        tabs.addTab(spec);

        getFragmentManager().beginTransaction().add(R.id.tab1, new ListSMSFragment(true)).commit();

        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (tabId) {
                    case "tag1": {
                        getFragmentManager().beginTransaction().add(R.id.tab1, new ListSMSFragment(true)).commit();

                    }
                    break;
                    case "tag2": {

                        getFragmentManager().beginTransaction().add(R.id.tab2, new ListSMSFragment(false)).commit();
                    }
                    break;
                }
            }
        });
        return view;
    }


}
