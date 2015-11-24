package com.example.asus1.smsreader;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class WelcomeFragment extends Fragment implements View.OnClickListener {

    MyAsyncTask myAsyncTask;

    TextView tvThreeSec;
    ImageView imSplashScreen;

    public WelcomeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        tvThreeSec = (TextView) view.findViewById(R.id.tvThreeSec);
        imSplashScreen = (ImageView) view.findViewById(R.id.imSplashScreen);
        imSplashScreen.setOnClickListener(this);

        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imSplashScreen:{
                cancelTask();
                callFragment();
            }break;
        }
    }

    private void cancelTask() {
        if (myAsyncTask == null)
            return;
        myAsyncTask.cancel(false);
    }

    public void callFragment(){
        getFragmentManager().popBackStack();
        getFragmentManager().beginTransaction().add(R.id.flContainer, new ListInboxOrSentSmSFragment()).commit();
    }

    class MyAsyncTask extends AsyncTask<Void,Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 3; i > 0; i--) {
                    publishProgress(i);
                    TimeUnit.SECONDS.sleep(1);

                    if (isCancelled()) return null;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvThreeSec.setText("Осталось " + values[0] + " секунд");
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            callFragment();
        }
    }
}
