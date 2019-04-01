package com.kadir.kpssmaster;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class anasayfa extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BillingProcessor.IBillingHandler{

    String mesaj; // Bildirim Mesajı

    CountDownTimer countDownTimer;
    boolean mTimerRunning;
    Calendar calendar;
    long mTimeLeftINMillis ;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    String shareBody = "https://play.google.com/store/apps/details?id=com.kadir.kpssmaster";
    TextView username;

    //*****shared preferences ve isim tanımlama****
    String isim;
    ShredPref shredPref;
    Context context = this;

    Button sorubankasi;
    Button puanhesaplama;
    Button video;
    Button forum;
    Button diger_uygulamalar;
    TextView timer;

    BillingProcessor bp;
    Button reklamlari_kaldir;
    Boolean reklam_goster = true;
    SharedPreferences preferences;

    ConnectionDetector cd;

    public InterstitialAd mInterstitialAd;  //Tam sayfa reklam göstermek için.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);

        //***********************************************************************************
        MobileAds.initialize(this, "ca-app-pub-3206398076180977~6110635794");

        //**************inersteller ad***********************
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3206398076180977/4255379285");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        //******************************************************

        //cihazın internet bağlantı kontrolü burada yapılıyor.
        cd = new ConnectionDetector(this);

        if(cd.isConnected()){

        }
        else{
            Toast.makeText(anasayfa.this, "Lütfen İnternet Bağlantınızı Kontrol Ediniz. Uygulama İnternet Olmadan Çalışmaz!",
                    Toast.LENGTH_LONG).show();
        }

        username = (TextView)findViewById(R.id.kullanici_name);

        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5L/58elINrH9bdaLumYSJToPKx9Le2GJq7cGX7VKEK+aFbGCCUXCX0YJfJZKveAeKveN7VM/BviS3qckOtOhqGJkzEfdllSABgXi2XPaV1de4qtqBBogob3W/z/HXSCzEhZmhKEdIuZiAYk4a67+bQmrLbxdw8d9tRar4e4+LRJNa5XJPs+LAJXgqT7Og86HuzvV6YVlKzgFEyv+eA//CJ7F9g4/+Nkk8amgjP4CY0f9EW/q9la9g+jNnoQQxqA5ppVpTCE/50ky1YBjsWhUFSkquDsyZ2ogi67RuAN+kU6RN33Sp83ppqegfLk1uZnh05tOSUPYUS23zeSTACk8FwIDAQAB", this);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        reklam_goster = preferences.getBoolean("goster", true);

        bildirimal();

        reklamlari_kaldir = (Button)findViewById(R.id.reklam_kaldir);
        if(reklam_goster == true)
        {
            reklamlari_kaldir.setVisibility(View.VISIBLE);
        }
        else
        {
            reklamlari_kaldir.setVisibility(View.INVISIBLE);
        }
        reklamlari_kaldir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bp.purchase(anasayfa.this, "reklamkaldir");
            }
        });

        /*
        firebase bağlantısını kontrol etme
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {

                }
                else {

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });*/

        //**************isimi shered preference dan çekme***********
        shredPref = new ShredPref();
        isim = shredPref.getValue(context, "user");
        username.setText(isim);

        sorubankasi = (Button)findViewById(R.id.soru_bankasi);
        puanhesaplama = (Button)findViewById(R.id.puan_hesaplama);
        video = (Button)findViewById(R.id.video);
        forum = (Button)findViewById(R.id.forum);
        timer = (TextView) findViewById(R.id.time);
        diger_uygulamalar = (Button)findViewById(R.id.digeruygulamalar);

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(anasayfa.this, konu_anlatim_video_secim.class);
                startActivity(i);
                finish();
            }
        });

        sorubankasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(anasayfa.this, soru_bankasi_alan_secimi.class);
                i.putExtra("isim", isim);
                startActivity(i);
                finish();
            }
        });

        puanhesaplama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*******intersteller ad başlar***********
                if (mInterstitialAd.isLoaded() && reklam_goster) {
                    mInterstitialAd.show();
                }

                else {
                    Intent i = new Intent(anasayfa.this, puan_hesaplama.class);
                    i.putExtra("isim", isim);
                    startActivity(i);
                    finish();
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i = new Intent(anasayfa.this, puan_hesaplama.class);
                        i.putExtra("isim", isim);
                        startActivity(i);
                        finish();
                    }
                });
                //***************************

            }
        });


        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*******intersteller ad başlar***********
                if (mInterstitialAd.isLoaded() && reklam_goster) {
                    mInterstitialAd.show();
                }

                else {
                    Intent i = new Intent(anasayfa.this, forum.class);
                    i.putExtra("isim", isim);
                    startActivity(i);
                    finish();
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent i = new Intent(anasayfa.this, forum.class);
                        i.putExtra("isim", isim);
                        startActivity(i);
                        finish();
                    }
                });
                //***************************

            }
        });

        diger_uygulamalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(anasayfa.this, diger_uygulamalar.class);
                startActivity(i);
                finish();
            }
        });

        startTimer();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
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
        if(id == R.id.bildirim_page)
        {
            Intent in = new Intent(anasayfa.this, bildirim_sayfasi.class);
            startActivity(in);
        }
        if(id == R.id.paylas)
        {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Harika Uygulama");
            i.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(i, "Mutlaka Denemelisin"));
        }
        if(id == R.id.puanver)
        {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market/details?id=com.kadir.kpssmaster")));
            }
            catch (ActivityNotFoundException e)
            {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.kadir.kpssmaster")));
            }
        }
        if(id == R.id.sifredegistir)
        {
            Intent i = new Intent(anasayfa.this, Sifre_Degistir.class);
            startActivity(i);
        }
        if(id == R.id.cikis)
        {
            Intent i = new Intent(anasayfa.this, mail_giris.class);
            startActivity(i);
            finish();
        }



        return false;
    }



    public void onBackPressed() {
        aletDialog();
    }

    public void aletDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(anasayfa.this);
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
                finish();

            }
        });

        AlertDialog al = alert.create();
        al.show();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        reklam_goster = false;
        Toast.makeText(getApplicationContext(),"İşleminiz Başarıyla Gerçekleştirilmiştir.", Toast.LENGTH_LONG).show();
        SharedPreferences.Editor editor =  preferences.edit();
        editor.putBoolean("goster", reklam_goster);
        editor.commit();
        reklamlari_kaldir.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        Toast.makeText(getApplicationContext(),"Üzgünüm Bir Hata Oluştu. Lütfen Daha Sonra Tekrar Deneyiniz. Veya Uygulama Yöneticisiyle İrtibata geçiniz.", Toast.LENGTH_LONG).show();
        reklam_goster = true;
        SharedPreferences.Editor editor =  preferences.edit();
        editor.putBoolean("goster", reklam_goster);
        editor.commit();
        reklamlari_kaldir.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBillingInitialized() {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }
    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(anasayfa.this);
        View promptView = layoutInflater.inflate(R.layout.input_diaolg, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(anasayfa.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String yeni_isim = String.valueOf(editText.getText());
                        isim = yeni_isim;
                        username.setText(yeni_isim);
                    }
                })
                .setNegativeButton("İptal",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


    public void startTimer()
    {
        calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.JULY, 14, 10, 15);
        mTimeLeftINMillis = calendar.getTimeInMillis()-System.currentTimeMillis();
        System.out.println("current time: " + System.currentTimeMillis());
        System.out.println("future time: " + calendar.getTimeInMillis() );
        System.out.println("fark süresi: " + mTimeLeftINMillis);
        countDownTimer = new CountDownTimer(mTimeLeftINMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftINMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {

                mTimerRunning = false;

            }
        }.start();

        mTimerRunning = true;
    }
    public void updateCountDownText()
    {
        long ay = 2628066667L;
        long gun = (mTimeLeftINMillis % ay)  ;
        long  saat= mTimeLeftINMillis % 86400000;
        long dakika = mTimeLeftINMillis % 3600000;
        long saniye = mTimeLeftINMillis % 60000;

        long month = (mTimeLeftINMillis / ay);
        long day = (gun / 86400000);
        long hour = saat / 3600000;
        long minute = dakika / 60000;
        long seconds = saniye / 1000;

        //String timeLeftFormatted = String.format(Locale.getDefault(), "%02d, %02d, %02d, %02d, %02d", month, day, hour, minute, seconds);
        timer.setText("Genel Yetenek-Genel Kültür Sınavına Kalan Süre\n" + month + " Ay " + day + " Gün " + hour + " Saat " + minute + " dakika "+ seconds + " Saniye");
    }

    public void bildirimal()
    {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Tag", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg =  token;
                        Log.d("Tag", msg);
                        System.out.println("bize lazım olan şey: " + msg);

                    }
                });

        if(getIntent().getExtras() != null)
        {
            mesaj = getIntent().getExtras().getString("mesaj");
            if(mesaj == null)
            {
                mesaj = "Yeni Mesajınız Yok";
            }
            SharedPreferences.Editor editor =  preferences.edit();
            editor.putString("notification", mesaj);
            editor.commit();
            System.out.println("Bildirim: " + mesaj);
        }

    }


}
