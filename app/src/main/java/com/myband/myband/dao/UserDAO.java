package com.myband.myband.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myband.myband.contract.UserContract;
import com.myband.myband.database.MyBandHelper;
import com.myband.myband.model.Category;
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
    private int indexLatitude;
    private int indexLongitude;
    private int indexAutologin;
    private int indexCategory;

    public UserDAO(Context mContext) {
        this.mContext = mContext;
    }

    public User insert(User user, boolean autoLogin) {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (autoLogin) {
            setAutoLoginFalse();
            user.setAutoLogin(true);
        }
        ContentValues values = valuesFromUser(user);
        long id = db.insert(UserContract.TABLE_NAME, null, values);
        user.setId(id);

        db.close();
        return user;
    }

    public int update(User user, boolean autoLogin) {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (autoLogin) {
            setAutoLoginFalse();
            user.setAutoLogin(true);
        }
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

    public User selectAutoLogin() {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM " +
                UserContract.TABLE_NAME +
                " WHERE " +
                UserContract.AUTOLOGIN +
                " = 1;";

        Cursor cursor = db.rawQuery(sql, null);

        User user = null;
        if (cursor.moveToNext()) {
            getColumnIndex(cursor);
            Category category = new Category();
            user = new User();
            user.setId(cursor.getLong(indexID));
            user.setUserName(cursor.getString(indexName));
            user.setLogin(cursor.getString(indexLogin));
            user.setPassword(cursor.getString(indexPassword));
            user.setLatitude(cursor.getDouble(indexLatitude));
            user.setLongitude(cursor.getDouble(indexLongitude));
            user.setAutoLogin((cursor.getInt(indexAutologin)) == 1);

            category.setId(cursor.getLong(indexCategory));
            user.setCategory(category);
        }

        cursor.close();
        db.close();
        return user;
    }

    public int count(User user) {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] id = new String[]{String.valueOf(user.getId())};

        String sql = "SELECT COUNT(*) FROM " +
                UserContract.TABLE_NAME +
                " WHERE " +
                UserContract.ID +
                " = ?;";

        Cursor cursor = db.rawQuery(sql, id);

        int count = 0;
        if (cursor.moveToNext()) {
            count = cursor.getInt(0);
        }

        cursor.close();
        db.close();
        return count;
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

    public void setAutoLoginFalse() {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "UPDATE " +
                UserContract.TABLE_NAME +
                " SET " +
                UserContract.COLUMN_AUTOLOGIN +
                " = 0;";
        db.execSQL(sql);

        db.close();
    }

    private void getColumnIndex(Cursor cursor) {
        indexID = cursor.getColumnIndex(UserContract.COLUMN_ID);
        indexName = cursor.getColumnIndex(UserContract.COLUMN_NAME);
        indexLogin = cursor.getColumnIndex(UserContract.COLUMN_LOGIN);
        indexPassword = cursor.getColumnIndex(UserContract.COLUMN_PASSWORD);
        indexLatitude = cursor.getColumnIndex(UserContract.COLUMN_LATITUDE);
        indexLongitude = cursor.getColumnIndex(UserContract.COLUMN_LONGITUDE);
        indexAutologin = cursor.getColumnIndex(UserContract.COLUMN_AUTOLOGIN);
        indexCategory = cursor.getColumnIndex(UserContract.COLUMN_CATEGORY);
    }

    private List<User> valuesFromCursor(Cursor cursor) {
        List<User> list = new ArrayList<>();
        User user;
        Category category;
        getColumnIndex(cursor);
        while (cursor.moveToNext()) {
            user = new User();
            category = new Category();
            user.setId(cursor.getLong(indexID));
            user.setUserName(cursor.getString(indexName));
            user.setLogin(cursor.getString(indexLogin));
            user.setPassword(cursor.getString(indexPassword));
            user.setLatitude(cursor.getDouble(indexLatitude));
            user.setLongitude(cursor.getDouble(indexLongitude));
            user.setAutoLogin((cursor.getInt(indexAutologin)) == 1);

            category.setId(cursor.getLong(indexCategory));
            user.setCategory(category);
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
        values.put(UserContract.COLUMN_LATITUDE, user.getLatitude());
        values.put(UserContract.COLUMN_LONGITUDE, user.getLongitude());
        values.put(UserContract.COLUMN_AUTOLOGIN, (user.isAutoLogin()) ? 1 : 0);
        values.put(UserContract.COLUMN_CATEGORY, user.getCategory().getId());
        return values;
    }
}

