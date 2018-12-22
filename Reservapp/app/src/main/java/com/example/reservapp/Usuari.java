package com.example.reservapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class Usuari extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuari);

        Intent intent = getIntent();

        String telf = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(telf);

        String contra = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.textView);
        textView.setText(contra);
    }
}
