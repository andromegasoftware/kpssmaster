package com.kadir.kpssmaster;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class bildirim_sayfasi extends AppCompatActivity {

    String mesaj = "Yeni Mesaj Yok";
    TextView Message;

    SharedPreferences preferences;

    DatabaseReference database_bildirim;
    DatabaseReference database_bildirim_resim;

    ImageView resim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bildirim_sayfasi);

        resim = (ImageView)findViewById(R.id.bildirim_image);



        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Message = (TextView) findViewById(R.id.bildirim);
        mesaj = preferences.getString("notification", "Yeni Mesajınız Yok");
        Message.setMovementMethod(new ScrollingMovementMethod());
        //Message.setText(mesaj);

        database_bildirim_resim = FirebaseDatabase.getInstance().getReference().child("bildirim").child("resim");
        database_bildirim = FirebaseDatabase.getInstance().getReference().child("bildirim").child("not");


        database_bildirim.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String bildirim_not = dataSnapshot.getValue().toString();

                Message.setText(bildirim_not);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database_bildirim_resim.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String bildirim_resim = dataSnapshot.getValue().toString();

                Picasso.with(bildirim_sayfasi.this).load(bildirim_resim).into(resim);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
