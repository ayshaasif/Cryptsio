package com.example.myapplication2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.models.EthereumAccount;
import com.example.myapplication2.models.Transaction;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{
    private  String RECEPIENT;// =  "0x651007140721e8e5eE838207b226e236C40186B2";
    private String PRIVATE_KEY= "0996883b1f59e187ae68c13c64f41eec35770b3e9f8bba8b8fb696d411f46094";
    private String SENDER;// = "0xF7dccE362936fd69cADddfAfAC61D409252f8916";
    private FirebaseFirestore dbfs;
    EditText noEtherTxt,account1,account2;
    String user;
    Button btn;
    int Ether;
    SendEther s ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectAll().detectAll().penaltyLog().build();
        StrictMode.setThreadPolicy(policy);

        EthereumAccount ethereumAccount = new EthereumAccount();
        ethereumAccount.setSENDER("0x6FA3a9039116C63A8652f927C01A17dA9297b063");
        ethereumAccount.setPrivateKey("0996883b1f59e187ae68c13c64f41eec35770b3e9f8bba8b8fb696d411f46094");

        dbfs  =  FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Intent reciverIntent = getIntent();
        String receiveBalance = reciverIntent.getStringExtra("Balance");
        String receiveName = reciverIntent.getStringExtra("name");

        TextView balancetransaction = (TextView) findViewById(R.id.balanceTransaction);
        TextView acctName = (TextView) findViewById(R.id.acctName);
        balancetransaction.setText(receiveBalance);
        acctName.setText(receiveName);

        account1 = findViewById(R.id.account1);
        account2 = findViewById(R.id.account2);
        noEtherTxt = findViewById(R.id.noEthers);
        account1.setText(ethereumAccount.getSENDER());

        btn = findViewById(R.id.sendBtn);

        btn.setOnClickListener(view -> {
            Ether = Integer.parseInt(noEtherTxt.getText().toString());
            RECEPIENT = account2.getText().toString();
            Toast.makeText(getApplicationContext(),RECEPIENT,Toast.LENGTH_LONG).show();
            Log.d("recepient address: ",RECEPIENT);
            System.out.println("Recepient : "+RECEPIENT);
            new SendEther(RECEPIENT,Ether,PRIVATE_KEY);
             Toast.makeText(getApplicationContext(),"transaction processing",Toast.LENGTH_LONG).show();
            saveTransaction();
            Intent intent = new Intent(MainActivity.this,MiniStatement.class);
            startActivity(intent);

        });

    }

    public void saveTransaction(){

        //map is a key value pair
        Map<String, Object> txnM = new HashMap<>();
        txnM.put("private key",PRIVATE_KEY);
        txnM.put("sender's address",SENDER);
        txnM.put("receiver's address",RECEPIENT);
        txnM.put("Amount",Ether);
        txnM.put("user_id",user);

        dbfs.collection("users").document(user)
                .collection("Transactions").document().set(txnM)
                .addOnSuccessListener(suc->{
                Toast.makeText(this,"record is inserted dbfs",Toast.LENGTH_LONG).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this,er.getMessage(),Toast.LENGTH_SHORT).show();
            });

    }


}