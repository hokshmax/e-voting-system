package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.votingsystem.module.helper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;

import java.io.File;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.CheckReturnValue;

public class signUP extends AppCompatActivity {


    EditText email, password, name,id,phone;

    CheckBox age,notpolive;
    EditText fullAdress,Job;


    Spinner city;

    Button createAccount;


 // array string of cities

    String [] cites ={"الرباط المحيط","الرباط شالة","سلا المدينة","سلا جديدة","الصخيرات تمارة","الخمسيات اولماس","الخمسيات الرمماني",
            "القنيطرة","الغرب","العيون","سيدي سليمان\t","سيدي قاسم\t","بوجدور","طرفاية","السمارة","وادي الذهب","اوسرد",
            "الرشيدية","تنغير","زاكورة","ورزازات","ميدلت","الدار البيضاء أنفا\t","ابن مسيك\t","سيدي البرنوصي\t","الفداء-مرس السلطان\t","عين السبع-الحي المحمدي\t"
            ,"مولاي رشيد\t","الحي الحسني\t","عين الشق\t","المحمدية\t","النواصر","مديونة","بنسليمان",
            "الجديدة","برشيد","سطات","سيدي بنور\t","أكادير إدا وتنان\t","إنزكان آيت ملول\t","شتوكة آيت باها\t","تارودانت الجنوبية\t","تارودانت الشمالية\t",
            "تيزنيت","طاطا","كلميم","طانطان","سيدي إفني\t","سا الزاك\t","المدينة سيدي يوسف بن علي\t","جليز-النخيل\t",
            "المنارة","الحوز","قلعة السراغنة\t","الصويرة","الرحامنة","أسفي","اليوسفية","طنجة أصيلة\t","المضيق الفنيدق\t","الفحص أنجرة\t",
            "تطوان","وزان","العرائش","شفشاون","الحسيمة","وجدة","الناضور","الدريوش","جرادة","بركان","تاوريرت","جرسيف",
            "فجيج","فاس الجنوبية\t","فاس الشمالية\t","إفران","مولاي يعقوب\t","صفرو","بولمان","الحاجب","تازة","تاونات-تيسة\t","القرية غفساي\t",
            "مكناس","بني ملال\t","بزو-واويزغت\t","ازيلال دمنات\t","الفقيه بن صالح\t"
            ,"خريبكة\t"
            ,"خنيفرة"};


    // method to check read data devices storage acces avilable

    public boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }
    // method to check write data devices storage acces avilable

    public boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set view of signup page
        setContentView(R.layout.activity_sign_u_p);

        //email field
        email = findViewById(R.id.email);
        //password field
        password = findViewById(R.id.password);
        //name field
        name = findViewById(R.id.name);
        //city field spppiner
        city = findViewById(R.id.city);
        //cin field
        id = findViewById(R.id.id);
        //phone field
        phone = findViewById(R.id.phone);
        //age field
        age=findViewById(R.id.age);
        //comfirm not in police field

        notpolive=findViewById(R.id.notpolive);
        //full address field

        fullAdress=findViewById(R.id.fulladdress);
        //job field

        Job=findViewById(R.id.Job);

        city.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,cites));

        isReadStoragePermissionGranted();
        isWriteStoragePermissionGranted();
        //buttton create account field

        createAccount = findViewById(R.id.createaccount);

        createAccount.setOnClickListener(v -> {

            //check age and not in police or milatry
            if(age.isChecked()&&notpolive.isChecked())
            {
                // method create account
                createnewAccount();

            }
            else {
                // retrun message to check age or job
                Toast.makeText(this,"check your age or job ",Toast.LENGTH_LONG).show();
            }
        });

        // method to load read encoded data
        setupBouncyCastle();

    }

    // method to check Permissions of device if granted ot not
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //resume tasks needing this permission
                } else {
                }
                break;

            case 3:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //resume tasks needing this permission
                } else {
                }
                break;
        }
    }

    private void setupBouncyCastle() {


        Handler handler = new Handler(Looper.getMainLooper());


        handler.post(() -> {
            final Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
            if (provider == null) {
                // Web3j will set up the provider lazily when it's first used.
                return;
            }
            if (provider.getClass().equals(BouncyCastleProvider.class)) {
                // BC with same package name, shouldn't happen in real life.
                return;
            }
            // Android registers its own BC provider. As it might be outdated and might not include
            // all needed ciphers, we substitute it with a known BC bundled in the app.
            // Android's BC has its package rewritten to "com.android.org.bouncycastle" and because
            // of that it's possible to have another BC implementation loaded in VM.
            Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
            Security.insertProviderAt(new BouncyCastleProvider(), 1);

        });
    }




    void createwallet(String auth )
    {

        Handler handler=new Handler();






        handler.post(() -> {
            final String password =this.password.getText().toString() ;
            String walletPath = getFilesDir().getAbsolutePath();
            File walletDir  = new File(walletPath);

            try{
                KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECIES");

                Security.addProvider(new BouncyCastleProvider());


                String fileName = WalletUtils.generateLightNewWalletFile(password, walletDir);


                walletDir = new File(walletPath + "/" + fileName);

                FirebaseStorage.getInstance().getReference("wallets").child(auth).putFile(Uri.fromFile(walletDir)).addOnSuccessListener(taskSnapshot -> {

                    Map<String,Object> map=new HashMap<>();

                    map.put("wallet",taskSnapshot.getUploadSessionUri());
                    map.put("password",password);
                  //  FirebaseFirestore.getInstance().collection("wallet").document(auth).set(map);
                });


            }
            catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                //Display an Error
            }

            try {
                Credentials credentials = WalletUtils.loadCredentials(password, walletDir);



                Toast.makeText(this, "Your address is " + credentials.getAddress(), Toast.LENGTH_LONG).show();

            }
            catch (Exception e){
                //Show Error
            }

        });

    }

    // create new account method
    private void createnewAccount() {

        // check all fileds if not empty
        if(email.getText().toString().isEmpty())
        {
            email.setError("email is empty");
            return ;
        }

        if(password.getText().toString().isEmpty())
        {
            password.setError("email is empty");

            return;
        }
        if(name.getText().toString().isEmpty())
        {
            name.setError("name is empty");

            return;
        }

        if(fullAdress.getText().toString().isEmpty())
        {
            fullAdress.setError("full is empty");

            return;
        }

        if(Job.getText().toString().isEmpty())
        {
            Job.setError("job is empty");

            return;
        }






        // create new account in firebase

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                // save data to firebase
                Map<String,Object>map=new HashMap<>();

                map.put("name",name.getText().toString());
                map.put("email",email.getText().toString());
                map.put("password",password.getText().toString());
                map.put("id",id.getText().toString());
                map.put("phone",phone.getText().toString());

                map.put("city",city.getSelectedItem().toString());

                map.put("job",Job.getText().toString());
                map.put("fulladress",fullAdress.getText().toString());

                map.put("admin",false);

                // check if data added
                FirebaseFirestore.getInstance().collection("users").document(authResult.getUser().getUid()).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //message
                        Toast.makeText(getApplicationContext(),"email Created",Toast.LENGTH_LONG).show();
                   //    createwallet(authResult.getUser().getUid());

                        //go to second page completesetup
                        startActivity(new Intent(signUP.this,completesetup.class));
                          finish();
                    }
                });
            }
        }).addOnFailureListener(e -> {
              Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        });
    }
}
