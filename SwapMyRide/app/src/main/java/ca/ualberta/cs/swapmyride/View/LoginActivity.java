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
package ca.ualberta.cs.swapmyride.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ca.ualberta.cs.swapmyride.Controller.DataManager;
import ca.ualberta.cs.swapmyride.Controller.LocalDataManager;
import ca.ualberta.cs.swapmyride.Controller.NetworkDataManager;
import ca.ualberta.cs.swapmyride.Controller.UserController;
import ca.ualberta.cs.swapmyride.Misc.DefaultPhotoSingleton;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.R;

/**
 * LoginActivity allows the user to enter the app as a specific username
 * <p/>
 * It takes in input through a username field, and determines if the username
 * exists. If so, it loads that user object into the UserSingleton. If not,
 * it shows an error and encourages the user to sign up
 */

public class LoginActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText usernameField;
    Button signIn;
    TextView signUp;
    String username;
    NetworkDataManager dm;
    LocalDataManager ldm;
    DataManager dataManager;
    UserController uController;

    /**
     * onCreate creates the login box and the button.
     * <p/>
     * Upon click, the username is checked against the
     * database of users to see if it exists. If so, the
     * user is added to the UserSingleton. If not, a Toast
     * message appears, and the user is encouraged to sign up.
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        DefaultPhotoSingleton.getInstance().init(getApplicationContext());

        getSupportActionBar().setTitle(R.string.title_activity_login);

        usernameField = (EditText) findViewById(R.id.usernameField);

        signIn = (Button) findViewById(R.id.signIn);

        signUp = (TextView) findViewById(R.id.signUp);

        dm = new NetworkDataManager();
        ldm = new LocalDataManager(LoginActivity.this);
        dataManager = new DataManager(getApplicationContext());
        uController = new UserController(getApplicationContext());

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameField.getText().toString();
                User user;
                if (dm.searchUser(username)) {
                    user = dm.retrieveUser(username);
                    uController.addCurrentUser(user);
                    dataManager.updateFriends();
                    Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                    startActivity(intent);
                    finish();
                } else if (ldm.searchUser(username)) {
                    uController.addCurrentUser(ldm.loadUser(username));
                    Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, username + " Not Found", Toast.LENGTH_LONG).show();
                }
            }
        });


        /**
         * This onClick listener sends the user to the SignUpActivity if they click the link.
         */
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataManager.networkAvailable()) {
                    Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "You must be online to sign up!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public Button getSignIn() {
        return signIn;
    }

    public EditText getUsernameField() {
        return usernameField;
    }

    public TextView getSignUp() {
        return signUp;
    }
}
