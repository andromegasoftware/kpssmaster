package com.kadir.kpssmaster;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class mail_giris extends AppCompatActivity {


    private Button girisyap;
    private EditText isim;
    ShredPref shredPref;
    Context context = this;
    String name;
    ProgressDialog dialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_giris);

        dialog = new ProgressDialog(this);

        shredPref = new ShredPref();

        girisyap = (Button)findViewById(R.id.girisyap);
        isim = (EditText)findViewById(R.id.isim);


        //****************ismi hatırlama**********************
        isim.setText(shredPref.getValue(context, "user"));


        girisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = isim.getText().toString();
                if(!name.equals("")) {
                    dialog.setMessage("Giriş Yapılıyor, Lütfen Bekleyiniz!!!");
                    shredPref.save(context, "user", isim.getText().toString());
                    Intent i = new Intent(mail_giris.this, anasayfa.class);
                    startActivity(i);
                    dialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            finish();
                        }
                    },2000);

                }
                else
                {
                    Toast.makeText(mail_giris.this, "Lütfen İsminizi Yazınız",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}
