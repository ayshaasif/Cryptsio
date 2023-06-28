package com.example.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.models.Transaction;

import java.util.ArrayList;

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.MyViewHolder>{

     ArrayList<Transaction> datalist;

    public MyAdapter(ArrayList<Transaction> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.sndr.setText(datalist.get(position).getSenderAddress());
            holder.rcvr.setText(datalist.get(position).getReceiver());
            holder.amt.setText(datalist.get(position).getAmount().toString());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txid,sndr,rcvr,amt;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            txid = itemView.findViewById(R.id.txidfromdb);
            sndr = itemView.findViewById(R.id.senderfromdb);
            rcvr = itemView.findViewById(R.id.receiverfromdb);
            amt = itemView.findViewById(R.id.amountfromdb);
            
        }
    }
}
