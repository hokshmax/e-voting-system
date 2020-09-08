package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.votingsystem.module.helper;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static com.example.votingsystem.module.helper.credentials;

public class completesetup extends AppCompatActivity {


    Button complete;

    String city;
    boolean admin;

    EditText privateKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completesetup);
        complete=findViewById(R.id.complete);
        privateKey=findViewById(R.id.privateKey);



        complete.setOnClickListener(v->{

            if(privateKey.getText().toString().isEmpty())
            {
                Toast.makeText(this,"wallet adress is empty",Toast.LENGTH_LONG).show();

return;
            }
            if(!privateKey.getText().toString().isEmpty())
            {
                try {
                    credentials=Credentials.create(privateKey.getText().toString());

                }catch (Exception e)
                {

Toast.makeText(this,"this wallet is invalied",Toast.LENGTH_LONG).show();
                    return;

                }
            }



            Map<String,Object> map=new HashMap<>();

            map.put("privatekey",  privateKey.getText().toString());






            FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getUid()).set(map, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(),"email Created",Toast.LENGTH_LONG).show();
//                    credentials=Credentials.create(privateKey.getText().toString());

                    getdata();


                }
            });
        });


    }

    public void getdata()
    {
        FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {


                if(documentSnapshot.contains("privatekey"))
                {


                    credentials= Credentials.create(documentSnapshot.get("privatekey").toString());

                    Intent intent=new Intent(completesetup.this,MainActivity.class);
                    intent.putExtra("admin",documentSnapshot.getBoolean("admin"));
                    intent.putExtra("city",documentSnapshot.getString("city"));
                    startActivity(intent);

                    finish();

                }

                else {

                    Toast.makeText(completesetup.this,"some thing wromg",Toast.LENGTH_LONG).show();
                }





            }
        });

    }
}
