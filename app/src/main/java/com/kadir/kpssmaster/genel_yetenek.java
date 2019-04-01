package com.kadir.kpssmaster;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class genel_yetenek extends AppCompatActivity {

    Button turkce;
    Button matematik;
    Button geometri;
    Button genelyetenekkarisik;

    long turkce_sorusayisi = 380;
    DatabaseReference sayideneme_turkce;

    long matematik_sorusayisi = 456;
    DatabaseReference sayideneme_matematik;

    long geometri_sorusayisi = 136;
    DatabaseReference sayideneme_geometri;

    long turkceb = 380;
    long matematikb = 456;
    long geometrib = 136;

    DatabaseReference sayideneme_genelyetenekkarisik;

    ProgressDialog dialog;

    public InterstitialAd mInterstitialAd;  //Tam sayfa reklam göstermek için.
    SharedPreferences preferences;
    Boolean reklam_goster = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genel_yetenek);

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

        dialog = new ProgressDialog(this);

        sayideneme_genelyetenekkarisik = FirebaseDatabase.getInstance().getReference();
        sayideneme_genelyetenekkarisik.keepSynced(true);
        sayideneme_genelyetenekkarisik.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                turkceb = dataSnapshot.child("turkce").getChildrenCount();
                matematikb = dataSnapshot.child("matematik").getChildrenCount();
                geometrib = dataSnapshot.child("geometri").getChildrenCount();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        sayideneme_turkce = FirebaseDatabase.getInstance().getReference().child("turkce");
        sayideneme_turkce.keepSynced(true);
        sayideneme_turkce.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                turkce_sorusayisi = dataSnapshot.getChildrenCount();

                System.out.println("turkçe soru sayisi: " + turkce_sorusayisi);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        sayideneme_matematik = FirebaseDatabase.getInstance().getReference().child("matematik");
        sayideneme_matematik.keepSynced(true);
        sayideneme_matematik.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                matematik_sorusayisi = dataSnapshot.getChildrenCount();

                System.out.println("matematik soru sayisi: " + matematik_sorusayisi);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        sayideneme_geometri = FirebaseDatabase.getInstance().getReference().child("geometri");
        sayideneme_geometri.keepSynced(true);
        sayideneme_geometri.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    geometri_sorusayisi = dataSnapshot.getChildrenCount();

                    System.out.println("not connected");
                System.out.println("geometri soru sayisi: " + geometri_sorusayisi);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {

                }
                else {
                    Toast.makeText(genel_yetenek.this, "Lütfen İnternet Bağlantınızı Kontrol Ediniz",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        turkce = (Button)findViewById(R.id.turkce);
        turkce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("Sorular Hazırlanıyor, Lütfen Bekleyiniz!!!");
                dialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //*******intersteller ad başlar***********
                        if (mInterstitialAd.isLoaded() && reklam_goster) {
                            mInterstitialAd.show();
                        }

                        else {
                            dialog.dismiss();
                            Intent i = new Intent(genel_yetenek.this, turkce_sorular.class);
                            i.putExtra("dizi", turkce_sorusayisi);
                            startActivity(i);
                            finish();
                        }

                        mInterstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                // Code to be executed when the interstitial ad is closed.
                                dialog.dismiss();
                                Intent i = new Intent(genel_yetenek.this, turkce_sorular.class);
                                i.putExtra("dizi", turkce_sorusayisi);
                                startActivity(i);
                                finish();
                            }
                        });
                        //***************************

                    }
                }, 2000);


            }
        });

        genelyetenekkarisik = (Button)findViewById(R.id.genelyetenekkarisik);
        genelyetenekkarisik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("Sorular Hazırlanıyor, Lütfen Bekleyiniz!!!");
                dialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //*******intersteller ad başlar***********
                        if (mInterstitialAd.isLoaded() && reklam_goster) {
                            mInterstitialAd.show();
                        }

                        else {
                            dialog.dismiss();
                            Intent i = new Intent(genel_yetenek.this, genel_yatanek_karisik.class);
                            i.putExtra("dizi1", turkceb);
                            i.putExtra("dizi2", matematikb);
                            i.putExtra("dizi3", geometrib);
                            startActivity(i);
                            finish();
                        }

                        mInterstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                // Code to be executed when the interstitial ad is closed.
                                dialog.dismiss();
                                Intent i = new Intent(genel_yetenek.this, genel_yatanek_karisik.class);
                                i.putExtra("dizi1", turkceb);
                                i.putExtra("dizi2", matematikb);
                                i.putExtra("dizi3", geometrib);
                                startActivity(i);
                                finish();
                            }
                        });
                        //***************************

                    }
                }, 2000);


            }
        });

        matematik = (Button)findViewById(R.id.matematik);
        matematik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("Sorular Hazırlanıyor, Lütfen Bekleyiniz!!!");
                dialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //*******intersteller ad başlar***********
                        if (mInterstitialAd.isLoaded() && reklam_goster) {
                            mInterstitialAd.show();
                        }

                        else {
                            dialog.dismiss();
                            Intent i = new Intent(genel_yetenek.this, matematik_sorular.class);
                            i.putExtra("dizi", matematik_sorusayisi);
                            startActivity(i);
                            finish();
                        }

                        mInterstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                // Code to be executed when the interstitial ad is closed.
                                dialog.dismiss();
                                Intent i = new Intent(genel_yetenek.this, matematik_sorular.class);
                                i.putExtra("dizi", matematik_sorusayisi);
                                startActivity(i);
                                finish();
                            }
                        });
                        //***************************

                    }
                }, 2000);
            }
        });

        geometri = (Button)findViewById(R.id.geometri);
        geometri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("Sorular Hazırlanıyor, Lütfen Bekleyiniz!!!");
                dialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //*******intersteller ad başlar***********
                        if (mInterstitialAd.isLoaded() && reklam_goster) {
                            mInterstitialAd.show();
                        }

                        else {
                            dialog.dismiss();
                            Intent i = new Intent(genel_yetenek.this, geometri_sorular.class);
                            i.putExtra("dizi", geometri_sorusayisi);
                            startActivity(i);
                            finish();
                        }

                        mInterstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                // Code to be executed when the interstitial ad is closed.
                                dialog.dismiss();
                                Intent i = new Intent(genel_yetenek.this, geometri_sorular.class);
                                i.putExtra("dizi", geometri_sorusayisi);
                                startActivity(i);
                                finish();
                            }
                        });
                        //***************************

                    }
                }, 2000);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(genel_yetenek.this, soru_bankasi_alan_secimi.class);
        finish();
        startActivity(in);
    }
}
