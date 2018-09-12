package com.example.fredliu.vtparking.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.fredliu.vtparking.Me.HistoryDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fredliu on 12/8/17.
 */

public class DatabaseManager {

    private SQLiteOpenHelper dbOpenHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    public void open() {
        database = dbOpenHelper.getWritableDatabase();
    }

    public void close() {
        dbOpenHelper.close();
    }

    public void insertParkingInfo(String content, String time) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COLUMN_NAME_CONTENT, content);
        values.put(DBOpenHelper.COLUMN_NAME_TIME, time);




        database.insert(DBOpenHelper.TABLE_NAME, null, values);
    }

    public List<HistoryDetail> getAllRecordsOrderedBy(String key) {
        Cursor cursor = database.query(DBOpenHelper.TABLE_NAME,
                new String[]{
                        DBOpenHelper.COLUMN_ID,
                        DBOpenHelper.COLUMN_NAME_CONTENT,
                        DBOpenHelper.COLUMN_NAME_TIME,
                }, null, null, null, null, key , null);
        cursor.moveToFirst();
        HistoryDetail historyDetail;
        List<HistoryDetail> result =new ArrayList<HistoryDetail>();
        while (!cursor.isAfterLast()) {
//            parkingSpot = new parkingSpot();
//            parkingSpot.setTitle(
//                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_TITLE)));
//            parkingSpot.setDate(
//                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_RELEASE_DATE)));
//            cursor.moveToNext();
//            result.add(parkingSpot);
        }
        return result;

        //return the list ordered by the key
        //The key should be one of the column keys defined in DBOpenHelper.
    }

    public List<HistoryDetail> getAllRecords() {
        Cursor cursor = database.query(DBOpenHelper.TABLE_NAME,
                new String[]{
                        DBOpenHelper.COLUMN_ID,
                        DBOpenHelper.COLUMN_NAME_CONTENT,
                        DBOpenHelper.COLUMN_NAME_TIME
                }, null, null, null, null, null, null);
        cursor.moveToFirst();
        HistoryDetail historyDetail;
        List<HistoryDetail> result =new ArrayList<HistoryDetail>();
        while (!cursor.isAfterLast()) {
           //historyDetail = new HistoryDetail();
//            parkingSpot.setTitle(
//                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_TITLE)));
//            parkingSpot.setDate(
//                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_RELEASE_DATE)));
            cursor.moveToNext();
            //result.add(historyDetail);
        }
        return result;
    }

    public void deleteAll() {
        if (database.isOpen()) {
            database.execSQL("DELETE FROM " + DBOpenHelper.TABLE_NAME);
        }
    }
}
