package com.tbcmad.todoapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.tbcmad.todoapp.R;
import com.tbcmad.todoapp.fragments.EditTodoFragment;

public class EditActivity extends BaseActivityWithHomeButton {

    FragmentManager fragmentManager;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setHomeButtonEnabled(true);

        fragmentManager = getSupportFragmentManager();
        fragment = new EditTodoFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.edit_activity_container, fragment)
                .commit();
    }
}