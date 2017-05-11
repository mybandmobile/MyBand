package com.myband.myband.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myband.myband.contract.UserContract;

/**
 * Created by Ranieri Aguiar on 11 de mai.
 */

public class MyBandHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "myBand";

    public MyBandHelper(Context context) { super(context, DB_NAME, null, DB_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUser = "CREATE TABLE " + UserContract.TABLE_NAME + " (" +
                UserContract._ID + " INTEGER PRIMARY KEY, " +
                UserContract.NAME + " TEXT NOT NULL, " +
                UserContract.PASSWORD + " TEXT NOT NULL, " +
                UserContract.LOCATION + " TEXT NOT NULL);";

        db.execSQL(createUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
