package com.example.flo.kf;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OverviewActivity extends AppCompatActivity {

    LinearLayout layout;
    TextView introduction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        layout=(LinearLayout) findViewById(R.id.activity_overview);
        introduction=(TextView) findViewById(R.id.overviewIntroduction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle("Themenübersicht");
        TextContent overviewText = new TextContent();
        introduction.setText(overviewText.overviewMainInfo);

        FirebaseQueries queries = new FirebaseQueries();
        queries.checkQuestions(this, layout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
