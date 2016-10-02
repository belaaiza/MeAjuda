package com.mathheals.meajuda.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mathheals.meajuda.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Sets on click listener on login button
        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        //Gets the text on email and password
        EditText emailField = (EditText) findViewById(R.id.emailField);
        EditText passwordField = (EditText) findViewById(R.id.passwordField);


    }
}
