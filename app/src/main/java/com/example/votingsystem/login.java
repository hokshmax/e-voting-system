package com.example.votingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;

import org.web3j.crypto.Credentials;

import static com.example.votingsystem.module.helper.credentials;

public class login extends AppCompatActivity {


    TextView createaccount;

    Button login;

    EditText email,pass;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
       // text create new account
        createaccount=findViewById(R.id.createaccount);

        //buttons
        login=findViewById(R.id.login);
        email=findViewById(R.id.useremail);
        pass=findViewById(R.id.userpassword);

        //login button on click
login.setOnClickListener(v->{
    // access to firebase and check email is exist and data
    FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
        @Override
        public void onSuccess(AuthResult authResult) {

             FirebaseFirestore.getInstance().collection("users").document(authResult.getUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                 @Override
                 public void onSuccess(DocumentSnapshot documentSnapshot) {


                     // check if user put wallet private key
                     if(documentSnapshot.contains("privatekey"))
                     {
                          //create request by private key to block chain contarct
                         credentials= Credentials.create(documentSnapshot.get("privatekey").toString());
                              // move to main activity page with user data
                         Intent intent=new Intent(login.this,MainActivity.class);
                         intent.putExtra("admin",documentSnapshot.getBoolean("admin"));
                         intent.putExtra("city",documentSnapshot.getString("city"));
                         startActivity(intent);

                         finish();

                     }

                     else {
                         // move to page complete set up to make user write his private key to login and conect with contract
                         Intent intent=new Intent(login.this,completesetup.class);
                         intent.putExtra("admin",documentSnapshot.getBoolean("admin"));
                         intent.putExtra("city",documentSnapshot.getString("city"));
                         startActivity(intent);
                         finish();
                     }





                 }
             });

        }
    }).addOnFailureListener(e -> {

        // message if some thing wrong happend
        Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
    });

    Toast.makeText(this,"Loading..........",Toast.LENGTH_LONG).show();
});
                  // go to create new  account page
        createaccount.setOnClickListener(v->{startActivity(new Intent(this,signUP.class));});
    }
}
