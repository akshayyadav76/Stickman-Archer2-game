package com.example.ak.stickmanarcher2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class First extends AppCompatActivity {
     AdView ad1;
    Button Exit1,Button22;


    private InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        MobileAds.initialize(this,"ca-app-pub-5155144708129978/6581695114" /*"ca-app-pub-3940256099942544/6300978111"*/);
        onbuttonclicklistener();
        //onclickbutton22();
        Button22 = (Button) findViewById(R.id.Button2);
        Button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });



        ad1=(AdView)findViewById(R.id.adView1);
        AdRequest adRequest =new AdRequest.Builder().build();
        ad1.loadAd(adRequest);


        interstitialAd =new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-5155144708129978/3194782744" /* "ca-app-pub-3940256099942544/1033173712"*/);
        interstitialAd.loadAd(new AdRequest.Builder().build());


        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startActivity(new Intent(First.this,MainActivity.class));
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }

    public void next(View v){
   if (interstitialAd.isLoaded())
   {
       interstitialAd.show();
   }
        else{
        startActivity(new Intent(First.this,MainActivity.class));
    }
    }


    public void onbuttonclicklistener(){

        Exit1=(Button)findViewById(R.id.Exitt);
        Exit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder a_builder=new AlertDialog.Builder(First.this);
                a_builder.setMessage("Do You Want to Exit").setCancelable(true)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                            }
                        });
                        AlertDialog alert=a_builder.create();
                alert.setTitle("Exit");
                alert.show();
            }
        });
    }

    public void openDialog(){

        Dialog Diloag =new Dialog();
        Diloag.show(getSupportFragmentManager(),"Diloag");
    }

}


