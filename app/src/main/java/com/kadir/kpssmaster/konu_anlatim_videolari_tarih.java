package com.kadir.kpssmaster;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class konu_anlatim_videolari_tarih extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    public static final String API_KEY = "AIzaSyBikGSQ6fCQZRMkifm7vfWvChHFEX7tFkI";
    String video_ismi;

    SharedPreferences preferences;
    Boolean reklam_goster = true;
    private AdView mAdView;

    String tarih_konular[];
    YouTubePlayerView youTubePlayerView;

    public InterstitialAd mInterstitialAd;  //Tam sayfa reklam göstermek için.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konu_anlatim_videolari_tarih);

        Intent i = getIntent();
        video_ismi = i.getStringExtra("video_ismi");
        ListView list = (ListView)findViewById(R.id.listview);
        tarih_konular = getResources().getStringArray(R.array.tarih_konular);

        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, tarih_konular);
        list.setAdapter(veriAdaptoru);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        reklam_goster = preferences.getBoolean("goster", true);

        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);

        MobileAds.initialize(this, "ca-app-pub-3206398076180977~6110635794");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3206398076180977/9199833568");

        //**************inersteller ad***********************
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3206398076180977/4255379285");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        //******************************************************

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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pozisyon, long l) {
                if(pozisyon == 0)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "pycVrv3Ivt4";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "pycVrv3Ivt4";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 1)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "Jo8cyg6SYOY";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "Jo8cyg6SYOY";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 2)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "R9QobgVd8rI";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "R9QobgVd8rI";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 3)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "vqbmraFLTmg";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "vqbmraFLTmg";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 4)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "M-L7vZI5cK4";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "M-L7vZI5cK4";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 5)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "3o5AJNUlOg0";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "3o5AJNUlOg0";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 6)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "-MvqtyjzR8g";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "-MvqtyjzR8g";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 7)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "XNJjmXhT-DY";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "XNJjmXhT-DY";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 8)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "g-igFjzcbEw";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "g-igFjzcbEw";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 9)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "Z2E-fzbSsyA";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "Z2E-fzbSsyA";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************

                }
                else if(pozisyon == 10)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "uqtcwTFSS9Q";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "uqtcwTFSS9Q";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 11)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "DabcjCG6reg";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "DabcjCG6reg";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 12)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "vuf6UQr3_xU";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "vuf6UQr3_xU";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 13)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "FS1tSxgrJzs";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "FS1tSxgrJzs";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 14)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "7Bk2BoPYnf0";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "7Bk2BoPYnf0";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 15)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "shHJHkXeHP8";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "shHJHkXeHP8";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 16)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "wT_A20sXZis";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "wT_A20sXZis";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 17)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "hXGDHHNsn_A";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "hXGDHHNsn_A";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 18)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "IMRuHEgFVGg";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "IMRuHEgFVGg";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 19)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "wqCyuSjd4Oc";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "wqCyuSjd4Oc";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 20)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "QU9AxpERFk0";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "QU9AxpERFk0";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 21)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "CjYzCoMNgg4";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "CjYzCoMNgg4";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 22)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "xU6T3QgMu8I";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "xU6T3QgMu8I";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 23)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "eloqHQJtPn8";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "eloqHQJtPn8";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 24)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "rqrW0RLQe9M";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "rqrW0RLQe9M";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 25)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "hEwe2DBzqE4";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "hEwe2DBzqE4";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 26)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "kwYx3_RresQ";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "kwYx3_RresQ";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 27)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "7PwxNO_EgR0";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "7PwxNO_EgR0";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 28)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "bzOejf0h8xQ";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "bzOejf0h8xQ";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 29)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "XhLabJSbRmw";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "XhLabJSbRmw";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 30)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "wiEVNmlX-M4";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "wiEVNmlX-M4";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 31)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "4ugpPIR_dTo";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "4ugpPIR_dTo";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 32)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "dBJ53iZ5MC0";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "dBJ53iZ5MC0";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 33)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "bWRcZF7cQLw";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "bWRcZF7cQLw";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 34)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "UqfjbBnekpM";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "UqfjbBnekpM";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 35)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "ltV6KBB5JXc";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "ltV6KBB5JXc";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 36)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "-uEeKNS4cSQ";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "-uEeKNS4cSQ";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 37)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "LTfkdbQO6cU";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "LTfkdbQO6cU";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 38)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "8hCLR53TGRg";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "8hCLR53TGRg";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
                else if(pozisyon == 39)
                {
                    //*******intersteller ad başlar***********
                    if (mInterstitialAd.isLoaded() && reklam_goster) {
                        mInterstitialAd.show();
                    }

                    else {
                        String VIDEO_ID = "asZIhuTN2EY";
                        Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "asZIhuTN2EY";
                            Intent i = new Intent(konu_anlatim_videolari_tarih.this, konu_anlatim_videolari_tarih.class);
                            i.putExtra("video_ismi", VIDEO_ID);
                            startActivity(i);
                            finish();
                        }
                    });
                    //***************************
                }
            }
        });

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        //start buffering
        if(!b)
        {
            youTubePlayer.cueVideo(video_ismi);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };

    @Override
    public void onBackPressed() {
        aletDialog();
    }

    public void aletDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(konu_anlatim_videolari_tarih.this);
        alert.setTitle("Çıkış");
        alert.setMessage("Çıkmak İstiyormusunuz?");
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
                    Intent in = new Intent(konu_anlatim_videolari_tarih.this, anasayfa.class);
                    finish();
                    startActivity(in);
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent in = new Intent(konu_anlatim_videolari_tarih.this, anasayfa.class);
                        finish();
                        startActivity(in);
                    }
                });
                //***************************
            }
        });

        AlertDialog al = alert.create();
        al.show();
    }
}
