package com.kadir.kpssmaster;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class konu_anlatim_video_secim extends AppCompatActivity {

    Button turkce;
    Button matematik;
    Button geometri;
    Button tarih;
    Button cografya;
    Button vatandaslik;
    Button egitim_bilimleri;

    public InterstitialAd mInterstitialAd;  //Tam sayfa reklam göstermek için.
    SharedPreferences preferences;
    Boolean reklam_goster = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konu_anlatim_video_secim);

        //*************************ücret ödenirse reklamı kaldırmak için*********************
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        reklam_goster = preferences.getBoolean("goster", true);
        //***********************************************************************************

        MobileAds.initialize(this, "ca-app-pub-3206398076180977~6110635794");
        //**************inersteller ad***********************
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3206398076180977/4255379285");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        //******************************************************

        turkce = (Button)findViewById(R.id.turkce);
        matematik = (Button)findViewById(R.id.matematik);
        geometri = (Button)findViewById(R.id.geometri);
        tarih = (Button)findViewById(R.id.tarih);
        cografya = (Button)findViewById(R.id.cografya);
        vatandaslik = (Button)findViewById(R.id.vatandaslik);
        egitim_bilimleri = (Button)findViewById(R.id.egitim_bilimleri);

        matematik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*******intersteller ad başlar***********
                if (mInterstitialAd.isLoaded() && reklam_goster) {
                    mInterstitialAd.show();
                }

                else {
                    String VIDEO_ID = "eNUUlbWBVvQ";
                    Intent i = new Intent(konu_anlatim_video_secim.this, konu_anlatim_videolari_matematik.class);
                    i.putExtra("video_ismi", VIDEO_ID);
                    startActivity(i);
                    finish();
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        String VIDEO_ID = "eNUUlbWBVvQ";
                        Intent i = new Intent(konu_anlatim_video_secim.this, konu_anlatim_videolari_matematik.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }
                });
                //***************************

            }
        });

       cografya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*******intersteller ad başlar***********
                if (mInterstitialAd.isLoaded() && reklam_goster) {
                    mInterstitialAd.show();
                }

                else {
                    String VIDEO_ID = "O4Csolpkly8";
                    Intent i = new Intent(konu_anlatim_video_secim.this, konu_anlatim_videolari_cografya.class);
                    i.putExtra("video_ismi", VIDEO_ID);
                    startActivity(i);
                    finish();
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        String VIDEO_ID = "O4Csolpkly8";
                        Intent i = new Intent(konu_anlatim_video_secim.this, konu_anlatim_videolari_cografya.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }
                });
                //***************************

            }
        });

        tarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*******intersteller ad başlar***********
                if (mInterstitialAd.isLoaded() && reklam_goster) {
                    mInterstitialAd.show();
                }

                else {
                    String VIDEO_ID = "pycVrv3Ivt4";
                    Intent i = new Intent(konu_anlatim_video_secim.this, konu_anlatim_videolari_tarih.class);
                    i.putExtra("video_ismi", VIDEO_ID);
                    startActivity(i);
                    finish();
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        String VIDEO_ID = "pycVrv3Ivt4";
                        Intent i = new Intent(konu_anlatim_video_secim.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }
                });
                //***************************

            }
        });

        turkce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*******intersteller ad başlar***********
                if (mInterstitialAd.isLoaded() && reklam_goster) {
                    mInterstitialAd.show();
                }

                else {
                    String VIDEO_ID = "YsM2HStZjSc";
                    Intent i = new Intent(konu_anlatim_video_secim.this, konu_anlatim_videolari_turkce.class);
                    i.putExtra("video_ismi", VIDEO_ID);
                    startActivity(i);
                    finish();
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        String VIDEO_ID = "YsM2HStZjSc";
                        Intent i = new Intent(konu_anlatim_video_secim.this, konu_anlatim_videolari_turkce.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }
                });
                //***************************

            }
        });
        vatandaslik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*******intersteller ad başlar***********
                if (mInterstitialAd.isLoaded() && reklam_goster) {
                    mInterstitialAd.show();
                }

                else {
                    String VIDEO_ID = "pKNrr1P43-o";
                    Intent i = new Intent(konu_anlatim_video_secim.this, konu_anlatim_videolari_vatandaslik.class);
                    i.putExtra("video_ismi", VIDEO_ID);
                    startActivity(i);
                    finish();
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        String VIDEO_ID = "pKNrr1P43-o";
                        Intent i = new Intent(konu_anlatim_video_secim.this, konu_anlatim_videolari_vatandaslik.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }
                });
                //***************************

            }
        });

        egitim_bilimleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*******intersteller ad başlar***********
                if (mInterstitialAd.isLoaded() && reklam_goster) {
                    mInterstitialAd.show();
                }

                else {
                    Intent i = new Intent(konu_anlatim_video_secim.this, egitim_bilimleri_video_secim.class);
                    startActivity(i);
                    finish();
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        Intent i = new Intent(konu_anlatim_video_secim.this, egitim_bilimleri_video_secim.class);
                        startActivity(i);
                        finish();
                    }
                });
                //***************************

            }
        });

        geometri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*******intersteller ad başlar***********
                if (mInterstitialAd.isLoaded() && reklam_goster) {
                    mInterstitialAd.show();
                }

                else {
                    String VIDEO_ID = "Uzh2xJHrTQQ";
                    Intent i = new Intent(konu_anlatim_video_secim.this, konu_anlatim_videolari_geometri.class);
                    i.putExtra("video_ismi", VIDEO_ID);
                    startActivity(i);
                    finish();
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        String VIDEO_ID = "Uzh2xJHrTQQ";
                        Intent i = new Intent(konu_anlatim_video_secim.this, konu_anlatim_videolari_geometri.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }
                });
                //***************************

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(konu_anlatim_video_secim.this, anasayfa.class);
        startActivity(i);
        finish();
    }


}
