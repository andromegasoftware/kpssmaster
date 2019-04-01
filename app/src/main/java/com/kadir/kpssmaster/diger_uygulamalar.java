package com.kadir.kpssmaster;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class diger_uygulamalar extends AppCompatActivity {

    Button ingilizce;
    Button matematik;
    Button kpss;
    Button kamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diger_uygulamalar);

        ingilizce = (Button)findViewById(R.id.ingilizce);
        matematik = (Button) findViewById(R.id.matematik);
        kpss = (Button)findViewById(R.id.kpss);
        kamera = (Button)findViewById(R.id.kamera);

        ingilizce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.kadir.learnenglishforkids"));
                startActivity(intent);
            }
        });

        matematik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.kadir.matematikoyunu"));
                startActivity(intent);
            }
        });
        kpss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.company.kadir.zikirmatic"));
                startActivity(intent);
            }
        });
        kamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.miranbey.u3df"));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(diger_uygulamalar.this, anasayfa.class);
        startActivity(i);
    }
}
