package com.rmit.twig.com;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.IOException;

public class AsyncTask_Post extends AsyncTask <Object, String, String> {
    @Override
    protected String doInBackground(Object... objects) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addPart("file", new FileBody((File) objects[2]));
        HttpEntity entity = builder.build();
        HttpPost request = new HttpPost("https://twig-api-v2.herokuapp.com/posts");
        request.setEntity(entity);

        HttpClient client = HttpClientBuilder.create().build();

        try {
            client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
