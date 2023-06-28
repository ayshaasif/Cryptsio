package com.example.myapplication2;

import android.util.Log;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.ExecutionException;

public class Main {
    String msg;
    public static Web3j web3j;
    Main(){
        Log.e("client info ","Before Connection: ");


//        web3j = Web3j.build(new HttpService("HTTP://172.17.96.63:7545"));
//        web3j = Web3j.build(new HttpService("HTTP://192.168.0.112:7545"));
        web3j = Web3j.build(new HttpService("HTTP://0.0.0.0:7545"));

        Log.e("client info ","Trying to connect");



        Web3ClientVersion web3ClientVersion = new Web3ClientVersion();
        try {

            web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e("client info ","nOT CONNECTED ");

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String web3ClientVersionString = web3ClientVersion.getWeb3ClientVersion();
        this.msg =  "This is the version: "+web3ClientVersionString;
        System.out.println("this is the msg:"+msg);
        Log.e("client info ","Client Version: "+ web3ClientVersionString+ "\n");
    }

    public  Web3j getConnection(){
        return web3j;
    }

}
