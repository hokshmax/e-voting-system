package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.votingsystem.adapters.spinneradapter;
import com.example.votingsystem.adapters.voterRecAdapter;
import com.example.votingsystem.module.helper;
import com.example.votingsystem.module.voteModule;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;

import java.io.File;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


import jnr.ffi.annotations.In;

import static com.example.votingsystem.module.helper.contractAddress;
import static com.example.votingsystem.module.helper.credentials;

public class MainActivity extends AppCompatActivity {

    Button button,addCandi,proccesVoting;


            Loadingfragment loadingfragment;


            long count=1;


// boolean to check email is admin or user
boolean isadmin;
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
    List<voteModule>data;
        Spinner voteacc;
    int CandicatorCount=0;



    String city;
    TextView walletAdress,txtname,txtcity,txtId;
    com.example.votingsystem.contract.Contracts_Election_sol_Election greeter;
    public  boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }
  RecyclerView voterRec;

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
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBouncyCastle();
        isReadStoragePermissionGranted();
        isWriteStoragePermissionGranted();
       voteacc=findViewById(R.id.candivote);
       addCandi=findViewById(R.id.addCandi);
        button = findViewById(R.id.button);
        voterRec=findViewById(R.id.voterRec);
        walletAdress=findViewById(R.id.walletadress);
        txtname=findViewById(R.id.txtname);
        txtcity=findViewById(R.id.txtCity);
        txtId=findViewById(R.id.txtId);
        proccesVoting=findViewById(R.id.proccesVoting);
        getProcessVoting();

        loadingfragment=new Loadingfragment();

        loadingfragment.setCancelable(false);



        proccesVoting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(proccesVoting.getText().toString().equals("startVoting"))
                {
                    Map<String,Boolean>map=new HashMap<>();
                    map.put("voting",true);
                    FirebaseFirestore.getInstance().collection("votingProcess").document("votingProcess").set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            proccesVoting.setText("stopVoting");
                        }
                    });
                }else {
                    Map<String,Boolean>map=new HashMap<>();
                    map.put("voting",false);
                    FirebaseFirestore.getInstance().collection("votingProcess").document("votingProcess").set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            proccesVoting.setText("startVoting");
                        }
                    });
                }
            }
        });
        getuserInfo()
;


        if(getIntent()!=null)
        {

            city=getIntent().getStringExtra("city");
            if(!getIntent().getBooleanExtra("admin",false))
            {
                voteacc.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                isadmin=false;

                addCandi.setVisibility(View.INVISIBLE);
                proccesVoting.setVisibility(View.INVISIBLE);

            }
            else {
                addCandi.setVisibility(View.VISIBLE);
                proccesVoting.setVisibility(View.VISIBLE);
isadmin=true;
                voteacc.setVisibility(View.INVISIBLE);
                button.setVisibility(View.INVISIBLE
                );
            }

        }

        addCandi.setOnClickListener(v->{
            addnewCandicate();
        });
          data=new ArrayList<>();
            button.setOnClickListener(v -> {
                        new vode().execute();


            }

            );



        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        voterRec.setLayoutManager(linearLayoutManager);

        voterRec.setAdapter(new voterRecAdapter(data,getSupportFragmentManager(),isadmin));



        //  button.setOnClickListener(v -> createwallet());

