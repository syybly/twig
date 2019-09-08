package com.rmit.twig.com;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.User;
import com.rmit.twig.view.Activity_Homepage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class SignInAsyncTask extends AsyncTask<String, String, String> {
    private Context context;
    public static ProgressDialog pd;
    private Map<String, List<String>> headers;

    public SignInAsyncTask(Context context) {
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
        HttpsURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept","application/json");
            connection.setDoOutput(true);
            connection.connect();

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(params[1]);

            int status=connection.getResponseCode();
            headers = connection.getHeaderFields();

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
            String token=headers.get("x-auth").get(0);
            User newuser=new User(id,email,name,interests);
//            DataHolder.user=newuser;
//            DataHolder.user.setToken(token);
            newuser.setToken(token);
            DataHolder.users.put(id,newuser);
            DataHolder.currentuser=id;
            GetPostListAsyncTask getPostListAsyncTask=new GetPostListAsyncTask(context);
            getPostListAsyncTask.execute();
            SignInAsyncTask.pd.dismiss();


        } catch (JSONException e) {

        }

        else {
            if (pd.isShowing()){
            pd.dismiss();
        }
            Toast nomatch = Toast.makeText(context, "Invalid Credentials!", Toast.LENGTH_SHORT);
            nomatch.show();
        }

    }
}
