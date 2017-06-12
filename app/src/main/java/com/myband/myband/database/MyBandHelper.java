package com.myband.myband.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myband.myband.contract.EventContract;
import com.myband.myband.contract.UserContract;
import com.myband.myband.model.Event;

/**
 * Created by Ranieri Aguiar on 11 de mai.
 */

public class MyBandHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "myBand";

    public MyBandHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableUser = "CREATE TABLE " + UserContract.TABLE_NAME + " (" +
                UserContract.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                UserContract.COLUMN_NAME + " TEXT NOT NULL, " +
                UserContract.COLUMN_PASSWORD + " TEXT NOT NULL, " +
                UserContract.COLUMN_LOGIN + " TEXT NOT NULL, " +
                UserContract.COLUMN_CATEGORY + " INTEGER NOT NULL, " +
                UserContract.COLUMN_LATITUDE + " REAL NOT NULL, " +
                UserContract.COLUMN_LONGITUDE + " REAL NOT NULL, " +
                UserContract.COLUMN_AUTOLOGIN + " INTEGER DEFAULT 0);";

        db.execSQL(createTableUser);

        String createTableEvent = "CREATE TABLE " + EventContract.TABLE_NAME + " (" +
                EventContract.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                EventContract.COLUMN_REQUESTER + " TEXT NOT NULL, " +
                EventContract.COLUMN_PROVIDER + " TEXT NOT NULL, " +
                EventContract.SUBGENRE + " TEXT NOT NULL, " +
                EventContract.INITIALDATE + " INTEGER NOT NULL, " +
                EventContract.FINALDATE + " REAL NOT NULL);";

        db.execSQL(createTableEvent);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
