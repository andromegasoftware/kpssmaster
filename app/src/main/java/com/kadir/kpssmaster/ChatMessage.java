package com.kadir.kpssmaster;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessage {
    String name;
    String mesaj;


    String saat;




    public ChatMessage()
    {

    }
    public ChatMessage(String name, String mesaj, String saat)
    {
        this.name = name ;
        this.mesaj = mesaj;
        this.saat = saat;

    }

    public String getName() {
        return name;
    }

    public String getMesaj() {
        return mesaj;
    }

    public String getSaat() {
        return saat;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public void setName(String name) { this.name = name; }

    public void setSaat(String saat) {this.saat = saat;}


}
