package com.imrannazir.demo_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "form_db";
    private static final String TABLE_NAME = "ap_Rec";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE_NUMBER = "phone";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FORM_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_NAME + " TEXT," + KEY_EMAIL + " TEXT, " +KEY_PHONE_NUMBER+" Text"+")";
        System.out.println("Create Table "+ CREATE_FORM_TABLE);
        db.execSQL(CREATE_FORM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addPersonData(PersonData personData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, personData.getName());
        values.put(KEY_EMAIL, personData.getEmail());
        values.put(KEY_PHONE_NUMBER, personData.getPhone());
        System.out.println("Value ----> "+ values);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public PersonData getPersonData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_NAME, KEY_EMAIL, KEY_PHONE_NUMBER}, KEY_NAME + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        return new PersonData(cursor.getString(0), cursor.getString(1),cursor.getString(2));
    }

    public List<PersonData> getAllPersonalData() {
        List<PersonData> personDataList = new ArrayList<PersonData>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                PersonData personData = new PersonData();
               // personData.setId(Integer.parseInt(cursor.getString(0)));
                personData.setName(cursor.getString(0));
                personData.setEmail(cursor.getString(1));
                personData.setPhone(cursor.getString(2));

                personDataList.add(personData);
            } while (cursor.moveToNext());
        }
        return personDataList;
    }

    public int getPersonalDataCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public int updatePersonal(PersonData personData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, personData.getName());
        values.put(KEY_EMAIL, personData.getEmail());
        values.put(KEY_PHONE_NUMBER,personData.getPhone());
        return db.update(TABLE_NAME, values, KEY_NAME+ " = ?", new String[]{personData.getName()});
    }


    public void deletePersonalData(PersonData personData) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_NAME + " = ?", new String[] { personData.getName() });
        db.close();
    }
}