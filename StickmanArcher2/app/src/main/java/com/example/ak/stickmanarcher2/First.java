package com.example.ak.stickmanarcher2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class First extends AppCompatActivity {
     AdView ad1,ad2,ad3;
    private InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        MobileAds.initialize(this, "ca-app-pub-5155144708129978~2776529081");

        ad1=(AdView)findViewById(R.id.adView1);
        AdRequest adRequest =new AdRequest.Builder().build();
        ad1.loadAd(adRequest);

        ad2=(AdView)findViewById(R.id.adView2);
        AdRequest adRequest2=new AdRequest.Builder().build();
        ad2.loadAd(adRequest2);


        ad3=(AdView)findViewById(R.id.adView3);
        AdRequest adRequest3 =new AdRequest.Builder().build();
        ad1.loadAd(adRequest3);

        interstitialAd =new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-5155144708129978/3194782744");
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
}
