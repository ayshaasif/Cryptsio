package com.example.myapplication2.models;

import java.util.Date;

public class Transaction {
    private  String SenderAddress;
    private String Receiver;
    private Integer reason;
    private Float amount;
    //private Date dateTime;
    public Transaction (){}

    public Transaction(String SenderAddress,String Receiver,Integer reason,Float amount){
        this.SenderAddress = SenderAddress;
        this.Receiver = Receiver;
        this.reason = reason;
        this.amount = amount;
        //this.time = time;
    }

    public String getSenderAddress() {
        return SenderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        SenderAddress = senderAddress;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
