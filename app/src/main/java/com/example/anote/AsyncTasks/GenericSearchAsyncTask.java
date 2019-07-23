package com.example.anote.AsyncTasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anote.Adapters.GenericSearchAdapter;
import com.example.anote.Adapters.HistorySearchAdapter;
import com.example.anote.HomePage.AddPage.AddPage;
import com.example.anote.HomePage.AddPage.SelectLists.AddPageSearchList;
import com.example.anote.MainActivity;
import com.example.anote.R;
import com.example.anote.SearchPage.SearchAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class GenericSearchAsyncTask extends AsyncTask<URL, Void, ArrayList<String>> {

    private WeakReference<Context> contextRef;

    public GenericSearchAsyncTask(Context context) { contextRef = new WeakReference<>(context); }

    @Override
    protected ArrayList<String> doInBackground(URL... urls) {
        String jsonResponse = "";
        try{
            jsonResponse = makeHttpRequest(urls[0]);
        } catch(IOException e){
            Log.e(MainActivity.LOG_TAG, "problem communicating with the server");
        }

        return ExtractFromJson(jsonResponse);
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        if(strings == null) return;

        final Context context = contextRef.get();

        LinearLayout suggestions = ((Activity)context).findViewById(R.id.lyt_generic_fields_suggestion);

        RecyclerView fieldsRV = ((Activity)context).findViewById(R.id.fields_list_suggestion_recycler_view);
        fieldsRV.setHasFixedSize(true);
        fieldsRV.setLayoutManager(new LinearLayoutManager(context));
        final GenericSearchAdapter adapter = new GenericSearchAdapter(context, strings);
        fieldsRV.setAdapter(adapter);

        adapter.setOnItemClickListener(new GenericSearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String str, int position) {
                Intent intent = new Intent();
                intent.putExtra("name", str);
                ((Activity) context).setResult(Activity.RESULT_OK,intent);
                ((Activity) context).finish();
            }
        });

        initSearchView(context, adapter, suggestions);

    }

    private void initSearchView(final Context context, final GenericSearchAdapter adapter,final LinearLayout suggestions){
        TextInputEditText searchView = ((Activity)context).findViewById(R.id.fields_list_search_bar);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.equals("")){
                    suggestions.setVisibility(View.GONE);
                } else {
                    suggestions.setVisibility(View.VISIBLE);
                    adapter.filter(charSequence.toString(), suggestions);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private String makeHttpRequest(URL url)throws IOException {
        String jsonResponse = null;

        if(url == null) return jsonResponse;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(MainActivity.LOG_TAG, "problem in connecting to url," +
                        " Response Code= "+urlConnection.getResponseCode());
            }

        } catch(IOException e) {
            Log.e(MainActivity.LOG_TAG, "HttpUrlConnection Error");
        } finally {
            if(urlConnection != null)
                urlConnection.disconnect();
            if(inputStream != null)
                inputStream.close();
        }

        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    // fix this to get attribute name from these kind of tables : [teacher, university, field, lesson]
    private ArrayList<String> ExtractFromJson(String jsonResponse){

        if(TextUtils.isEmpty(jsonResponse)) return null;

        ArrayList<String> strings = new ArrayList<>();

        try{
            JSONArray root = new JSONArray(jsonResponse);
            for(int i=0; i<root.length(); i++){
                JSONObject object = root.getJSONObject(i);
                String name = object.getString("NAME");
                strings.add(name);
            }
            return strings;
        } catch (JSONException e) {
            Log.e(MainActivity.LOG_TAG, "problem extracting jsonResponse");
        }
        return null;
    }

}


