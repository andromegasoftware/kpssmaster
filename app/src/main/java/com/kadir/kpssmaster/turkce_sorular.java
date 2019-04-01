package com.kadir.kpssmaster;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class turkce_sorular extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    int kategoritürkce = 1;

    DatabaseReference database1;
    DatabaseReference database2;
    DatabaseReference database3;
    DatabaseReference database4;
    DatabaseReference database5;

    double genelskor1;
    double genelskor2;
    double genelskor3;
    double genelskor4;
    double genelskor5;
    double basariorani;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    Random r = new Random();
    int soruno;

    String dogru_cevap;
    String kullanicicevapa;
    String kullanicicevapb;
    String kullanicicevapc;
    String kullanicicevapd;
    String kullanicicevape;

    String sorular;
    String sorulara;
    String sorularb;
    String sorularc;
    String sorulard;
    String sorulare;


    TextView cevapa;
    TextView cevapb;
    TextView cevapc;
    TextView cevapd;
    TextView cevape;

    Button diger_soruya_gec;

    ImageView imagesoru;

    DatabaseReference database;
    DatabaseReference databasea;
    DatabaseReference databaseb;
    DatabaseReference databasec;
    DatabaseReference databased;
    DatabaseReference databasee;
    DatabaseReference dogrucevap;

    long e;

    private AdView mAdView;

    TextView sure;
    TextView dogru;
    TextView yanlis;
    TextView bos;
    Boolean boskontrol = true;

    private static final long START_TIME_IN_MILIS = 90000;

    CountDownTimer countDownTimer;
    boolean mTimerRunning;
    long mTimeLeftINMillis = START_TIME_IN_MILIS;

    double sorulan_soru_sayisi = 0;
    double dogrusayisi = 0;
    double yanlissayisi = 0;
    int bossayisi = 0;

    String kategori = "TÜRKÇE KATEGORİSİNDEKİ SKORLAR";

    SharedPreferences preferences;
    Boolean reklam_goster = true;

    //*****shared preferences ve isim tanımlama****
    String isim;
    ShredPref shredPref;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turkce_sorular);

       /* mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = mCurrentUser.getUid();

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);
        mUserDatabase.keepSynced(true);
        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    isim = dataSnapshot.child("isim").getValue().toString();

                }catch (Exception e){}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        //**************isimi shered preference dan çekme***********
        shredPref = new ShredPref();
        isim = shredPref.getValue(context, "user");

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        reklam_goster = preferences.getBoolean("goster", true);

        sure = (TextView)findViewById(R.id.sure);
        dogru = (TextView)findViewById(R.id.dogru);
        yanlis = (TextView)findViewById(R.id.yanlis);
        bos = (TextView)findViewById(R.id.bos);


        MobileAds.initialize(this, "ca-app-pub-3206398076180977~6110635794");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3206398076180977/9199833568");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
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
        });

        Intent in = getIntent();

        e = in.getLongExtra("dizi",0);


        imagesoru = (ImageView)findViewById(R.id.imagesoru);
        cevapa = (TextView) findViewById(R.id.cevap_a);
        cevapb = (TextView) findViewById(R.id.cevap_b);
        cevapc = (TextView) findViewById(R.id.cevap_c);
        cevapd = (TextView) findViewById(R.id.cevap_d);
        cevape = (TextView) findViewById(R.id.cevap_e);

        diger_soruya_gec = (Button) findViewById(R.id.diger_soru);

        oyundevam();
        genelskorlaricek();

        cevapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                boskontrol = false;

                pauseTimer();
                resetTimer();
                updateCountDownText();

                cevapa.setEnabled(false);
                cevapb.setEnabled(false);
                cevapc.setEnabled(false);
                cevapd.setEnabled(false);
                cevape.setEnabled(false);

                dogrucevabiisaretle();

                if(kullanicicevapa.equals(dogru_cevap) )
                {
                    dogrusayisi = dogrusayisi + 1;
                    dogru.setText("" + (int)dogrusayisi);
                    cevapa.setBackgroundResource(R.drawable.buton_dogru);
                }
                else
                {
                    yanlissayisi = yanlissayisi + 1;
                    yanlis.setText("" + (int)yanlissayisi);
                    cevapa.setBackgroundResource(R.drawable.buton_yanlis);
                }
                }catch (NullPointerException e){}
            }
        });
        cevapb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                boskontrol = false;

                pauseTimer();
                resetTimer();
                updateCountDownText();

                cevapa.setEnabled(false);
                cevapb.setEnabled(false);
                cevapc.setEnabled(false);
                cevapd.setEnabled(false);
                cevape.setEnabled(false);

                dogrucevabiisaretle();

                if(kullanicicevapb.equals(dogru_cevap))
                {
                    dogrusayisi = dogrusayisi + 1;
                    dogru.setText("" + (int)dogrusayisi);
                    cevapb.setBackgroundResource(R.drawable.buton_dogru);
                }
                else
                {
                    yanlissayisi = yanlissayisi + 1;
                    yanlis.setText("" + (int)yanlissayisi);
                    cevapb.setBackgroundResource(R.drawable.buton_yanlis);
                }
                }catch (NullPointerException e){}
            }
        });
        cevapc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                boskontrol = false;

                pauseTimer();
                resetTimer();
                updateCountDownText();

                cevapb.setEnabled(false);
                cevapa.setEnabled(false);
                cevapc.setEnabled(false);
                cevapd.setEnabled(false);
                cevape.setEnabled(false);

                dogrucevabiisaretle();

                if(kullanicicevapc.equals(dogru_cevap))
                {
                    dogrusayisi = dogrusayisi + 1;
                    dogru.setText("" + (int)dogrusayisi);
                    cevapc.setBackgroundResource(R.drawable.buton_dogru);
                }
                else
                {
                    yanlissayisi = yanlissayisi + 1;
                    yanlis.setText("" + (int)yanlissayisi);
                    cevapc.setBackgroundResource(R.drawable.buton_yanlis);
                }
                }catch (NullPointerException e){}
            }
        });
        cevapd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                boskontrol = false;

                pauseTimer();
                resetTimer();
                updateCountDownText();

                cevapb.setEnabled(false);
                cevapc.setEnabled(false);
                cevapa.setEnabled(false);
                cevapd.setEnabled(false);
                cevape.setEnabled(false);

                dogrucevabiisaretle();

                if(kullanicicevapd.equals(dogru_cevap))
                {
                    dogrusayisi = dogrusayisi + 1;
                    dogru.setText("" + (int)dogrusayisi);
                    cevapd.setBackgroundResource(R.drawable.buton_dogru);
                }
                else
                {
                    yanlissayisi = yanlissayisi + 1;
                    yanlis.setText("" + (int)yanlissayisi);
                    cevapd.setBackgroundResource(R.drawable.buton_yanlis);
                }
                }catch (NullPointerException e){}
            }
        });
        cevape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                boskontrol = false;

                pauseTimer();
                resetTimer();
                updateCountDownText();

                cevapb.setEnabled(false);
                cevapc.setEnabled(false);
                cevapd.setEnabled(false);
                cevapa.setEnabled(false);
                cevape.setEnabled(false);

                dogrucevabiisaretle();

                if(kullanicicevape.equals(dogru_cevap))
                {
                    dogrusayisi = dogrusayisi + 1;
                    dogru.setText("" + (int)dogrusayisi);
                    cevape.setBackgroundResource(R.drawable.buton_dogru);
                }
                else
                {
                    yanlissayisi = yanlissayisi + 1;
                    yanlis.setText("" + (int)yanlissayisi);
                    cevape.setBackgroundResource(R.drawable.buton_yanlis);
                }
                }catch (NullPointerException e){}
            }
        });

        diger_soruya_gec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(boskontrol)
                    {
                        bossayisi = bossayisi + 1;
                        bos.setText("" + (int)bossayisi);
                    }
                    oyundevam();
                    pauseTimer();
                    resetTimer();
                    updateCountDownText();
                    startTimer();
            }
        });



        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_vi);
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.sinavibitir)
        {
            basariorani = ((dogrusayisi-(yanlissayisi/4))/sorulan_soru_sayisi)*100;


            if(genelskor1 < basariorani)
            {
                database1.child("name").setValue(isim);
                database1.child("scor").setValue(basariorani);
            }
            else if(genelskor2 < basariorani)
            {
                database2.child("name").setValue(isim);
                database2.child("scor").setValue(basariorani);
            }
            else if(genelskor3 < basariorani)
            {
                database3.child("name").setValue(isim);
                database3.child("scor").setValue(basariorani);
            }
            else if(genelskor4 < basariorani)
            {
                database4.child("name").setValue(isim);
                database4.child("scor").setValue(basariorani);
            }
            else if(genelskor5 < basariorani)
            {
                database5.child("name").setValue(isim);
                database5.child("scor").setValue(basariorani);
            }


            Intent i = new Intent(turkce_sorular.this, sonuc.class);
            i.putExtra("sorusayisi", sorulan_soru_sayisi);
            i.putExtra("dogrusayisi", dogrusayisi);
            i.putExtra("yanlissayisi", yanlissayisi);
            i.putExtra("bossayisi", bossayisi);
            i.putExtra("basariorani", basariorani);
            i.putExtra("isim", isim);
            i.putExtra("kategori", kategori);
            i.putExtra("kategorino", kategoritürkce);
            startActivity(i);
            finish();

        }

        if(id == R.id.sureyidurdur)
        {
           pauseTimer();
        }
        if(id == R.id.anasayfayadon)
        {
           aletDialog();
        }
        if(id == R.id.dogrucevabigoster)
        {
            dogrucevabiisaretle();
        }
        if(id == R.id.hatalisoru)
        {
            hatalisorubildir();
        }


        return false;
    }

    public void oyundevam()
    {
        sorulan_soru_sayisi++;

        soruno = r.nextInt((int) e) + 1;

        System.out.println("Soru no: " + soruno);

        boskontrol = true;

        cevapa.setEnabled(true);
        cevapb.setEnabled(true);
        cevapc.setEnabled(true);
        cevapd.setEnabled(true);
        cevape.setEnabled(true);

        cevapa.setBackgroundResource(R.drawable.butonsekil);
        cevapb.setBackgroundResource(R.drawable.butonsekil);
        cevapc.setBackgroundResource(R.drawable.butonsekil);
        cevapd.setBackgroundResource(R.drawable.butonsekil);
        cevape.setBackgroundResource(R.drawable.butonsekil);

        if(mTimerRunning)
        {
            pauseTimer();
        }
        else
        {
            startTimer();
        }

        database = FirebaseDatabase.getInstance().getReference().child("turkce").child(String.valueOf(soruno)).child("s");
        databasea = FirebaseDatabase.getInstance().getReference().child("turkce").child(String.valueOf(soruno)).child("a");
        databaseb = FirebaseDatabase.getInstance().getReference().child("turkce").child(String.valueOf(soruno)).child("b");
        databasec = FirebaseDatabase.getInstance().getReference().child("turkce").child(String.valueOf(soruno)).child("c");
        databased = FirebaseDatabase.getInstance().getReference().child("turkce").child(String.valueOf(soruno)).child("d");
        databasee = FirebaseDatabase.getInstance().getReference().child("turkce").child(String.valueOf(soruno)).child("e");
        dogrucevap = FirebaseDatabase.getInstance().getReference().child("turkce").child(String.valueOf(soruno)).child("dc");

        database.keepSynced(true);
        databasea.keepSynced(true);
        databaseb.keepSynced(true);
        databasec.keepSynced(true);
        databased.keepSynced(true);
        databasee.keepSynced(true);
        dogrucevap.keepSynced(true);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    sorular = dataSnapshot.getValue().toString();

                    Picasso.with(turkce_sorular.this).load(sorular).into(imagesoru);
                }catch (NullPointerException e){oyundevam();}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databasea.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try{
                sorulara = dataSnapshot.getValue().toString();
                kullanicicevapa = dataSnapshot.getKey();
                cevapa.setText("A)  " + sorulara);
                }catch (NullPointerException e){}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try{
                sorularb = dataSnapshot.getValue().toString();
                kullanicicevapb = dataSnapshot.getKey();
                cevapb.setText("B)  " + sorularb);
                }catch (NullPointerException e){}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databasec.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try{
                sorularc = dataSnapshot.getValue().toString();
                kullanicicevapc = dataSnapshot.getKey();
                cevapc.setText("C)  " + sorularc);
                }catch (NullPointerException e){}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databased.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try{
                sorulard = dataSnapshot.getValue().toString();
                kullanicicevapd = dataSnapshot.getKey();
                cevapd.setText("D)  " + sorulard);
                }catch (NullPointerException e){}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databasee.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try{
                sorulare = dataSnapshot.getValue().toString();
                kullanicicevape = dataSnapshot.getKey();
                cevape.setText("E)  " + sorulare);
                }catch (NullPointerException e){}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dogrucevap.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try{
                dogru_cevap = dataSnapshot.getValue().toString();
                }catch (NullPointerException e){}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void resetTimer()
    {
        mTimeLeftINMillis = START_TIME_IN_MILIS;
        updateCountDownText();


    }
    public void startTimer()
    {
        countDownTimer = new CountDownTimer(mTimeLeftINMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftINMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {

                mTimerRunning = false;

                pauseTimer();
                resetTimer();
                updateCountDownText();

                cevapa.setEnabled(false);
                cevapb.setEnabled(false);
                cevapc.setEnabled(false);
                cevapd.setEnabled(false);
                cevape.setEnabled(false);

                dogrucevabiisaretle();

                bossayisi = bossayisi + 1;
                bos.setText("" + (int)bossayisi);

            }
        }.start();

        mTimerRunning = true;
    }

    public void updateCountDownText()
    {

        int seconds = (int) (mTimeLeftINMillis / 1000) % 90;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d", seconds);

        sure.setText(timeLeftFormatted);

    }

    public void pauseTimer()
    {
        countDownTimer.cancel();
        mTimerRunning = false;

    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseTimer();

    }
    @Override
    public void onBackPressed() {
       aletDialog();
    }

    public void aletDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(turkce_sorular.this);
        alert.setTitle("Çıkış");
        alert.setMessage("Sınavı Bitirip Çıkmak İstiyormusunuz?");
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

                pauseTimer();

                Intent in = new Intent(turkce_sorular.this, anasayfa.class);
                finish();
                startActivity(in);
            }
        });

        AlertDialog al = alert.create();
        al.show();
    }
    public void genelskorlaricek()
    {
        database1 = FirebaseDatabase.getInstance().getReference().child("skor").child("turkce").child("1");
        database2 = FirebaseDatabase.getInstance().getReference().child("skor").child("turkce").child("2");
        database3 = FirebaseDatabase.getInstance().getReference().child("skor").child("turkce").child("3");
        database4 = FirebaseDatabase.getInstance().getReference().child("skor").child("turkce").child("4");
        database5 = FirebaseDatabase.getInstance().getReference().child("skor").child("turkce").child("5");

        database1.keepSynced(true);
        database2.keepSynced(true);
        database3.keepSynced(true);
        database4.keepSynced(true);
        database5.keepSynced(true);

        database1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                genelskor1 = Double.parseDouble(dataSnapshot.child("scor").getValue().toString());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                genelskor2 = Double.parseDouble(dataSnapshot.child("scor").getValue().toString());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                genelskor3 = Double.parseDouble(dataSnapshot.child("scor").getValue().toString());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                genelskor4 = Double.parseDouble(dataSnapshot.child("scor").getValue().toString());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                genelskor5 = Double.parseDouble(dataSnapshot.child("scor").getValue().toString());



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public void dogrucevabiisaretle()
    {
        if(dogru_cevap.equals("a"))
        {
            cevapa.setBackgroundResource(R.drawable.buton_dogru);
        }
        else if(dogru_cevap.equals("b"))
        {
            cevapb.setBackgroundResource(R.drawable.buton_dogru);
        }
        else if(dogru_cevap.equals("c"))
        {
            cevapc.setBackgroundResource(R.drawable.buton_dogru);
        }
        else if(dogru_cevap.equals("d"))
        {
            cevapd.setBackgroundResource(R.drawable.buton_dogru);
        }
        else if(dogru_cevap.equals("e"))
        {
            cevape.setBackgroundResource(R.drawable.buton_dogru);
        }
    }
    private void hatalisorubildir()
    {

        DatabaseReference hatali_soru = FirebaseDatabase.getInstance().getReference().child("hatali soru");
        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("kategori", "türkçe");
        userMap.put("soru no", String.valueOf(soruno));
        hatali_soru.push().setValue(userMap);

        AlertDialog.Builder alert = new AlertDialog.Builder(turkce_sorular.this);
        alert.setTitle("Geri Bildiriminiz İçin Teşekkürler.");
        alert.setCancelable(true);

        alert.setNegativeButton("Tamam", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });

        AlertDialog al = alert.create();
        al.show();
    }
}
