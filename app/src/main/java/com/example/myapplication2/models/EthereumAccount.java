package com.example.myapplication2.models;

public class EthereumAccount {
    //these datas must be queried from the database
    private String Username;
    private  String PRIVATE_KEY;
    private  String SENDER;


    private String Receivers[];


    public String[] getReceivers() {
        return Receivers;
    }

    public void setReceivers(String[] receivers) {
        Receivers = receivers;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPrivateKey() {
        return PRIVATE_KEY;
    }

    public  void setPrivateKey(String privateKey) {
        this.PRIVATE_KEY = privateKey;
    }

    public  String getSENDER() {
        return SENDER;
    }


    public void setSENDER(String SENDER) {
        this.SENDER = SENDER;
    }
}
