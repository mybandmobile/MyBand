package com.myband.myband.asyncTask;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;
import com.myband.myband.model.User;

import org.parceler.Parcels;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ranie on 30 de mai.
 */

public class UserTask extends AsyncTask<Bundle, Void, User> {

    @Override
    protected User doInBackground(Bundle... params) {
        OkHttpClient client = new OkHttpClient();
        String host = "http://myband.ddns.net/rest/user";
        //final String host = "http://192.168.15.6:8080/myband/rest/user";
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();

        User user = (User) Parcels.unwrap(params[0].getParcelable("user"));
        int cod = (Integer) params[0].get("cod");

        switch (cod) {
            case User.loginAccount:
                host += "/login";
                break;
            case User.createAccount:
                host += "/createuser";
                break;
            case User.updateAccount:
                host += "/updateuser";
                break;
            case User.deleteAccount:
                host += "/deleteuser";
                break;
        }


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
