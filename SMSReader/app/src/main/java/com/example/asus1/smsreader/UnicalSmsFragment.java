package com.example.asus1.smsreader;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class UnicalSmsFragment extends Fragment implements View.OnClickListener {
    TextView tvAdress;
    TextView tvBody;
    Button btnSend;
    SMSData smsData;
    public UnicalSmsFragment(SMSData smsData) {
        this.smsData = smsData;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_unical_sms, container, false);
        tvAdress = ((TextView) view.findViewById(R.id.tvAdress));
        tvBody = ((TextView) view.findViewById(R.id.tvBody));
        btnSend = (Button) view.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);

        tvAdress.setText(smsData.getNumber());
        tvBody.setText(smsData.getBody());
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSend:{
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String textToSend = smsData.getBody();
                intent.putExtra(Intent.EXTRA_TEXT, textToSend);
                try {
                    startActivity(Intent.createChooser(intent, "Title"));
                }
                catch (android.content.ActivityNotFoundException ex) {

                }

            }break;
        }
    }
}
