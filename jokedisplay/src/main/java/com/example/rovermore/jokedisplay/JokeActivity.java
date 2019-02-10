package com.example.rovermore.jokedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intent = getIntent();

        String jokeString = intent.getStringExtra("intent_joke_key");

        TextView jokeTextView = findViewById(R.id.tv_joke);
        jokeTextView.setText(jokeString);
    }
}
