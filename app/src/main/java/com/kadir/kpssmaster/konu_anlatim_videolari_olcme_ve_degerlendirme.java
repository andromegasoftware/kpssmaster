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

public class konu_anlatim_videolari_olcme_ve_degerlendirme extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    public static final String API_KEY = "AIzaSyBikGSQ6fCQZRMkifm7vfWvChHFEX7tFkI";
    String video_ismi;

    SharedPreferences preferences;
    Boolean reklam_goster = true;
    private AdView mAdView;

    String olcme_ve_degerlendirme_konular[];
    YouTubePlayerView youTubePlayerView;

    public InterstitialAd mInterstitialAd;  //Tam sayfa reklam göstermek için.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konu_anlatim_videolari_olcme_ve_degerlendirme);

        Intent i = getIntent();
        video_ismi = i.getStringExtra("video_ismi");
        ListView list = (ListView)findViewById(R.id.listview);
        olcme_ve_degerlendirme_konular = getResources().getStringArray(R.array.olcme_ve_degerlendirme_konular);

        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, olcme_ve_degerlendirme_konular);
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
                        String VIDEO_ID = "2o7UyQOyxpI";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "2o7UyQOyxpI";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "fKYrq_-NlRY";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "fKYrq_-NlRY";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "5q0I-j2hhjg";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "5q0I-j2hhjg";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "dZde4m7tCzs";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "dZde4m7tCzs";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "VlCBfwp_Cas";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "VlCBfwp_Cas";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "r8vmYsVWjbM";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "r8vmYsVWjbM";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "iCbNlSJa9dQ";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "iCbNlSJa9dQ";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "teJ-uSqWnOI";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "teJ-uSqWnOI";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "YLG-a9yMrZM";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "YLG-a9yMrZM";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "ukEVPKcQ0Ug";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "ukEVPKcQ0Ug";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "dLtjTIJq4Gk";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "dLtjTIJq4Gk";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "IDy6aVumhpI";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "IDy6aVumhpI";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "6RJgztPEfTk";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "6RJgztPEfTk";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "PHo66PQARPE";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "PHo66PQARPE";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "LunV6nUf9ws";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "LunV6nUf9ws";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "4msS-tBOyf0";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "4msS-tBOyf0";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "6fCzSXFP6To";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "6fCzSXFP6To";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "dM8qdq3BfbQ";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "dM8qdq3BfbQ";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "6edoCtpTsnk";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "6edoCtpTsnk";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "k8cRuUfiR9I";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "k8cRuUfiR9I";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "B49sCeLq1aM";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "B49sCeLq1aM";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
                        String VIDEO_ID = "F6OghYYhCmw";
                        Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                        i.putExtra("video_ismi", VIDEO_ID);
                        startActivity(i);
                        finish();
                    }

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            // Code to be executed when the interstitial ad is closed.
                            String VIDEO_ID = "F6OghYYhCmw";
                            Intent i = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
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
        AlertDialog.Builder alert = new AlertDialog.Builder(konu_anlatim_videolari_olcme_ve_degerlendirme.this);
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
                    Intent in = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, anasayfa.class);
                    finish();
                    startActivity(in);
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the interstitial ad is closed.
                        Intent in = new Intent(konu_anlatim_videolari_olcme_ve_degerlendirme.this, anasayfa.class);
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
