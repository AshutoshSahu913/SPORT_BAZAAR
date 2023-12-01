package com.example.sportbazaar;

import android.os.Bundle;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sportbazaar.databinding.SettingsActivityBinding;

public class SettingsActivity extends AppCompatActivity {

    SettingsActivityBinding settingsActivityBinding;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.settings_activity);
        settingsActivityBinding = SettingsActivityBinding.inflate(getLayoutInflater());
        setContentView(settingsActivityBinding.getRoot());
        toolbar = findViewById(R.id.toolbar1);



    }

}