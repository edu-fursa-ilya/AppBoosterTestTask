package com.fursa.appbooster.network;

import android.net.Uri;

import com.fursa.appbooster.model.TaskModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Fursa Ilya on 09.11.17.
 * <p>
 * This class helps to parse raw
 * JSON from gists
 */

public class OffersFetcher {
    private static final String FETCHER_TAG = OffersFetcher.class.getSimpleName();

    /*
        This method will receive JSON as
        a raw string
     */
    public String getJson(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    /*
        This method will call parseItems method and load our items to List
     */
    public List<TaskModel> fetchItems() throws IOException, JSONException {
        List<TaskModel> modelList = new ArrayList<>();
        String url = Uri.parse("https://goo.gl/SVPYvu").buildUpon().build().toString();
        JSONObject jsonObject = new JSONObject(getJson(url));
        parseItems(modelList, jsonObject);
        return modelList;
    }
    /*

     */
    private void parseItems(List<TaskModel> modelList, JSONObject jsonObject) throws JSONException {
        JSONArray offersJsonObject = jsonObject.getJSONArray("offers");
        for (int i = 0; i < offersJsonObject.length(); i++) {
            JSONObject current = offersJsonObject.getJSONObject(i);
            //set fields to our object
            modelList.add(
                    new TaskModel(
                    current.getInt("id"),
                    current.getString("title"),
                    current.getString("description"),
                    current.getString("download_link"),
                    current.getString("icon"),
                    current.getDouble("reward"),
                    current.getString("apple_download_link"),
                    current.getString("apple_bundle_id"))
            );
        }
    }
}
