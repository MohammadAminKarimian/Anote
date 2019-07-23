package com.example.anote.AsyncTasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.example.anote.MainActivity;

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

public class SearchAsyncTask extends AsyncTask<URL, Void, ArrayList<String>> {

    private WeakReference<Context> contextRef;

    SearchAsyncTask(Context context) { contextRef = new WeakReference<>(context); }

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

        Context context = contextRef.get();

//        RecyclerView fieldsRV = ((Activity)context).findViewById(R.id.fields_recycler_view);
//        fieldsRV.setHasFixedSize(true);
//        fieldsRV.setLayoutManager(new LinearLayoutManager(context));
//        fieldsRV.setAdapter(new GenericSearchAdapter(context, strings));

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

    // fix this to get attribute name from these to kind of tables : [teacher, university, field, lesson]
    private ArrayList<String> ExtractFromJson(String jsonResponse){

        if(TextUtils.isEmpty(jsonResponse)) return null;

        ArrayList<String> strings = new ArrayList<>();

        try{
            JSONArray root = new JSONArray(jsonResponse);
            for(int i=0; i<root.length(); i++){
                JSONObject field = root.getJSONObject(i);
                String name = field.getString("FIELDS");
                int _id = Integer.parseInt(field.getString("ID"));
                strings.add(name);
            }
            return strings;
        } catch (JSONException e) {
            Log.e(MainActivity.LOG_TAG, "problem extracting jsonResponse");
        }
        return null;
    }

}


