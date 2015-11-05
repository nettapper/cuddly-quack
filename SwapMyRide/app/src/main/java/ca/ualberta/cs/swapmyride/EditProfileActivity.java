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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    UserSingleton userSingleton;
    User user;
    Button save;
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        user = userSingleton.getCurrentUser();

        TextView usernameTextView = (TextView) findViewById(R.id.usernameField);
        usernameTextView.setText(user.getUserName().toString());

        usernameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditProfileActivity.this, "Cannot edit username", Toast.LENGTH_SHORT).show();
            }
        });

        final TextView nameTextView = (TextView) findViewById(R.id.nameField);
        nameTextView.setText(user.getName());

        final TextView emailTextView = (TextView) findViewById(R.id.emailField);
        emailTextView.setText(user.getUserEmail());

        final TextView addressTextView = (TextView) findViewById(R.id.editText2);
        addressTextView.setText(user.getUserAddress());

        save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setName(nameTextView.getText().toString());
                user.setUserEmail(emailTextView.getText().toString());
                user.setUserAddress(addressTextView.getText().toString());

                dataManager = new DataManager(EditProfileActivity.this);
                dataManager.saveUser(user);

                finish();
            }
        });
    }


}
