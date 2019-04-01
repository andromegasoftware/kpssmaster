package com.kadir.kpssmaster;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class forum extends AppCompatActivity {

    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;

    Button gonder;
    EditText mesaj;
    ListView messagesList;

    ShredPref shredPref;
    Context context = this;
    String name;
    private AdView mAdView;

    SharedPreferences preferences;
    Boolean reklam_goster = true;

    SimpleDateFormat sdf;
    String saat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");

        /*
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = mCurrentUser.getUid();

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);
        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    name = dataSnapshot.child("isim").getValue().toString();

                }catch (Exception e){}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        //**************isimi shered preference dan çekme***********
        shredPref = new ShredPref();
        name = shredPref.getValue(context, "user");

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        reklam_goster = preferences.getBoolean("goster", true);

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

        gonder = (Button)findViewById(R.id.gonder);
        mesaj = (EditText) findViewById(R.id.mesaj);
        messagesList = (ListView)findViewById(R.id.list);

        final DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("forum");
        database.limitToLast(50);



        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saat = sdf.format(new Date());
                ChatMessage chat = new ChatMessage(name, mesaj.getText().toString(), saat);
                database.push().setValue(chat);
                mesaj.setText("");
            }
        });


        final List<ChatMessage> messages = new LinkedList<>();


        final ArrayAdapter<ChatMessage> adapter = new ArrayAdapter<ChatMessage>(this, android.R.layout.two_line_list_item, messages)
        {
            @NonNull
            @Override
            public View getView(int position, View view, ViewGroup parent) {
                if(view == null)
                {
                    view = getLayoutInflater().inflate(android.R.layout.two_line_list_item, parent, false);
                }
                ChatMessage chat = messages.get(position);
                ((TextView)view.findViewById(android.R.id.text1)).setText(chat.getName() + " " + chat.getSaat());
                ((TextView)view.findViewById(android.R.id.text2)).setText(chat.getMesaj());
                return  view;
            }
        };
        messagesList.setAdapter(adapter);
        //messagesList.setSelection(messagesList.getCount()-1);

        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String isim = dataSnapshot.child("name").getValue(String.class);
                String mesaj = dataSnapshot.child("mesaj").getValue(String.class);
                String saat = dataSnapshot.child("saat").getValue(String.class);
                ChatMessage chat = new ChatMessage();
                chat.setMesaj(mesaj);
                chat.setName(isim);
                chat.setSaat(saat);
                messages.add(chat);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        aletDialog();
    }

    public void aletDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(forum.this);
        alert.setTitle("Çıkış");
        alert.setMessage("Bu Bölümden Çıkmak İstiyormusunuz?");
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

                Intent in = new Intent(forum.this, anasayfa.class);
                finish();
                startActivity(in);
            }
        });

        AlertDialog al = alert.create();
        al.show();
    }
}
