package com.example.myapplication2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
public class HomePage extends AppCompatActivity{
    FirebaseAuth mAuth;
    Button btn ,logOutBtn;
    BigDecimal scaledbalance;
    Balance b;
    TextView tBalance,tAddress;
    MathContext m = new MathContext(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectAll().detectAll().penaltyLog().build();
        StrictMode.setThreadPolicy(policy);
        System.out.println("Connecting....");
        tBalance = findViewById(R.id.Balance);
        tAddress =findViewById(R.id.address);

           b = new Balance();
          scaledbalance = (b.scaledBalance).round(m);
            tBalance.setText("Eth"+scaledbalance);
            tAddress.setText(b.ethAddress);



//        mAuth = FirebaseAuth.getInstance();
//        logOutBtn = findViewById(R.id.logOutBtn);
//        logOutBtn.setOnClickListener(view -> {
//            mAuth.signOut();
//            startActivity(new Intent(HomePage.this,LoginPage.class));
//        });

        btn = findViewById(R.id.chkButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),b.msgBalance, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomePage.this,MainActivity.class);
                intent.putExtra("Balance",""+scaledbalance);
                intent.putExtra("name", "Aysha Asif");
                startActivity(intent);
            }
        });

    }

//    @Override
//    protected void onStart(){
//        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user == null){
//            startActivity(new Intent(HomePage.this,LoginPage.class));
//        }
//    }

}