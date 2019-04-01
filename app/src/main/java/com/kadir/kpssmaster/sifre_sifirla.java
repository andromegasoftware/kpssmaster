package com.kadir.kpssmaster;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class sifre_sifirla extends AppCompatActivity {

    TextView email;
    Button resetpassword;
    FirebaseAuth Auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre_sifirla);

        dialog = new ProgressDialog(this);
        email = (TextView)findViewById(R.id.editText);
        resetpassword = (Button)findViewById(R.id.button2);
        Auth = FirebaseAuth.getInstance();

        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String user_email = email.getText().toString().trim();

               if(user_email.equals(""))
               {
                   Toast.makeText(sifre_sifirla.this, "Lütfen Sistemde Kayıtlı E-Maili Giriniz.",
                           Toast.LENGTH_LONG).show();
               }
               else
               {
                   dialog.setMessage("İşleminiz Yapılıyor, Lütfen Bekleyiniz!!!");
                   dialog.show();
                   Auth.sendPasswordResetEmail(user_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful())
                           {
                               dialog.dismiss();
                               Toast.makeText(sifre_sifirla.this, "E-Mail Adresinize Şifrenizi Sıfırlamak İçin Link Gçnderildi.",
                                       Toast.LENGTH_LONG).show();
                               finish();
                               startActivity(new Intent(sifre_sifirla.this, mail_giris.class));
                           }
                           else
                           {
                               dialog.dismiss();
                               Toast.makeText(sifre_sifirla.this, "Şİfre Sıfırlamada Bir Hata Oluştu. Lütfen Tekrar Deneyiniz.",
                                       Toast.LENGTH_LONG).show();
                           }
                       }
                   });
               }

            }
        });
    }
}
