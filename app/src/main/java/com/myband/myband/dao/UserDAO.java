package com.myband.myband.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myband.myband.contract.UserContract;
import com.myband.myband.database.MyBandHelper;
import com.myband.myband.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ranieri Aguiar on 11 de mai.
 */

public class UserDAO {
    private Context mContext;
    private User user;

    public UserDAO(Context mContext) {
        this.mContext = mContext;
    }

    public User insertUser(User user) {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = valuesFromUser(user);
        long id = db.insert(UserContract.TABLE_NAME, null, values);
        user.setId(id);

        db.close();
        return user;
    }

    public int updateUser(User user) {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = valuesFromUser(user);
        int rowsAffected = db.update(UserContract.TABLE_NAME, values, UserContract._ID + " = ?", new String[]{String.valueOf(user.getId())});

        db.close();
        return rowsAffected;
    }

    public int deleteUser(User user) {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
        int rowsAffected = db.delete(UserContract.TABLE_NAME, UserContract._ID + " = ?", new String[]{String.valueOf(user.getId())});
        db.close();
        return rowsAffected;
    }

    public List<User> listUser() {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.TABLE_NAME, null);

        List<User> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            user = new User();

            user.setId(cursor.getLong(cursor.getColumnIndex(UserContract._ID)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(UserContract.PASSWORD)));


            list.add(user);
        }

        cursor.close();
        db.close();
        return list;
    }

    private ContentValues valuesFromUser(User user) {
        ContentValues values = new ContentValues();
        values.put(UserContract._ID, user.getId());

        values.put(UserContract.PASSWORD, user.getPassword());

        return values;
    }
}

