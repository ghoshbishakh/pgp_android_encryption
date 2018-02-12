package com.example.bishakh.encryptiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.security.Provider;
import java.security.Security;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RSAKeyPairGenerator rsaKeyPairGenerator = new RSAKeyPairGenerator();

        File secret = new File("/sdcard/secret.bpg");
        File public_ = new File("/sdcard/pub.bpg");
        if(!(secret.exists() && public_.exists())){
            Log.v("EncryptionTest", "Generating Keys");
            try {
                rsaKeyPairGenerator.generate("myid", "123", "/sdcard/secret.bpg", "/sdcard/pub.bpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            Log.v("EncryptionTest", "Keys Already Exist");
        }


        KeyBasedFileProcessor keyBasedFileProcessor = new KeyBasedFileProcessor();

        // Encryption Test
        Log.v("EncryptionTest", "Encrypting Files..");
        try {
            keyBasedFileProcessor.encrypt("/sdcard/video.mp4", "/sdcard/pub.bpg");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Decryption Test
        Log.v("EncryptionTest", "Decrypting Files..");
        try {
            keyBasedFileProcessor.decrypt("/sdcard/video.mp4.bpg", "/sdcard/secret.bpg", "123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
