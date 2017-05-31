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
    private int indexID;
    private int indexName;
    private int indexLogin;
    private int indexPassword;
    private int indexLocation;
    private int indexAutologin;
    private int indexCategory;

    public UserDAO(Context mContext) {
        this.mContext = mContext;
    }

    public User insert(User user) {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = valuesFromUser(user);
        long id = db.insert(UserContract.TABLE_NAME, null, values);
        user.setId(id);

        db.close();
        return user;
    }

    public int update(User user) {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = valuesFromUser(user);
        int rowsAffected = db.update(UserContract.TABLE_NAME, values, UserContract.COLUMN_ID + " = ?", new String[]{String.valueOf(user.getId())});

        db.close();
        return rowsAffected;
    }

    public int delete(User user) {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
        int rowsAffected = db.delete(UserContract.TABLE_NAME, UserContract.COLUMN_ID + " = ?", new String[]{String.valueOf(user.getId())});
        db.close();
        return rowsAffected;
    }

    public User select(boolean autoLogin) {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] auto = autoLogin ? new String[]{"1"} : new String[]{"0"};

        String sql = "SELECT * FROM " +
                UserContract.TABLE_NAME +
                " WHERE " +
                UserContract.AUTOLOGIN +
                " = ?;";

        Cursor cursor = db.rawQuery(sql, auto);

        User user = null;
        if (cursor.moveToNext()) {
            getColumnIndex(cursor);
            user = new User();
            user.setId(cursor.getLong(indexID));
            user.setUserName(cursor.getString(indexName));
            user.setLogin(cursor.getString(indexLogin));
            user.setPassword(cursor.getString(indexPassword));
            user.setLocation(cursor.getString(indexLocation));
            user.setAutoLogin((cursor.getInt(indexAutologin)) == 1);
            user.getCategory().setId(cursor.getLong(indexCategory));
        }

        cursor.close();
        db.close();
        return user;
    }

    public List<User> list() {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + UserContract.TABLE_NAME, null);

        List<User> list = valuesFromCursor(cursor);
        cursor.close();
        db.close();
        return list;
    }

    private void getColumnIndex(Cursor cursor) {
        indexID = cursor.getColumnIndex(UserContract.COLUMN_ID);
        indexName = cursor.getColumnIndex(UserContract.COLUMN_NAME);
        indexLogin = cursor.getColumnIndex(UserContract.COLUMN_LOGIN);
        indexPassword = cursor.getColumnIndex(UserContract.COLUMN_PASSWORD);
        indexLocation = cursor.getColumnIndex(UserContract.COLUMN_LOCATION);
        indexAutologin = cursor.getColumnIndex(UserContract.COLUMN_AUTOLOGIN);
        indexCategory = cursor.getColumnIndex(UserContract.COLUMN_CATEGORY);
    }

    private List<User> valuesFromCursor(Cursor cursor) {
        List<User> list = new ArrayList<>();
        User user;
        getColumnIndex(cursor);
        while (cursor.moveToNext()) {
            user = new User();
            user.setId(cursor.getLong(indexID));
            user.setUserName(cursor.getString(indexName));
            user.setLogin(cursor.getString(indexLogin));
            user.setPassword(cursor.getString(indexPassword));
            user.setLocation(cursor.getString(indexLocation));
            user.setAutoLogin((cursor.getInt(indexAutologin)) == 1);
            user.getCategory().setId(cursor.getLong(indexCategory));
            list.add(user);
        }
        return list;
    }

    private ContentValues valuesFromUser(User user) {
        ContentValues values = new ContentValues();
        values.put(UserContract.COLUMN_ID, user.getId());
        values.put(UserContract.COLUMN_NAME, user.getUserName());
        values.put(UserContract.COLUMN_LOGIN, user.getLogin());
        values.put(UserContract.COLUMN_PASSWORD, user.getPassword());
        values.put(UserContract.COLUMN_LOCATION, user.getLocation());
        values.put(UserContract.COLUMN_AUTOLOGIN, (user.isAutoLogin()) ? 1 : 0);
        values.put(UserContract.COLUMN_CATEGORY, user.getCategory().getId());
        return values;
    }
}

