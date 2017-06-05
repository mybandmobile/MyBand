package com.myband.myband.asyncTask;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.myband.myband.model.User;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ranie on 30 de mai.
 */

public class CreateUserTask extends AsyncTask<User, Void, User> {

    @Override
    protected User doInBackground(User... params) {
        OkHttpClient client = new OkHttpClient();
        final String host = "http://myband.ddns.net/myband/rest/user/createuser";
        //final String host = "http://192.168.15.6:8080/myband/rest/user/createuser";
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();
        User user = params[0];

        try {
            String jsonString = gson.toJson(user, User.class);

            RequestBody body = RequestBody.create(json, jsonString);
            Request request = new Request.Builder().url(host).post(body).build();
            Response response = client.newCall(request).execute();

            jsonString = response.body().string();
            user = gson.fromJson(jsonString, User.class);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
