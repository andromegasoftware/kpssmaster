package com.kadir.kpssmaster;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sifre_Degistir extends AppCompatActivity {

    EditText password;
    Button changepassword;
    Button back;
    FirebaseAuth Auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre__degistir);

        Auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);


        password = (EditText) findViewById(R.id.password);
        changepassword = (Button) findViewById(R.id.change_pass);
        back = (Button)findViewById(R.id.geri);

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null)
                {
                    dialog.setMessage("Şifre Değiştiriliyor, Lütfen Bekleyiniz!!!");
                    dialog.show();
                    user.updatePassword(password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(getApplicationContext(), "Şifreniz Başarıyla Değiştirildi", Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                        Auth.signOut();
                                        finish();
                                        Intent i = new Intent(Sifre_Degistir.this, mail_giris.class);
                                        startActivity(i);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Şifreniz En Az 6 Karakter Olmalı!", Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                    }
                                }
                            });

                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
