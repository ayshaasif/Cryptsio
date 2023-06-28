package com.example.myapplication2;

import com.example.myapplication2.models.EthereumAccount;
import com.example.myapplication2.models.Transaction;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseCryptsio {

    private DatabaseReference databaseReference;


    public static FirebaseDatabase getDb() {
        return db;
    }

    private static FirebaseDatabase db;

    public DatabaseCryptsio(){
        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Transaction.class.getSimpleName());
    }

    public  DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public Task<Void> add(Transaction tx){
        return databaseReference.push().setValue(tx);
    }

    public Task<Void> add(EthereumAccount ethAcc){
        databaseReference = db.getReference(EthereumAccount.class.getSimpleName());
        return databaseReference.push().setValue(ethAcc);
    }



}
