package com.example.ak.stickmanarcher2;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Timer;
import java.util.TimerTask;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    private boolean exit=false;
    TextView text;
    AdView adView;
    InterstitialAd interstitialAd2;
    ProgressBar pg;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.web);
        pg=(ProgressBar)findViewById(R.id.progressBar);

        text=(TextView)findViewById(R.id.text1);
        checkConnection();

        //hide status bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//       // getSupportActionBar().hide();
//        // hide action bar
//        View decorView= getWindow().getDecorView();
//        int uiOption = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOption);
//        // hide status bar

    }

 protected boolean isOnline()
{
    ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo=cm.getActiveNetworkInfo();

    if (networkInfo!=null && networkInfo.isConnectedOrConnecting())
    {
        return  true;
    }
    else{
        return false;

    }
}


    private void checkConnection() {
        if(isOnline()){



            //Toast.makeText(this, "your now connect ", Toast.LENGTH_SHORT).show();
            webView.loadUrl("http://www.twimads.com/local/files/games/stickman-archer-2/start.html");
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
           // webView.setWebChromeClient(new WebChromeClient());
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    Toast.makeText(MainActivity.this, "Game is Loading....", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    pg.setVisibility(view.GONE);
                    webView.setVisibility(view.VISIBLE);

                }
            });

            //improve webview performance

           // webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            //webView.getSettings().setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);
            //webView.getSettings().setAppCacheEnabled(true);

           // webSettings.setDomStorageEnabled(true);
           // webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
           // webSettings.setUseWideViewPort(true);
           // webSettings.setSavePassword(true);
            //webSettings.setSaveFormData(true);
           // webSettings.setEnableSmoothTransition(true);
            
            adView= (AdView)findViewById(R.id.adView2);

            AdRequest adRequest=new AdRequest.Builder().build();
            adView.loadAd(adRequest);


            interstitialAd2 =new InterstitialAd(this);
            interstitialAd2.setAdUnitId("ca-app-pub-5155144708129978/9532403844" /*"ca-app-pub-3940256099942544/1033173712"*/);
            interstitialAd2.loadAd(new AdRequest.Builder().build());
            interstitialAd2.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    //super.onAdClosed();
                    finish();
                    interstitialAd2.loadAd(new AdRequest.Builder().build());
                }
            });
        }

        else {
          //  Toast.makeText(this, "your connected", Toast.LENGTH_SHORT).show();
          text.setText("      Your Internet Connection is diabled. \n \n Please Connect to the Internet and Restart The Application ");
        }

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
           webView.goBack();


        } else {
            // super.onBackPressed();

            if (exit){
                next();
                finish();
            }
            else{
                Toast.makeText(this, "press again to exit", Toast.LENGTH_SHORT).show();
            }

            Timer timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    exit=false;
                }
            },2000);
            exit=true;
        }
    }

    public void next() {
    if(interstitialAd2.isLoaded())
    {
    interstitialAd2.show();
    }
    else
    {
        finish();
    }
    }

}
