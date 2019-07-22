package com.example.anote.HomePage.AddPage.SelectLists;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.anote.AsyncTasks.GenericSearchAsyncTask;
import com.example.anote.R;

import java.net.MalformedURLException;
import java.net.URL;

public class FieldList extends AppCompatActivity {

    public String url = "https://apianote.000webhostapp.com/select.php";

    public final String LOG_TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_list);

        GenericSearchAsyncTask asyncTask = new GenericSearchAsyncTask(this);
        URL Url = createUrl(url);
        asyncTask.execute(Url);
    }

    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }
}
