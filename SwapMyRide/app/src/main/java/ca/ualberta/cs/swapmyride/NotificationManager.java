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
package ca.ualberta.cs.swapmyride;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by adrianomarini on 2015-11-04.
 */
public class NotificationManager {

    private ArrayList<Trade> tradesToBeNotified = new ArrayList<>();
    private ArrayList<String> friendRequests = new ArrayList<>();

    public NotificationManager() {
    }

    public Boolean notifyTrade(Trade trade){
        tradesToBeNotified.add(trade);
        return true;
    }

    public Boolean notifyFriendRequest(String username){
        friendRequests.add(username);
        return true;
    }

    //http://stackoverflow.com/questions/2115758/how-to-display-alert-dialog-in-android
    // Author: David Hedlund
    // Modified and Accessed 4 November 2015
    public void showNotification(Context context){
        Integer size = tradesToBeNotified.size();
        String trades = Integer.toString(size);
        new AlertDialog.Builder(context)
                .setTitle("New Trade!")
                .setMessage("You have " + trades + " new trades pending! Go to Active Trades to view!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                })
                .show();
    }

    public void showFriendRequest(final Context context, final String username){
        new AlertDialog.Builder(context)
                .setTitle("New Friend!")
                .setMessage(username + "is now following you! Click view to see their profile!")
                .setPositiveButton("View Profile", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, AddFriendProfileActivity.class);
                        intent.putExtra("Username", username);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                })
                .show();
    }

    public void notifyMe(Context context) {
        if (tradesToBeNotified.size() > 0) {
            showNotification(context);
            tradesToBeNotified.clear();
        }

        for(String username : friendRequests){
            showFriendRequest(context, username);
            friendRequests.clear();
        }

    }

}