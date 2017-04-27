package com.example.flo.kf;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    public EditText username;
    public EditText password;
    public EditText mail;
    public TextView introduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        username = (EditText) findViewById(R.id.registrationUsernameInput);
        password = (EditText) findViewById(R.id.registrationPasswordInput);
        mail = (EditText) findViewById(R.id.registrationMailInput);
        introduction = (TextView) findViewById(R.id.registrationIntro);
        TextContent introductionContent = new TextContent();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle("Registrierung");
        introduction.setText(introductionContent.registrationMainInfo);
    }



    public void finishRegistration(View complete){
        String usernameOutput;
        String passwordOutput;
        String mailOutput;

        usernameOutput=username.getText().toString();
        passwordOutput=password.getText().toString();
        mailOutput=mail.getText().toString();

        FirebaseQueries q = new FirebaseQueries();
        q.register(usernameOutput, passwordOutput, mailOutput, this);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
