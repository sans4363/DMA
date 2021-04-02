package com.tbcmad.todoapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tbcmad.todoapp.R;
import com.tbcmad.todoapp.activity.auth.AuthViewModel;
import com.tbcmad.todoapp.activity.auth.LoginActivity;
import com.tbcmad.todoapp.fragments.ListTodoFragment;
import com.tbcmad.todoapp.viewModel.TodoListViewModel;
import com.tbcmad.todoapp.view_holders.TodoListViewHolder;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    ListTodoFragment fragment;
    FloatingActionButton floatingActionButton;
    private TodoListViewModel mTodoVm;
    private long mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragment = new ListTodoFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.list_activity_container, fragment)
                .commit();

        floatingActionButton = findViewById(R.id.floatingActionButton);
        mTodoVm = new ViewModelProvider(this).get(TodoListViewModel.class);
        mUserId = AuthViewModel.getUserId(this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_delete_all:
                fragment.getAdapter().deleteAllTodos();
                break;
            case R.id.mnu_delete_cpmpleted:
                fragment.getAdapter().deleteAllCompletedTodos();
                break;
            case R.id.mnu_logout:
                AuthViewModel.logout(this);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}