package com.sustuso.caralarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Options");
    }
    public boolean onOptionsItemSelected(MenuItem item){ // Tocco pulsante indietro
        finish();
        return true;

    }
}
