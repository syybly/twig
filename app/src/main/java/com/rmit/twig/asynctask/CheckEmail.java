package com.rmit.twig.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.rmit.twig.view.Activity_Preference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CheckEmail extends AsyncTask<String, String, String> {
    private Context context;
    private ProgressDialog pd;

    public CheckEmail(Context context) {
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
            connection.connect();
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
                JSONObject emailcheck=new JSONObject(result);
                boolean exists=emailcheck.getBoolean("emailExisted");
                if(!exists){
                    Intent intent=new Intent(context, Activity_Preference.class);
                    context.startActivity(intent);
                }
                else{
                    Toast wrongemail = Toast.makeText(context, "Email already exists", Toast.LENGTH_SHORT);
                    wrongemail.show();
                }
            } catch (JSONException e) {

            }
        else {
            Toast nomatch = Toast.makeText(context, "Something went wrong, please try again", Toast.LENGTH_SHORT);
            nomatch.show();
        }
    }
}
