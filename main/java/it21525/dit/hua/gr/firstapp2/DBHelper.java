package it21525.dit.hua.gr.firstapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "PERSONAL_INFO";
    public static final int DB_VERSION = 1;
    public static final String KEY_ID = "_ID";
    public static final String KEY_USER_ID = "_USER_ID";
    public static final String KEY_LON = "_LON";
    public static final String KEY_LAN = "_LAN";
    public static final String KEY_DT = "_DT";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DB_NAME
                + " (" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_USER_ID + " TEXT NOT NULL,"
                + KEY_LON + " REAL NOT NULL,"
                + KEY_LAN + " REAL NOT NULL,"
                + KEY_DT + " TEXT NOT NULL"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //insert object ContactsContract in db
    public long insert(ContactsContract contactsContract){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, contactsContract.getUser_id());
        values.put(KEY_LON, contactsContract.getLon());
        values.put(KEY_LAN, contactsContract.getLan());
        values.put(KEY_DT, contactsContract.getDt());
        return sqLiteDatabase.insert(DB_NAME,null, values);
    }

    //add the timestamps in a list and load it in the spinner of the second activity
    public ArrayList<String> loadDts(){
        ArrayList<String> dtList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //select column dt from all rows in db
        Cursor cursor = sqLiteDatabase.query(DB_NAME, new String[]{KEY_DT},null, null, null,null,null);
        if(cursor.moveToFirst()){
            do{
                dtList.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        return dtList;
    }

    //get the query/queries which correspond to the given user id and/or timestamp and add them to a list
    public ArrayList<ContactsContract> getQuery(String userId, String dt){
        ArrayList<ContactsContract> contactsContractArrayList = new ArrayList<>();
        int id ;
        String user_id ;
        double lon;
        double lan;
        String timestamp;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //select rows whose user_id and/or dt match the selected ones in the second activity
        Cursor cursor = sqLiteDatabase.query(DB_NAME, null, "_USER_ID=? OR _DT=?", new String[]{userId, dt},null, null, null);

        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(0);
                user_id = cursor.getString(1);
                lon= cursor.getDouble(2);
                lan = cursor.getDouble(3);
                timestamp = cursor.getString(4);
                ContactsContract obj = new ContactsContract(id, user_id, lon, lan, timestamp);
                contactsContractArrayList.add(obj);
            }while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        return contactsContractArrayList;
    }

    public ArrayList<ContactsContract> getAll(){
        ArrayList<ContactsContract> contactsContractArrayList = new ArrayList<>();
        int id ;
        String user_id ;
        double lon;
        double lan;
        String timestamp;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DB_NAME, null, null, null,null, null, null);
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(0);
                user_id = cursor.getString(1);
                lon= cursor.getDouble(2);
                lan = cursor.getDouble(3);
                timestamp = cursor.getString(4);
                ContactsContract obj = new ContactsContract(id, user_id, lon, lan, timestamp);
                contactsContractArrayList.add(obj);
            }while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        return contactsContractArrayList;
    }

}
