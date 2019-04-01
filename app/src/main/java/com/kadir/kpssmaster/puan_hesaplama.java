package com.kadir.kpssmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class puan_hesaplama extends AppCompatActivity {

    private AdView mAdView;

    SharedPreferences preferences;
    Boolean reklam_goster = true;

    EditText genel_yetenek_dogru;
    EditText genel_yetenek_yanlis;
    EditText genel_yetenek_net;

    EditText genel_kultur_dogru;
    EditText genel_kultur_yanlis;
    EditText genel_kultur_net;

    EditText egitim_bilimleri_dogru;
    EditText egitim_bilimleri_yanlis;
    EditText egitim_bilimleri_net;


    double genel_yetenek_dogru_sayisi;
    double genel_yetenek_yanlis_sayisi;
    double genel_yetenek_net_sayisi;

    double genel_kultur_dogru_sayisi;
    double genel_kultur_yanlis_sayisi;
    double genel_kultur_net_sayisi;

    double egitim_bilimleri_dogru_sayisi;
    double egitim_bilimleri_yanlis_sayisi;
    double egitim_bilimleri_net_sayisi;

    double oabt_dogru_sayisi;
    double oabt_yanlis_sayisi;
    double oabt_net_sayisi;



    Button hesapla;
    Button temizle;

    double kpssp3_puani;
    double kpssp10_puani;


    TextView kpssp3;
    TextView kpssp10;

    String isim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puan_hesaplama);

        genel_yetenek_dogru = (EditText)findViewById(R.id.gyd);
        genel_yetenek_yanlis = (EditText)findViewById(R.id.gyy);
        genel_yetenek_net = (EditText) findViewById(R.id.gyn);
        genel_yetenek_net.setEnabled(false);

        genel_kultur_dogru = (EditText) findViewById(R.id.gkd);
        genel_kultur_yanlis = (EditText) findViewById(R.id.gky);
        genel_kultur_net = (EditText) findViewById(R.id.gkn);
        genel_kultur_net.setEnabled(false);

        egitim_bilimleri_dogru = (EditText) findViewById(R.id.ebd);
        egitim_bilimleri_yanlis = (EditText) findViewById(R.id.eby);
        egitim_bilimleri_net = (EditText)findViewById(R.id.ebn);
        egitim_bilimleri_net.setEnabled(false);


        hesapla = (Button)findViewById(R.id.hesapla);
        temizle = (Button) findViewById(R.id.temizle);

        kpssp3 = (TextView)findViewById(R.id.kpssp3);
        kpssp10 = (TextView)findViewById(R.id.kpssp10);


       /* preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        reklam_goster = preferences.getBoolean("goster", true);

        MobileAds.initialize(this, "ca-app-pub-3206398076180977/9199833568");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3206398076180977/9199833568");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("ca-app-pub-3206398076180977/9199833568").build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                if(reklam_goster == true)
                {
                    mAdView.setVisibility(View.VISIBLE);
                }
                else
                {
                    mAdView.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                if(reklam_goster == true)
                {
                    mAdView.setVisibility(View.VISIBLE);
                }
                else
                {
                    mAdView.setVisibility(View.INVISIBLE);
                }
            }
        });*/

        hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    genel_yetenek_dogru_sayisi = Double.parseDouble(genel_yetenek_dogru.getText().toString());

                    String a = genel_yetenek_yanlis.getText().toString();
                    if(a.isEmpty())
                    {
                        genel_yetenek_yanlis_sayisi = 0;
                        genel_yetenek_yanlis.setText("" + 0);
                    }
                    else
                        {
                            genel_yetenek_yanlis_sayisi = Double.parseDouble(a);
                        }

                    genel_kultur_dogru_sayisi = Double.parseDouble(genel_kultur_dogru.getText().toString());

                    String b = genel_kultur_yanlis.getText().toString();
                    if(b.isEmpty())
                    {
                        genel_kultur_yanlis_sayisi = 0;
                        genel_kultur_yanlis.setText("" + 0);
                    }
                    else
                        {
                        genel_kultur_yanlis_sayisi = Double.parseDouble(b);
                    }

                    String e = egitim_bilimleri_dogru.getText().toString();
                    if(!e.isEmpty())
                        egitim_bilimleri_dogru_sayisi = Double.parseDouble(e);

                    String c = egitim_bilimleri_yanlis.getText().toString();
                    if(c.isEmpty())
                    {
                        egitim_bilimleri_yanlis_sayisi = 0;
                        egitim_bilimleri_yanlis.setText("" + 0);
                    }
                    else
                    {
                        egitim_bilimleri_yanlis_sayisi = Double.parseDouble(c);
                    }



                genel_yetenek_net_sayisi = genel_yetenek_dogru_sayisi - genel_yetenek_yanlis_sayisi / 4;
                genel_kultur_net_sayisi = genel_kultur_dogru_sayisi - genel_kultur_yanlis_sayisi / 4;
                egitim_bilimleri_net_sayisi = egitim_bilimleri_dogru_sayisi - egitim_bilimleri_yanlis_sayisi / 4;
                oabt_net_sayisi = oabt_dogru_sayisi - oabt_yanlis_sayisi / 4;

                genel_yetenek_net.setText("" + genel_yetenek_net_sayisi);
                genel_kultur_net.setText("" + genel_kultur_net_sayisi);
                egitim_bilimleri_net.setText("" + egitim_bilimleri_net_sayisi);


                kpssp3_puani = 0.5*(genel_kultur_net_sayisi + genel_yetenek_net_sayisi) + 40;
                kpssp3.setText("" + kpssp3_puani);


                if(!e.isEmpty())
                {
                    kpssp10_puani = 0.30 * (genel_yetenek_net_sayisi + genel_kultur_net_sayisi) + egitim_bilimleri_net_sayisi * 0.40 + 40;
                    kpssp10.setText("" + kpssp10_puani);
                }


                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(puan_hesaplama.this, "Lütfen Boşlukları Doldurun!",
                            Toast.LENGTH_LONG).show();
                }



            }
        });

        temizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genel_yetenek_dogru.setText("");
                genel_yetenek_yanlis.setText("");
                genel_yetenek_net.setText("");

                genel_kultur_dogru.setText("");
                genel_kultur_yanlis.setText("");
                genel_kultur_net.setText("");

                egitim_bilimleri_dogru.setText("");
                egitim_bilimleri_yanlis.setText("");
                egitim_bilimleri_net.setText("");

                kpssp3.setText("");
                kpssp10.setText("");
                genel_yetenek_dogru.requestFocus();

            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(puan_hesaplama.this, anasayfa.class);
        finish();
        startActivity(in);
    }

}
