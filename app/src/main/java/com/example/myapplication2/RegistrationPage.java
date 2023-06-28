package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationPage extends AppCompatActivity {
    EditText regUser,regPasswrd;
    Button regBtn,lgnBtn;


    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        regUser = findViewById(R.id.regEmail);
        regPasswrd = findViewById(R.id.regPasswrd);
        regBtn = findViewById(R.id.RegisterUser);
        lgnBtn = findViewById(R.id.LoginUser);

        mAuth = FirebaseAuth.getInstance();
        regBtn.setOnClickListener(view ->{
            createUser();
        });
        lgnBtn.setOnClickListener(view->{
            startActivity(new Intent(RegistrationPage.this,LoginPage.class));
        });

    }

    private void createUser(){
        String email = regUser.getText().toString();
        String password = regPasswrd.getText().toString();


        if(TextUtils.isEmpty(email)){
            regUser.setError("Email cannot be empty");
            regUser.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            regPasswrd.setError("password cannot be empty");
            regPasswrd.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Registration Successful", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(RegistrationPage.this,LoginPage.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"Registration Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}