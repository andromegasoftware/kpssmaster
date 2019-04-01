package com.kadir.kpssmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class acilis extends AppCompatActivity {

    String mesaj;
    SharedPreferences preferences;

    private static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acilis);


        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        bildirimal();
        TextView textView = findViewById(R.id.textView);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        textView.startAnimation(animation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(acilis.this, mail_giris.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
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
