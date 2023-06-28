package com.example.myapplication2;

import android.util.Log;
import android.widget.Toast;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Balance extends Main {
    String msgBalance;
    Web3j client;
    EthGetBalance balanceResponse = new EthGetBalance();
    public static String ethAddress;
    public BigDecimal scaledBalance = new BigDecimal(0000000000000L);
    Balance(){
//        ethAddress = "0x18BFb27AE33185892878399250888A11ACea4e9e";
//        ethAddress ="0xF7dccE362936fd69cADddfAfAC61D409252f8916";
//        ethAddress = "0x761bC4a672B40aDF521507e880b86B56E879F134"; uni wifi
        ethAddress = "0x6FA3a9039116C63A8652f927C01A17dA9297b063";
        client = getConnection();

        try {

           balanceResponse= client.ethGetBalance(ethAddress, DefaultBlockParameter.valueOf("latest")).sendAsync().get(10, TimeUnit.SECONDS);
            BigInteger unscaledBalance = balanceResponse.getBalance();
            msgBalance = "This is the balance: "+unscaledBalance;
            scaledBalance = new BigDecimal(unscaledBalance)
                    .divide(new BigDecimal(1000000000000000000L),18, RoundingMode.HALF_UP);
            Log.e("balance",unscaledBalance+"");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
