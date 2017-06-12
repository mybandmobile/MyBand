package com.myband.myband.asyncTask;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;
import com.myband.myband.model.Event;

import org.parceler.Parcels;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by SuporteTécnico on 11/06/2017.
 */

public class EventoTask extends AsyncTask<Bundle,Void,Event> {
    @Override
    protected Event doInBackground(Bundle... params) {
        OkHttpClient client = new OkHttpClient();
        String host = "http://myband.ddns.net/rest/event";
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();

        Event event = (Event) Parcels.unwrap(params[0].getParcelable("event"));
        int cod = (Integer) params[0].get("cod");

        switch (cod) {
            case Event.createEvent:
                host += "/createevent";
                break;
            case Event.updateEvent:
                host += "/updateevent";
                break;
            case Event.deleteEvent:
                host += "/deleteevent";
                break;
        }
        try {
            String jsonString = gson.toJson(event, Event.class);

            RequestBody body = RequestBody.create(json, jsonString);
            Request request = new Request.Builder().url(host).post(body).build();
            Response response = client.newCall(request).execute();

            jsonString = response.body().string();
            event = gson.fromJson(jsonString, Event.class);
            return event;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
