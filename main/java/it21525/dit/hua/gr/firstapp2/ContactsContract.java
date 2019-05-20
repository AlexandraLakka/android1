package it21525.dit.hua.gr.firstapp2;

import android.support.annotation.NonNull;

public class ContactsContract {

    private int id;
    private String user_id;
    private double lon;
    private double lan;
    private String dt;
    //https://stackoverflow.com/questions/8077530/android-get-current-timestamp
    private Long dtLong = System.currentTimeMillis()/1000;


    public ContactsContract(int id, String user_id, double lon, double lan, String dt) {
        this.id = id;
        this.user_id = user_id;
        this.lon = lon;
        this.lan = lan;
        this.dt = dt;
    }

    public ContactsContract(String user_id, double lon, double lan, String dt) {
        this.user_id = user_id;
        this.lon = lon;
        this.lan = lan;
        this.dt = dt;
    }
    public ContactsContract(){

    }

    public int getId() {

        return id;
    }

    public String getUser_id() {

        return user_id;
    }

    public double getLon() {

        return lon;
    }

    public double getLan() {

        return lan;
    }

    public String getDt() {
        dt = dtLong.toString();
        return dt;
    }

    //3rd activity:used to show the objects we return from the database in String form
    @NonNull
    @Override
    public String toString() {
        return "id:" + this.id
                + ", user_id:" + this.user_id
                + ", lon:" + this.lon +","+"\n"
                + "lan" + this.lan
                + ", dt:" + this.dt;
    }
}
