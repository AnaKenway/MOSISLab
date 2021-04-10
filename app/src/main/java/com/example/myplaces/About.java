package com.example.myplaces;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;

public class About extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        Button ok=(Button)findViewById(R.id.about_ok);
        ok.setOnClickListener(view -> finish());
    }
}