//        Web3j web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/f5080496d68d47b185d77c9b5d2bf8f7")); // defaults to http://localhost:8545/
//        Web3ClientVersion web3ClientVersion = null;
//
//
//        try {
//            web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
//            if(!web3ClientVersion.hasError())
//            {
//                Toast.makeText(this,"good",Toast.LENGTH_LONG).show();
//
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
//
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//
//            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
//        }
//        assert web3ClientVersion != null;
        // String clientVersion = web3ClientVersion.getWeb3ClientVersion();

        // Toast.makeText(this, "Your address is " + web3ClientVersion, Toast.LENGTH_LONG).show();

        walletAdress.setText( "ID:"+credentials.getAddress());

                greeter =    com.example.votingsystem.contract.Contracts_Election_sol_Election
                        .load(contractAddress, helper.web3j, credentials, helper.gasLimit, helper.gasPrice);




        Handler handler=new Handler(Looper.getMainLooper() );

        handler.post(() -> {
            try {
                retruncandies(2);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Toast.makeText(this, "Your address is " +greeter.getContractAddress(), Toast.LENGTH_LONG).show();










//        try {
//            //Toast.makeText(this, " hoksh" +greating.get().toString()+greating.isDone(), Toast.LENGTH_LONG).show();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }

    private void getProcessVoting() {

        FirebaseFirestore.getInstance().collection("votingProcess").document("votingProcess").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(e!=null)
                {
                    return;
                }
                boolean x= (boolean) documentSnapshot.get("voting");
                if(x==true)
                {
                    proccesVoting.setText("stopVoting");
                    button.setEnabled(true);

                }
                else {
                    proccesVoting.setText("startVoting");
                    button.setEnabled(false);
                }


            }
        });
    }

    private void getuserInfo() {
        FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                txtname.setText(documentSnapshot.get("name").toString());
                txtcity.setText(documentSnapshot.get("city").toString());
                txtId.setText(documentSnapshot.get("id").toString());

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addnewCandicate() {

        View view= LayoutInflater.from(this).inflate(R.layout.addnewcandi,null,false);
        EditText newCandi=view.findViewById(R.id.edtcandies);
        EditText job=view.findViewById(R.id.edtcandiesJob);
        EditText party=view.findViewById(R.id.edtcandiesParty);
        EditText plan=view.findViewById(R.id.edtcandiesPlan);
        EditText degree=view.findViewById(R.id.edtcandiesDegree);

        Spinner spinner=view.findViewById(R.id.addnewCandiSpiner);



        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,cites));




        AlertDialog.Builder newCandiAlert=new AlertDialog.Builder(this);

        newCandiAlert.setView(view);

        newCandiAlert.setNeutralButton("submit", (dialog, which) -> {
            if(newCandi.getText().toString().isEmpty()||job.getText().toString().isEmpty()||party.getText().toString().isEmpty()||
            plan.getText().toString().isEmpty()||degree.getText().toString().isEmpty())
            {
                Toast.makeText(this,"some filed is empty",Toast.LENGTH_LONG).show();

                return;
            }

         CompletableFuture add=greeter.addCandidate(newCandi.getText().toString(),spinner.getSelectedItem().toString()).sendAsync();

             Toast.makeText(this,"new Candicate Added",Toast.LENGTH_LONG).show();

             Map<String,Object>map=new HashMap<>();
             map.put("name",newCandi.getText().toString());
             map.put("job",job.getText().toString());
             map.put("party",party.getText().toString());
             map.put("degree",newCandi.getText().toString());
             map.put("plan",newCandi.getText().toString());
             map.put("city",spinner.getSelectedItem().toString());



             FirebaseFirestore.getInstance().collection("candicates").document().set(map).addOnSuccessListener(aVoid -> {

                 finish();
                 overridePendingTransition(0, 0);
                 startActivity(getIntent());
                 overridePendingTransition(0, 0);

             });






        });

        newCandiAlert.setNegativeButton("Cancel", (dialog, which) -> {

            dialog.dismiss();

        });

        newCandiAlert.show();


    }

    @Override
    public void recreate() {
        super.recreate();
    }

    private void setupBouncyCastle() {
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
    }


class vode extends AsyncTask<Void,Integer,Boolean>{


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

    }


    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
loadingfragment.show(getSupportFragmentManager(),"dd");
            vote();
        } catch (ExecutionException e) {

            new Handler(getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage().contains("you have already voted before")?"you have already voted before":"error try agian",Toast.LENGTH_LONG).show();

                }
            });
            e.printStackTrace();
        } catch (InterruptedException e) {
            new Handler(getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage().contains("you have already voted before")?"you have already voted before":"error try agian",Toast.LENGTH_LONG).show();

                }
            });
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        loadingfragment.dismiss();
       // Toast.makeText(MainActivity.this,"Voted ",Toast.LENGTH_LONG).show();

    }


}

    void vote() throws ExecutionException, InterruptedException {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {


            Future<TransactionReceipt> greating = greeter.vote(BigInteger.valueOf(count)).sendAsync();



            Toast.makeText(this,greating.get().isStatusOK()+"",Toast.LENGTH_LONG).show();

        }
    }

    void retruncandies(int x) throws ExecutionException, InterruptedException {


        CompletableFuture<BigInteger> greating1 = greeter.candidatesCount().sendAsync();
        x=Integer.parseInt(greating1.get().toString());
       if(greating1.isDone())
       {
           if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
               for(int i=1;i<=x;i++)
               {
                   CompletableFuture<Tuple4<BigInteger, String, String, BigInteger>> greating = greeter.candidates(BigInteger.valueOf(i)).sendAsync();

                   if(city.equals(greating.get().component3())&& isadmin==false) {


                       Toast.makeText(MainActivity.this,"max"+isadmin,Toast.LENGTH_LONG).show();


                       data.add(new voteModule(Integer.parseInt(String.valueOf(greating.get().component1())), Integer.parseInt(String.valueOf(greating.get().component4())), greating.get().component2(), greating.get().component3()) {


                       });
                   }
                   else if(isadmin) {

                       data.add(new voteModule(Integer.parseInt(String.valueOf(greating.get().component1())), Integer.parseInt(String.valueOf(greating.get().component4())), greating.get().component2(), greating.get().component3()) {


                       });
                   }





               }
               voterRec.getAdapter().notifyDataSetChanged();

               voteacc.setAdapter(new spinneradapter(data));

               voteacc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                       count=id;
                         Toast.makeText(MainActivity.this,"maxx"+count,Toast.LENGTH_LONG).show();

                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> parent) {

                   }
               });



           }

       }
    }
    
    void createwallet()
    {
         final String password = "medium";
         String walletPath = getFilesDir().getAbsolutePath();
         File walletDir  = new File(walletPath);

        try{
            KeyPairGenerator   kpg = KeyPairGenerator.getInstance("ECIES");

            Security.addProvider(new BouncyCastleProvider());


            String fileName = WalletUtils.generateLightNewWalletFile(password, walletDir);
            walletDir = new File(walletPath + "/" + fileName);
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
    }
}

