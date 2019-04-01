package com.kadir.kpssmaster;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class uye_ol extends AppCompatActivity {

    private FirebaseAuth Auth;
    private EditText email;
    private EditText password;
    private DatabaseReference mDatabase;
    Button uyeol;
    ProgressDialog dialog;
    TextView isim;
    Button girise_don;
    String name;

    CheckBox checkBox;
    TextView sozlesme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_ol);

        dialog = new ProgressDialog(this);

        Auth = FirebaseAuth.getInstance();
        email = (EditText)findViewById(R.id.isim);
        password = (EditText)findViewById(R.id.sifre);
        uyeol = (Button)findViewById(R.id.tamam);
        isim = (TextView)findViewById(R.id.isim);

        checkBox = (CheckBox) findViewById(R.id.checkBox_sozlesme);
        sozlesme = (TextView) findViewById(R.id.sozlesme);
        sozlesme.setMovementMethod(LinkMovementMethod.getInstance());

        //Check if user is already LoggedIn


        uyeol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String getemail = email.getText().toString().trim();
                    String getpassword = password.getText().toString().trim();
                    name = isim.getText().toString().trim();

                    if(!getemail.isEmpty() && !getpassword.isEmpty() && !name.isEmpty())
                    {
                        if(checkBox.isChecked())
                        {
                            callsignup(getemail, getpassword);
                        }
                        else
                        {Toast.makeText(uye_ol.this, "Lütfen Kullanıcı Sözleşmesini Onaylayın.",
                                Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {Toast.makeText(uye_ol.this, "İsim, E-Mail veya Şifre Boş Geçilemez!",
                            Toast.LENGTH_LONG).show();
                    }


                }catch (Exception e)
                {
                    Toast.makeText(uye_ol.this, "E-Mail veya Şifre Boş Geçilemez!",
                            Toast.LENGTH_LONG).show();

                }



            }
        });

        girise_don = (Button)findViewById(R.id.giris_don);
        girise_don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //create account
    private void callsignup(String email, String password){
        dialog.setMessage("İşlem Yapılıyor, Lütfen Bekleyiniz!!!");
        dialog.show();
        Auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        dialog.show();
                        if (task.isSuccessful())
                        {
                            dialog.dismiss();
                            userprofile();
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Testing", "createUserWithEmail:success");
                            Toast.makeText(uye_ol.this, "Kaydınız Başarıyla Gerçekleştirildi.",
                                    Toast.LENGTH_LONG).show();
                            finish();


                        }
                        else
                            {
                                dialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Log.w("Testing", "createUserWithEmail:failure", task.getException());
                            String mesage = task.getException().getMessage();
                            System.out.println("cıktı: " + mesage);
                            if(mesage.equals("The email address is already in use by another account."))
                            {
                                Toast.makeText(uye_ol.this, "Bu E-Mail Adresi Zaten Sistemde Kayıtlı.",
                                        Toast.LENGTH_LONG).show();
                            }
                            else if(mesage.equals("The email address is badly formatted."))
                            {
                                Toast.makeText(uye_ol.this, "Lütfen Geçerli Bir E-Mail Adresi Giriniz.",
                                        Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(uye_ol.this, "Şifre En Az 6 Karakter Olmalı.",
                                        Toast.LENGTH_LONG).show();
                            }

                        }

                        // ...
                    }
                });
    }

    //set user display name
    private void userprofile(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
        {
            // Name, email address, and profile photo Url
            String name_tekrar = isim.getText().toString().trim();
            String email = user.getEmail();
            String uid = user.getUid();
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
            HashMap<String, String> userMap = new HashMap<>();
            userMap.put("isim", name_tekrar);
            userMap.put("email", email);
            userMap.put("status", "Merhaba, Ben de KPSSMASTER kullanıyorum.");
            userMap.put("image", "default");
            userMap.put("thumb_image", "default");

            mDatabase.setValue(userMap);
            //Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.

        }
    }

}
