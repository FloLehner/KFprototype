package com.example.flo.kf;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class QuestionActivity extends AppCompatActivity {
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar4);
        layout = (LinearLayout) findViewById(R.id.activity_question);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle idContent = getIntent().getExtras();
        String questionId = idContent.getString("id");
        FirebaseQueries queries = new FirebaseQueries();
        queries.getQuestions(questionId, this, layout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
