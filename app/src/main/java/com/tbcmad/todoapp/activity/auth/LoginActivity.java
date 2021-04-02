package com.tbcmad.todoapp.activity.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tbcmad.todoapp.R;
import com.tbcmad.todoapp.activity.MainActivity;
import com.tbcmad.todoapp.model.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mUsernameEt, mPasswordEt;
    private AuthViewModel mAuthVm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameEt = findViewById(R.id.username_et);
        mPasswordEt = findViewById(R.id.password_et);
        mAuthVm = new ViewModelProvider(this).get(AuthViewModel.class);

        findViewById(R.id.login_btn).setOnClickListener(this);
        findViewById(R.id.register_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_btn:
                handleLogin();
                break;
            case R.id.register_btn:
                handleRegisterRequest();
            default:
        }
    }

    private void handleRegisterRequest() {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
        finish();
    }

    private void handleLogin() {


        String username = getStringFromEt(mUsernameEt);
        String password = getStringFromEt(mPasswordEt);

        if (username.trim().length() == 0) {
            mUsernameEt.setError("Please enter username");
        }

        if (password.trim().length() < 10) {
            mPasswordEt.setError("Please enter a password with minimum 10 characters");
        }

        User user = mAuthVm.validateUser(username, password);

        if (user == null) {
            mPasswordEt.setError("Incorrect username or password. Try again!");
            return;
        }

        mAuthVm.storeUidInSharedPrefs(user);

        Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();

        Intent intent= new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean doesEtHaveText(EditText et) {
        CharSequence text = et.getText();
        if (text != null && text.length() > 0) {
            return true;
        }
        return false;
    }

    private String getStringFromEt(EditText et) {
        CharSequence text = et.getText();
        if (text != null && text.length() > 0) {
            return text.toString();
        }
        return "";
    }
}