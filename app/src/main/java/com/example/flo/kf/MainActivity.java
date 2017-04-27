package com.example.flo.kf;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public TextView registration;
    public EditText username;
    public EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        registration = (TextView) findViewById(R.id.registrationInfo);
        username = (EditText) findViewById(R.id.usernameInput);
        password = (EditText) findViewById(R.id.passwordInput);

        setSupportActionBar(toolbar);

        toolbar.setTitle("Wilkommen");
        TextContent registrationText = new TextContent();
        registration.setText(registrationText.registrationInfo);
    }



    public void login(View login){
        String passwordOutput;
        String usernameOutput;

        passwordOutput=password.getText().toString();
        usernameOutput=username.getText().toString();
        FirebaseQueries q = new FirebaseQueries();
        q.checkLogin(usernameOutput, passwordOutput, this);
    }



    public void register(View register){
        Intent systemRegistration = new Intent(this, RegistrationActivity.class);
        startActivity(systemRegistration);

    }


}
