package com.myband.myband.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.myband.myband.contract.EventContract;
import com.myband.myband.database.MyBandHelper;
import com.myband.myband.model.Event;
import com.myband.myband.model.SubGenre;
import com.myband.myband.model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuporteTécnico on 12/06/2017.
 */

public class EventDAO {

    private Context mContext;
    private int indexId;
    private int indexRequester;
    private int indexProvider;
    private int indexSubGenre;
    private int indexInitialDate;
    private int indexFinalDate;

    public EventDAO(Context mContext) {
        this.mContext = mContext;
    }

    public Event insert(Event event) {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = valuesFromEvent(event);
        db.insert(EventContract.TABLE_NAME, null, values);

        db.close();
        return event;
    }

    public int update(Event event) {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = valuesFromEvent(event);
        int rowsAffected = db.update(EventContract.TABLE_NAME, values, EventContract.COLUMN_ID + " = ?",
                new String[]{String.valueOf(event.getId())});

        db.close();
        return rowsAffected;
    }

    public int delete(Event event) {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
        int rowsAffected = db.delete(EventContract.TABLE_NAME, EventContract.COLUMN_ID + " = ?",
                new String[]{String.valueOf(event.getId())});
        db.close();
        return rowsAffected;
    }


    public List<Event> list() throws ParseException {
        MyBandHelper helper = new MyBandHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + EventContract.TABLE_NAME, null);

        List<Event> list = valuesFromCursor(cursor);
        cursor.close();
        db.close();
        return list;
    }


    private void getColumnIndex(Cursor cursor) {
        indexId = cursor.getColumnIndex(EventContract.COLUMN_ID);
        indexRequester = cursor.getColumnIndex(EventContract.COLUMN_REQUESTER);
        indexProvider = cursor.getColumnIndex(EventContract.COLUMN_PROVIDER);
        indexSubGenre = cursor.getColumnIndex(EventContract.COLUMN_SUBGENRE);
        indexInitialDate = cursor.getColumnIndex(EventContract.COLUMN_INITIALDATE);
        indexFinalDate = cursor.getColumnIndex(EventContract.COLUMN_FINALDATE);

    }

    private List<Event> valuesFromCursor(Cursor cursor) throws ParseException {
        List<Event> list =        new ArrayList<>();
        Event event;
        User requester;
        User provider;
        SubGenre subGenre;
        getColumnIndex(cursor);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dataBegin;
        java.util.Date dataEnd;

        while (cursor.moveToNext()) {
            event =     new Event();
            requester = new User();
            provider =  new User();
            subGenre =  new SubGenre();
            event.setId(cursor.getLong(indexId));

            requester.setId(cursor.getLong(indexRequester));
            event.setRequester(requester);

            provider.setId(cursor.getLong(indexProvider));
            event.setProvider(provider);

            subGenre.setId(cursor.getLong(indexSubGenre));
            event.setSubGenre(subGenre);

            dataBegin = new java.sql.Date(format.parse(String.valueOf(indexInitialDate)).getTime());
            event.setInitialDate(dataBegin);

            dataEnd = new java.sql.Date(format.parse(String.valueOf(indexFinalDate)).getTime());
            event.setFinalDate(dataEnd);

            list.add(event);
        }
        return list;
    }

    private ContentValues valuesFromEvent(Event event) {
        ContentValues values =   new ContentValues();
        values.put(EventContract.COLUMN_ID, event.getId());
        values.put(EventContract.COLUMN_REQUESTER, String.valueOf(event.getRequester()));
        values.put(EventContract.COLUMN_PROVIDER, String.valueOf(event.getProvider()));
        values.put(EventContract.COLUMN_SUBGENRE, String.valueOf(event.getSubGenre()));
        values.put(EventContract.COLUMN_INITIALDATE, String.valueOf(event.getInitialDate()));
        values.put(EventContract.COLUMN_FINALDATE, String.valueOf(event.getFinalDate()));
        return values;
    }
}
