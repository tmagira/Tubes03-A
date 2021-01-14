package com.example.tubes03_a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    // static variable
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "History Pencarian";

    // table name
    private static final String TABLE_HISTORY = "history";

    // column tables
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_TYPE = "type";
    private static final int KEY_OCCURREDAT = 0;
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_LINKIMAGE = "link";
    private static final String KEY_DESC = "desc";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DataBaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_HISTORY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_TYPE + " TEXT, " + KEY_OCCURREDAT + "INTEGER PRIMARY KEY," + KEY_ADDRESS + " TEXT," + KEY_LINKIMAGE + "TEXT," + KEY_DESC + "TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // on Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(db);
    }

    public void addRecord(BikeReport report) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, report.getTitle());
        values.put(KEY_TYPE, report.getType());
        values.put(String.valueOf(KEY_OCCURREDAT), report.getOccurredAt());
        values.put(KEY_ADDRESS, report.getAddress());
        values.put(KEY_LINKIMAGE, report.getLink());
        values.put(KEY_DESC, report.getDesc());
        db.insert(TABLE_HISTORY, null, values);
        db.close();
    }

    public BikeReport getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HISTORY, new String[]{KEY_ID,
                        KEY_TITLE, KEY_TYPE, String.valueOf(KEY_OCCURREDAT), KEY_ADDRESS, KEY_LINKIMAGE, KEY_DESC}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        BikeReport contact = new BikeReport(cursor.getString(0),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        // return contact
        return contact;
    }

    public List<BikeReport> getFilteredRecord(String tag) {
        List<BikeReport> contactList = new ArrayList<BikeReport>();

        String selectQuery = "SELECT  * FROM " + TABLE_HISTORY + " WHERE "+ KEY_TITLE +" LIKE '%" + tag + "%'";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BikeReport report = new BikeReport();
                //report.setId(Integer.parseInt(cursor.getString(0)));
                report.setTitle(cursor.getString(1));
                report.setType(cursor.getString(2));
                report.setOccurredAt(cursor.getInt(3));
                report.setAddress(cursor.getString(4));
                report.setLink(cursor.getString(5));
                report.setDesc(cursor.getString(6));
                contactList.add(report);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // get All Record
    public List<BikeReport> getAllRecord() {
        List<BikeReport> contactList = new ArrayList<BikeReport>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HISTORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BikeReport report = new BikeReport();
                //report.setId(Integer.parseInt(cursor.getString(0)));
                report.setTitle(cursor.getString(1));
                report.setType(cursor.getString(2));
                report.setOccurredAt(cursor.getInt(3));
                report.setAddress(cursor.getString(4));
                report.setLink(cursor.getString(5));
                report.setDesc(cursor.getString(6));
                contactList.add(report);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    //update record
    public int updateContact(BikeReport contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, contact.getTitle());
        values.put(KEY_TYPE, contact.getType());
        values.put(String.valueOf(KEY_OCCURREDAT),contact.getOccurredAt());
        values.put(KEY_ADDRESS,contact.getAddress());
        values.put(KEY_LINKIMAGE,contact.getLink());
        values.put(KEY_DESC,contact.getDesc());
        // updating row
        return db.update(TABLE_HISTORY, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getTitle())});
    }

    public void deleteRecord(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_HISTORY, KEY_ID + " = " + id, null);
    }

}
