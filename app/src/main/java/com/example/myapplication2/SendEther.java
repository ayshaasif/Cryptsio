package com.example.myapplication2;

import android.os.StrictMode;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class SendEther extends Main{
    private final static BigInteger  GAS_LIMIT = BigInteger.valueOf(21000L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(200000000L);
    Web3j conn = web3j;

    SendEther(String RECEPIENT,int Amount,String PRIVATE_KEY){

        TransactionManager transactionManager = new RawTransactionManager(web3j,
                getCredentialsFromPrivateKey(PRIVATE_KEY)
        );

        Transfer transfer = new Transfer(web3j,transactionManager);
        try {
            TransactionReceipt transactionReceipt = transfer.sendFunds(
                    RECEPIENT,
                    BigDecimal.valueOf(Amount),
                    Convert.Unit.ETHER,
                    GAS_PRICE,
                    GAS_LIMIT
            ).sendAsync().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("------------------transaction failed------------------");
            e.printStackTrace();
        }
    }
    private Credentials getCredentialsFromWallet() throws IOException, CipherException {
        Credentials credentials = WalletUtils.loadCredentials("passphrase","wallet/path");
        return credentials;
    }

    private Credentials getCredentialsFromPrivateKey(String PRIVATE_KEY){
        return Credentials.create(PRIVATE_KEY);
    }
}
