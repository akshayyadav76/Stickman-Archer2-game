package com.example.ak.stickmanarcher2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private boolean exit=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        // hide action bar
        View decorView= getWindow().getDecorView();
        int uiOption = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOption);
        // hide status bar

        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.web);
        webView.loadUrl("http://www.twimads.com/local/files/games/stickman-archer-2/start.html");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            // super.onBackPressed();
            if (exit){
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
}
