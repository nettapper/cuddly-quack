/*
 * Copyright 2015 Adriano Marini, Carson McLean, Conner Dunn, Daniel Haberstock, Garry Bullock
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.ualberta.cs.swapmyride.Misc;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.UnsupportedEncodingException;

import ca.ualberta.cs.swapmyride.Model.Photo;

/**
 * Given a photo and a url the photo is attempted to be saved to the server
 *
 * Created by Garry on 2015-11-29.
 */
public class SavePhotoRunnable implements Runnable {
    private Photo photo;
    private String url;
    private Gson gson = new Gson();

    //private
    public SavePhotoRunnable(final Photo photo, final String url) {
        this.photo = photo;
        this.url = url + "photos/" + photo.getUid().getID();
        Log.i("NetworkDataManager", this.url);
    }

    /* Based on https://github.com/rayzhangcl/ESDemo and https://github.com/joshua2ua/AndroidElasticSearch */
    public void run() {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpAdd = new HttpPost(url);
        HttpResponse response;
        StringEntity stringEntity = null;

        try {
            stringEntity = new StringEntity(gson.toJson(photo));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        httpAdd.setEntity(stringEntity);
        httpAdd.setHeader("Accept", "application/json");

        //possible IO Error and an HTTP apache error
        try {
            response = httpClient.execute(httpAdd);
            String status = response.getStatusLine().toString();
            Log.i("NetworkDataManager", status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
