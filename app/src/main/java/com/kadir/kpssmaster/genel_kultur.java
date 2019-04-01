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

public class genel_kultur extends AppCompatActivity {

    Button tarih;
    Button cografya;
    Button vatandaslik;
    Button guncelbilgiler;
    Button genelkulturkarisik;

    long tariha = 520;
    long cografyaa = 318;
    long vatandaslika = 218;
    long guncelbilgilera = 175;

    long tarih_sorusayisi = 520;
    DatabaseReference sayideneme_tarih;

    long cografya_sorusayisi = 318;
    DatabaseReference sayideneme_cografya;

    long vatandaslik_sorusayisi = 218;
    DatabaseReference sayideneme_vatandaslik;

    long guncelbilgiler_sorusayisi = 175;
    DatabaseReference sayideneme_guncelbilgiler;


    DatabaseReference sayideneme_genelkulturkarisik;

    ProgressDialog dialog;

    public InterstitialAd mInterstitialAd;  //Tam sayfa reklam göstermek için.
    SharedPreferences preferences;
    Boolean reklam_goster = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genel_kultur);

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

        sayideneme_genelkulturkarisik = FirebaseDatabase.getInstance().getReference();
        sayideneme_genelkulturkarisik.keepSynced(true);
        sayideneme_genelkulturkarisik.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                tariha = dataSnapshot.child("tarih").getChildrenCount();
                cografyaa = dataSnapshot.child("cografya").getChildrenCount();
                vatandaslika = dataSnapshot.child("vatandaslik").getChildrenCount();
                guncelbilgilera = dataSnapshot.child("guncelbilgiler").getChildrenCount();



            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        sayideneme_tarih = FirebaseDatabase.getInstance().getReference().child("tarih");
        sayideneme_tarih.keepSynced(true);
        sayideneme_tarih.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               tarih_sorusayisi = dataSnapshot.getChildrenCount();

                System.out.println("tarih soru sayisi: " + tarih_sorusayisi);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        sayideneme_cografya = FirebaseDatabase.getInstance().getReference().child("cografya");
        sayideneme_cografya.keepSynced(true);
        sayideneme_cografya.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                cografya_sorusayisi = dataSnapshot.getChildrenCount();

                System.out.println("cografya soru sayisi: " + cografya_sorusayisi);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        sayideneme_vatandaslik = FirebaseDatabase.getInstance().getReference().child("vatandaslik");
        sayideneme_vatandaslik.keepSynced(true);
        sayideneme_vatandaslik.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                vatandaslik_sorusayisi = dataSnapshot.getChildrenCount();

                System.out.println("vatandaslik soru sayisi: " + vatandaslik_sorusayisi);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       sayideneme_guncelbilgiler = FirebaseDatabase.getInstance().getReference().child("guncelbilgiler");
       sayideneme_guncelbilgiler.keepSynced(true);
        sayideneme_guncelbilgiler.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                guncelbilgiler_sorusayisi = dataSnapshot.getChildrenCount();

                System.out.println("güncel bilgiler soru sayisi: " + guncelbilgiler_sorusayisi);

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
                    Toast.makeText(genel_kultur.this, "Lütfen İnternet Bağlantınızı Kontrol Ediniz",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        tarih = (Button)findViewById(R.id.tarih);
        tarih.setOnClickListener(new View.OnClickListener() {
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
                            Intent i = new Intent(genel_kultur.this, tarih_sorular.class);
                            i.putExtra("dizi", tarih_sorusayisi);
                            startActivity(i);
                            finish();
                        }

                        mInterstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                // Code to be executed when the interstitial ad is closed.
                                dialog.dismiss();
                                Intent i = new Intent(genel_kultur.this, tarih_sorular.class);
                                i.putExtra("dizi", tarih_sorusayisi);
                                startActivity(i);
                                finish();
                            }
                        });
                        //***************************

                    }
                }, 2000);


            }
        });

        cografya = (Button)findViewById(R.id.cografya);
        cografya.setOnClickListener(new View.OnClickListener() {
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
                            Intent i = new Intent(genel_kultur.this, cografya_sorular.class);
                            i.putExtra("dizi", cografya_sorusayisi);
                            startActivity(i);
                            finish();
                        }

                        mInterstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                // Code to be executed when the interstitial ad is closed.
                                dialog.dismiss();
                                Intent i = new Intent(genel_kultur.this, cografya_sorular.class);
                                i.putExtra("dizi", cografya_sorusayisi);
                                startActivity(i);
                                finish();
                            }
                        });
                        //***************************

                    }
                }, 2000);
            }
        });

        vatandaslik = (Button)findViewById(R.id.vatandaslik);
        vatandaslik.setOnClickListener(new View.OnClickListener() {
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
                            Intent i = new Intent(genel_kultur.this, vatandaslik_sorular.class);
                            i.putExtra("dizi", vatandaslik_sorusayisi);
                            startActivity(i);
                            finish();
                        }

                        mInterstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                // Code to be executed when the interstitial ad is closed.
                                dialog.dismiss();
                                Intent i = new Intent(genel_kultur.this, vatandaslik_sorular.class);
                                i.putExtra("dizi", vatandaslik_sorusayisi);
                                startActivity(i);
                                finish();
                            }
                        });
                        //***************************

                    }
                }, 2000);
            }
        });

        guncelbilgiler = (Button)findViewById(R.id.guncelbilgiler);
        guncelbilgiler.setOnClickListener(new View.OnClickListener() {
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
                            Intent i = new Intent(genel_kultur.this, guncelbilgiler_sorular.class);
                            i.putExtra("dizi", guncelbilgiler_sorusayisi);
                            startActivity(i);
                            finish();
                        }

                        mInterstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                // Code to be executed when the interstitial ad is closed.
                                dialog.dismiss();
                                Intent i = new Intent(genel_kultur.this, guncelbilgiler_sorular.class);
                                i.putExtra("dizi", guncelbilgiler_sorusayisi);
                                startActivity(i);
                                finish();
                            }
                        });
                        //***************************

                    }
                }, 2000);
            }
        });

        genelkulturkarisik = (Button)findViewById(R.id.genel_kultur_karisik);
        genelkulturkarisik.setOnClickListener(new View.OnClickListener() {
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
                            Intent i = new Intent(genel_kultur.this, genel_kultur_karisik.class);
                            i.putExtra("dizi1", tariha);
                            i.putExtra("dizi2", cografyaa);
                            i.putExtra("dizi3", vatandaslika);
                            i.putExtra("dizi4", guncelbilgilera);
                            startActivity(i);
                            finish();
                        }

                        mInterstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                // Code to be executed when the interstitial ad is closed.
                                dialog.dismiss();
                                Intent i = new Intent(genel_kultur.this, genel_kultur_karisik.class);
                                i.putExtra("dizi1", tariha);
                                i.putExtra("dizi2", cografyaa);
                                i.putExtra("dizi3", vatandaslika);
                                i.putExtra("dizi4", guncelbilgilera);
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
        Intent in = new Intent(genel_kultur.this, soru_bankasi_alan_secimi.class);
        finish();
        startActivity(in);
    }
}
