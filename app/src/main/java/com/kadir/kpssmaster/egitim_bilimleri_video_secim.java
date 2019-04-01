package com.kadir.kpssmaster;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class egitim_bilimleri_video_secim extends AppCompatActivity {

    Button olcme_ve_degerlendirme;
    Button rehberlik_ve_ozel_egitim;
    Button ogretim_ilke_ve_yontemleri;
    Button ogrenme_psikolojisi;
    Button gelisim_psikolojisi;
    String isim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egitim_bilimleri_video_secim);

        Intent i = getIntent();
        isim = i.getStringExtra("isim");

         olcme_ve_degerlendirme = (Button)findViewById(R.id.olcme_ve_degerlendirme);
         rehberlik_ve_ozel_egitim = (Button)findViewById(R.id.rehberlik_ve_ozel_egitim);
         ogretim_ilke_ve_yontemleri = (Button)findViewById(R.id.ogretim_ilke);
         ogrenme_psikolojisi = (Button)findViewById(R.id.ogrenme_psikolojisi);
         gelisim_psikolojisi = (Button)findViewById(R.id.gelisim_psikolojisi);

         olcme_ve_degerlendirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String VIDEO_ID = "zJAC-3XY9X8";
                Intent i = new Intent(egitim_bilimleri_video_secim.this, konu_anlatim_videolari_olcme_ve_degerlendirme.class);
                i.putExtra("video_ismi", VIDEO_ID);
                startActivity(i);
                finish();
            }
        });
        rehberlik_ve_ozel_egitim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String VIDEO_ID = "2o7UyQOyxpI";
                Intent i = new Intent(egitim_bilimleri_video_secim.this, konu_anlatim_videolari_rehberlik_ve_ozel_egitim.class);
                i.putExtra("video_ismi", VIDEO_ID);
                startActivity(i);
                finish();
            }
        });
        ogretim_ilke_ve_yontemleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String VIDEO_ID = "uoJN5Mpq87w";
                Intent i = new Intent(egitim_bilimleri_video_secim.this, konu_anlatim_videolari_ogretim_ilke_ve_yontemleri.class);
                i.putExtra("video_ismi", VIDEO_ID);
                startActivity(i);
                finish();
            }
        });

        ogrenme_psikolojisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String VIDEO_ID = "whBWWxItvV0";
                Intent i = new Intent(egitim_bilimleri_video_secim.this, konu_anlatim_videolari_ogrenme_psikolojisi.class);
                i.putExtra("video_ismi", VIDEO_ID);
                startActivity(i);
                finish();
            }
        });

        gelisim_psikolojisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String VIDEO_ID = "eHtxSMSZDpc";
                Intent i = new Intent(egitim_bilimleri_video_secim.this, konu_anlatim_videolari_gelisim_psikolojisi.class);
                i.putExtra("video_ismi", VIDEO_ID);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        aletDialog();
    }

    public void aletDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(egitim_bilimleri_video_secim.this);
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

                Intent in = new Intent(egitim_bilimleri_video_secim.this, anasayfa.class);
                in.putExtra("isim", isim);
                finish();
                startActivity(in);
            }
        });

        AlertDialog al = alert.create();
        al.show();
    }
}
