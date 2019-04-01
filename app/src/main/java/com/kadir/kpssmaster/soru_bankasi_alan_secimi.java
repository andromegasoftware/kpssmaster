package com.kadir.kpssmaster;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class soru_bankasi_alan_secimi extends AppCompatActivity {

    long egitimbiilimleri_sorusayisi = 551;
    DatabaseReference sayideneme_egitimbilimleri;

    Button genelyetenek;
    Button genelkultur;
    Button egitimbilimleri;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru_bankasi_alan_secimi);

        dialog = new ProgressDialog(this);

        sayideneme_egitimbilimleri = FirebaseDatabase.getInstance().getReference().child("egitimbilimleri");
        sayideneme_egitimbilimleri.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                egitimbiilimleri_sorusayisi = dataSnapshot.getChildrenCount();
                System.out.println("Eğitim Bilimleri soru sayisi: " + egitimbiilimleri_sorusayisi);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        System.out.println("Eğitim Bilimleri soru sayisi: " + egitimbiilimleri_sorusayisi);

        genelyetenek = (Button)findViewById(R.id.genel_yetenek);
        genelkultur = (Button)findViewById(R.id.genel_kultur);
        egitimbilimleri = (Button)findViewById(R.id.egitim_bilimleri);

        genelyetenek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(soru_bankasi_alan_secimi.this, genel_yetenek.class);
                startActivity(i);
                finish();

            }
        });

        genelkultur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(soru_bankasi_alan_secimi.this, genel_kultur.class);
                startActivity(i);
                finish();

            }
        });

        egitimbilimleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("Sorular Hazırlanıyor, Lütfen Bekleyiniz!!!");
                dialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        Intent i = new Intent(soru_bankasi_alan_secimi.this, egitimbilimleri_sorular.class);
                        i.putExtra("dizi", egitimbiilimleri_sorusayisi);
                        startActivity(i);
                        finish();
                    }
                }, 2000);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(soru_bankasi_alan_secimi.this, anasayfa.class);
        finish();
        startActivity(in);
    }

}
