package com.example.myapplication2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.example.myapplication2.models.Transaction;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MiniStatement extends AppCompatActivity {

    RecyclerView recyclerView;
    DocumentReference docRef;
    public Task<QuerySnapshot> colRef;
    private FirebaseFirestore dbfs;
    String user;

    MyAdapter myAdapter;
    ArrayList<Transaction> datalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_statement);

        recyclerView = findViewById(R.id.TransactionList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dbfs  =  FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        datalist = new ArrayList<>();
        myAdapter = new MyAdapter(datalist);
        recyclerView.setAdapter(myAdapter);
        loadTransaction();

    }

    public void loadTransaction(){
       dbfs.collection("users").document(user)
                .collection("Transactions").get()
               .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d: list){
                    Transaction obj = d.toObject(Transaction.class);
                    datalist.add(obj);
                }
                myAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG,"on Failure: ",e);
            }
        });


    }
}