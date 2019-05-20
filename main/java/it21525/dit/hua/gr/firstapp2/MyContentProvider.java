package it21525.dit.hua.gr.firstapp2;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static android.provider.VoicemailContract.Status.CONTENT_URI;

public class MyContentProvider extends android.content.ContentProvider {
    private DBHelper dbHelper;
    private UriMatcher uriMatcher;
    //static final String URL = "content://it21525.dit.hua.gr.firstapp2/personal_info/insert";
    //static final Uri CONTENT_URI = Uri.parse(URL);

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("it21525.dit.hua.gr.firstapp2", "personal_info",1);
        uriMatcher.addURI("it21525.dit.hua.gr.firstapp2", "personal_info/*",2);
        uriMatcher.addURI("it21525.dit.hua.gr.firstapp2", "personal_info/insert",3);
        return false;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        switch(uriMatcher.match(uri)){
            case 1:
                cursor = dbHelper.getReadableDatabase().query(dbHelper.DB_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
                break;
            case 2:
                //https://stackoverflow.com/questions/4050087/how-to-obtain-the-last-path-segment-of-an-uri
                String[] segments = uri.getPath().split("/");
                String id = segments[segments.length-1];
                cursor = dbHelper.getReadableDatabase().query(dbHelper.DB_NAME,
                        null,
                        dbHelper.KEY_USER_ID+"=?",
                         new String[]{id},
                        null,
                        null,
                        null);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long row = 0;
        /*
        if(uri.equals("content://it21525.dit.hua.gr.firstapp2/personal_info/insert")) {
            ContactsContract contactsContract = new ContactsContract();
            values.put(DBHelper.KEY_DT, contactsContract.getDt());
            row = sqLiteDatabase.insert(dbHelper.DB_NAME,null, values);

        }
        */
        switch(uriMatcher.match(uri)){
            case 3:
                ContactsContract contactsContract = new ContactsContract();
                values.put(DBHelper.KEY_DT, contactsContract.getDt());
                row = sqLiteDatabase.insert(dbHelper.DB_NAME,null, values);
                Log.e(TAG, "Row has been inserted");
            break;
        }
        if (row > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(_uri, null);
            Log.e(TAG, "Row has been inserted");
            return _uri;
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
