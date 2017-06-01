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

public class LoginTask extends AsyncTask<User, Void, User> {

    @Override
    protected User doInBackground(User... params) {
        OkHttpClient client = new OkHttpClient();
        final String ipv4 = "http://192.168.15.5";
        String urlAPI;
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();
        User user = params[0];

        if (user.getUserName() == null) {
            urlAPI = ipv4 + ":8080/myband/rest/user/login";
        } else {
            urlAPI = ipv4 + ":8080/myband/rest/user/createuser";
        }

        try {
            String jsonString = gson.toJson(user, User.class);

            RequestBody body = RequestBody.create(json, jsonString);
            Request request = new Request.Builder().url(urlAPI).post(body).build();
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
