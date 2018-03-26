package com.apackage.yvyor.androidfinal;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {


    Button logIn;
    EditText username;
    WebView scoreView;
    String url, host;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initializeWeb();
        clickListener();
    }

    protected void initializeWeb()
    {
        scoreView=findViewById(R.id.webView);
        scoreView.getSettings().setLoadsImagesAutomatically(true);
        scoreView.getSettings().setJavaScriptEnabled(true);
        scoreView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //localhost <- ip fuckery //192.168.0.104 <-- local network PC IP
        //10.0.47.26
        //10.1.5.71
        //255.255.255.252
        host = "10.1.5.71";
        url = "http://"+host+"/MyWebApi/api/db/retrieveData/";
        scoreView.loadUrl(url);
    }
    protected void clickListener() {
        username = findViewById(R.id.userNameInput_PText);
        logIn = findViewById(R.id.login_Button);

        logIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (username.length() < 1) {
                    Toast.makeText(LogIn.this, "Enter Name", Toast.LENGTH_LONG).show();
                } else {
                    Intent nInt = new Intent(getApplicationContext(), PlayActivity.class);
                    nInt.putExtra("username", username.getText().toString());
                    startActivity(nInt);
                }
            }
        });
    }
}
