package com.tbcmad.todoapp.activity.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tbcmad.todoapp.R;
import com.tbcmad.todoapp.activity.MainActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button mRegisterButton;
    private EditText mUsernameEt, mPasswordEt, mEmailEt;
    private AuthViewModel mAuthVm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mRegisterButton = findViewById(R.id.register_btn);
        mUsernameEt = findViewById(R.id.username_et);
        mPasswordEt = findViewById(R.id.password_et);
        mEmailEt = findViewById(R.id.email_et);
        mAuthVm = new ViewModelProvider(this).get(AuthViewModel.class);

        findViewById(R.id.login_btn).setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.register_btn:
                handleRegister();
                break;
            case R.id.login_btn:
                handleLoginRequest();
                break;
            default:
        }
    }

    private void handleLoginRequest() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void handleRegister() {

        String username = getStringFromEt(mUsernameEt);
        String password = getStringFromEt(mPasswordEt);
        String email = getStringFromEt(mEmailEt);

        if (username.trim().length() == 0) {
            mUsernameEt.setError("Please enter username");
        }

        if (password.trim().length() < 10) {
            mPasswordEt.setError("Please enter a password with minimum 10 characters");
        }

        if (email.trim().length() == 0) {
            mEmailEt.setError("Please enter a valid email");
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailEt.setError("Entered email is not valid.");
        }

        long id = mAuthVm.registerUser(username, password, email);

        if (id <= 0) {
            Toast.makeText(this, "Error while creating account.", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuthVm.storeUidInSharedPrefs(id);

        Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }

    private void displayEtErrors() {

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