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
                UserContract.COLUMN_LOCATION + " TEXT, " +
                UserContract.COLUMN_AUTOLOGIN + " INTEGER NOT NULL);";

        db.execSQL(createTableUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
