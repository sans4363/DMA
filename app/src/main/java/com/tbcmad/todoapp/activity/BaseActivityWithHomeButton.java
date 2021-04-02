package com.tbcmad.todoapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tbcmad.todoapp.R;

public class BaseActivityWithHomeButton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}