package com.example.abhishekaryan.servicetutorial.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service{


    private static final String TAG = MyService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG,"msg: onCreate method is called, Thread name: - "+ Thread.currentThread().getName());
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"msg: onBind method is called, Thread name: - "+ Thread.currentThread().getName());
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG,"msg: onStartCommand method is called, Thread name: - "+ Thread.currentThread().getName());
        MyAsynTask task=new MyAsynTask();
        task.execute();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"msg: onDestroy method is called, Thread name: - "+ Thread.currentThread().getName());
    }


    public class MyAsynTask extends AsyncTask<Void,String,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG,"msg: onPreExecute method is called, Thread name: - "+ Thread.currentThread().getName());
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.i(TAG,"msg: doInBackground method is called, Thread name: - "+ Thread.currentThread().getName());

            int ctr=0;
            while(ctr<12){

                try {
                    publishProgress("progress is updated to - "+ctr);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ctr++;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Toast.makeText(MyService.this,""+values[0],Toast.LENGTH_SHORT).show();
            Log.i(TAG,"msg: onProgressUpdate method is called, Thread name: - "+ Thread.currentThread().getName());
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            Toast.makeText(MyService.this,"Task finished !",Toast.LENGTH_SHORT).show();
            Log.i(TAG,"msg: onPostExecute method is called, Thread name: - "+ Thread.currentThread().getName());
        }
    }
}
