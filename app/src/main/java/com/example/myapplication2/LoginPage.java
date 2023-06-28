package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity {
    EditText usrEml,emlPasswrd;
    Button lgnBtn ,regBtn;
    FirebaseAuth mAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        usrEml = findViewById(R.id.UserEmail);
        emlPasswrd = findViewById(R.id.EmailPasswrd);
        regBtn = findViewById(R.id.RegisterUser);
        lgnBtn = findViewById(R.id.LoginUser);
        mAuth = FirebaseAuth.getInstance();

        lgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPage.this,RegistrationPage.class));            }
        });
    }

    private void loginUser(){
        String email = usrEml.getText().toString();
        String password = emlPasswrd.getText().toString();


        if(TextUtils.isEmpty(email)){
            usrEml.setError("Email cannot be empty");
            usrEml.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            emlPasswrd.setError("password cannot be empty");
            emlPasswrd.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginPage.this,HomePage.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"Login Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}

