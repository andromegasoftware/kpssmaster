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
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class sonuc extends AppCompatActivity {

    int kategorino;

    DatabaseReference databaseReference;

    Button anasayfa1;
    Button cikis;

    TextView isim;
    TextView sorulan_soru_sayisi;
    TextView dogru_cevap_sayisi;
    TextView yanlis_cevap_sayisi;
    TextView bos_cevap_sayisi;
    TextView basari_orani;
    TextView kateg;

    String kullaniciisim;
    String kategori;
    double sorulansorusayisi;
    double dogrusayisi;
    double yanlissayisi;
    double bossayisi;
    double basariorani;

    TextView isim1;
    TextView isim2;
    TextView isim3;
    TextView isim4;
    TextView isim5;

    public InterstitialAd mInterstitialAd;  //Tam sayfa reklam göstermek için.
    SharedPreferences preferences;
    Boolean reklam_goster = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);

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

        anasayfa1 = (Button)findViewById(R.id.anasayfa);
        cikis = (Button)findViewById(R.id.cikis);



        isim = (TextView)findViewById(R.id.isim);
        sorulan_soru_sayisi = (TextView)findViewById(R.id.sorulan_soru_sayisi);
        dogru_cevap_sayisi = (TextView)findViewById(R.id.dogru_cevap_sayisi);
        yanlis_cevap_sayisi = (TextView)findViewById(R.id.yanlis_cevap_sayisi);
        bos_cevap_sayisi = (TextView)findViewById(R.id.bos_cevap_sayisi);
        basari_orani = (TextView)findViewById(R.id.basari_orani);
        kateg = (TextView)findViewById(R.id.kateg);

        isim1 = (TextView)findViewById(R.id.isim1);
        isim2 = (TextView)findViewById(R.id.isim2);
        isim3 = (TextView)findViewById(R.id.isim3);
        isim4 = (TextView)findViewById(R.id.isim4);
        isim5 = (TextView)findViewById(R.id.isim5);


        Intent i = getIntent();
        kullaniciisim = i.getStringExtra("isim");
        sorulansorusayisi = i.getDoubleExtra("sorusayisi", 0);
        dogrusayisi = i.getDoubleExtra("dogrusayisi", 0);
        yanlissayisi = i.getDoubleExtra("yanlissayisi", 0);
        bossayisi = i.getIntExtra("bossayisi", 0);
        basariorani = i.getDoubleExtra("basariorani", 0);
        kategori = i.getStringExtra("kategori");
        kategorino = i.getIntExtra("kategorino", 0);

       isim.setText("" + kullaniciisim);
       sorulan_soru_sayisi.setText("" + (int)sorulansorusayisi);
       dogru_cevap_sayisi.setText("" + (int)dogrusayisi);
       yanlis_cevap_sayisi.setText("" + (int)yanlissayisi);
       bos_cevap_sayisi.setText("" + (int)bossayisi);
       basari_orani.setText("% " + String.format("%.2f", basariorani));
       kateg.setText("" + kategori);

       if(kategorino == 1)
       {
           databaseReference = FirebaseDatabase.getInstance().getReference().child("skor").child("turkce");
       }
       else if(kategorino == 2)
        {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("skor").child("matematik");
        }
       else if(kategorino == 3)
       {
           databaseReference = FirebaseDatabase.getInstance().getReference().child("skor").child("geometri");
       }
       else if(kategorino == 4)
       {
           databaseReference = FirebaseDatabase.getInstance().getReference().child("skor").child("tarih");
       }
       else if(kategorino == 5)
       {
           databaseReference = FirebaseDatabase.getInstance().getReference().child("skor").child("cografya");
       }
       else if(kategorino == 6)
       {
           databaseReference = FirebaseDatabase.getInstance().getReference().child("skor").child("vatandaslik");
       }
       else if(kategorino == 7)
       {
           databaseReference = FirebaseDatabase.getInstance().getReference().child("skor").child("guncelbilgiler");
       }
       else if(kategorino == 8)
       {
           databaseReference = FirebaseDatabase.getInstance().getReference().child("skor").child("eğitim bilimleri");
       }
       else if(kategorino == 9)
       {
           databaseReference = FirebaseDatabase.getInstance().getReference().child("skor").child("genelkulturkarisik");
       }
       else if(kategorino == 10)
       {
           databaseReference = FirebaseDatabase.getInstance().getReference().child("skor").child("genelyetenekkarisik");
       }

       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               try{
               String skorisim1 = dataSnapshot.child("1").child("name").getValue().toString();
               double skor1 = Double.parseDouble(dataSnapshot.child("1").child("scor").getValue().toString());
               isim1.setText("1. " + skorisim1 + " %" + String.format("%.2f", skor1));
               }catch (Exception e){}

               try{
               String skorisim2 = dataSnapshot.child("2").child("name").getValue().toString();
               double skor2 = Double.parseDouble(dataSnapshot.child("2").child("scor").getValue().toString());
               isim2.setText("2. " + skorisim2 + " %" + String.format("%.2f", skor2));
               }catch (Exception e){}

               try{
               String skorisim3 = dataSnapshot.child("3").child("name").getValue().toString();
               double skor3 = Double.parseDouble(dataSnapshot.child("3").child("scor").getValue().toString());
               isim3.setText("3. " + skorisim3 + " %" + String.format("%.2f", skor3));
               }catch (Exception e){}

               try{
               String skorisim4 = dataSnapshot.child("4").child("name").getValue().toString();
               double skor4 = Double.parseDouble(dataSnapshot.child("4").child("scor").getValue().toString());
               isim4.setText("4. " + skorisim4 + " %" + String.format("%.2f", skor4));
               }catch (Exception e){}

               try{
               String skorisim5 = dataSnapshot.child("5").child("name").getValue().toString();
               double skor5 = Double.parseDouble(dataSnapshot.child("5").child("scor").getValue().toString());
               isim5.setText("5. " + skorisim5 + " %" + String.format("%.2f", skor5));
               }catch (Exception e){}

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });


        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aletDialog();
            }
        });
        anasayfa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*******intersteller ad başlar***********
                if (mInterstitialAd.isLoaded() && reklam_goster) {
                    mInterstitialAd.show();
                }

                else {
                    Intent i = new Intent(sonuc.this, anasayfa.class);
                    startActivity(i);
                    finish();
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i = new Intent(sonuc.this, anasayfa.class);
                        startActivity(i);
                        finish();
                    }
                });
                //***************************

            }
        });


    }

    public void aletDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(sonuc.this);
        alert.setTitle("Çıkış");
        alert.setMessage("Uygulamadan Çıkmak İstiyormusunuz?");
        alert.setCancelable(true);

        alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });

        alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //*******intersteller ad başlar***********
                if (mInterstitialAd.isLoaded() && reklam_goster) {
                    mInterstitialAd.show();
                }

                else {
                    finish();
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        finish();
                    }
                });
                //***************************

            }
        });

        AlertDialog al = alert.create();
        al.show();
    }

    @Override
    public void onBackPressed() {
        aletDialog();
    }
}
