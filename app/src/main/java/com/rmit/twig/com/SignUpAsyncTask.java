package com.rmit.twig.com;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.rmit.twig.Controller.DataHolder;
import com.rmit.twig.Model.User;
import com.rmit.twig.View.HomepageActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SignUpAsyncTask extends AsyncTask<String, String, String> {
    private Context context;
    private ProgressDialog pd;

    public SignUpAsyncTask(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();
    }

    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept","application/json");
            connection.setDoOutput(true);
            connection.connect();

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(params[1]);

            int status=connection.getResponseCode();

            if (status==400) {
                return null;
            }
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            return buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (pd.isShowing()){
            pd.dismiss();
        }
        if(result!=null)
            try {
                JSONObject user=new JSONObject(result);
                JSONArray jsonArray=user.getJSONArray("interests");
                String id=user.getString("_id");
                String name=user.getString("name");
                String email=user.getString("email");
                ArrayList<String> interests=new ArrayList<>();
                for (int i=0;i<jsonArray.length();i++) {
                    interests.add(jsonArray.get(i).toString());
                }
                User newuser=new User(id,email,name,interests);
                DataHolder.user=newuser;
                Intent intent = new Intent(context, HomepageActivity.class);
                context.startActivity(intent);
            } catch (JSONException e) {

            }
        else {
            Toast nomatch = Toast.makeText(context, "Something went wrong, please try again.", Toast.LENGTH_SHORT);
            nomatch.show();
        }
    }
}
