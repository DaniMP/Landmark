package com.example.landmark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static String CONSTANT_EXTRA_TEXT = "extraText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ((TextView)this.findViewById(R.id.TextView2)).setText(getIntent().getStringExtra(CONSTANT_EXTRA_TEXT));


    }
}
