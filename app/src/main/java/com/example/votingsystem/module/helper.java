package com.example.votingsystem.module;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.infura.InfuraHttpService;

import java.math.BigInteger;

public class helper {

    // contract address
    public static  String contractAddress = "0x46f5D48DAFba3CEB2f281393A520699DE6A3fC7F";

    // endpoint url provided by infura
    public static  String url = "https://ropsten.infura.io/v3/f5080496d68d47b185d77c9b5d2bf8f7";
    // web3j infura instance
  public static   Web3j web3j = Web3j.build(new InfuraHttpService(url));
    // gas limit
    public static  BigInteger gasLimit = BigInteger.valueOf(20_000_000_000L);
    // gas price
    public static BigInteger gasPrice = BigInteger.valueOf(4300000);
    // create credentials w/ your private key
    public static Credentials credentials = Credentials.create("95598565915074818947099222236926775953639481586177383634210199509079411565752");


}
