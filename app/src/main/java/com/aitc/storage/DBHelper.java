package com.aitc.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by shamar on 5/28/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, Const.DB_NAME, null, Const.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE persons " +
                "(_id integer primary key, name text, age integer, phone text, email text, " +
                "position text, street text, place text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS persons");
        onCreate(db);
    }

    public boolean insertPerson  (String name, int age,
                                  String phone, String email,
                                  String position, String street,
                                  String place) {
        Log.d("DBHELPER", "here");
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Const.DB_COLUMN_NAME, name);
            contentValues.put(Const.DB_COLUMN_AGE, age);
            contentValues.put(Const.DB_COLUMN_PHONE, phone);
            contentValues.put(Const.DB_COLUMN_EMAIL, email);
            contentValues.put(Const.DB_COLUMN_POSITION, position);
            contentValues.put(Const.DB_COLUMN_STREET, street);
            contentValues.put(Const.DB_COLUMN_PLACE, place);

            db.insertOrThrow(Const.TABLE_NAME, null, contentValues);
        }
        catch (Exception e) {
            Log.d("DBHELPER", e.getMessage());
        }

        return true;
    }

    public Cursor getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM persons", null );
        res.moveToFirst();
        return res;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM persons WHERE _id = " + id , null);
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Const.TABLE_NAME);
        return numRows;
    }

    public boolean updatePerson (Integer id, String name, int age, String phone, String email, String position, String street, String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Const.DB_COLUMN_NAME, name);
        contentValues.put(Const.DB_COLUMN_AGE, age);
        contentValues.put(Const.DB_COLUMN_PHONE, phone);
        contentValues.put(Const.DB_COLUMN_EMAIL, email);
        contentValues.put(Const.DB_COLUMN_POSITION, position);
        contentValues.put(Const.DB_COLUMN_STREET, street);
        contentValues.put(Const.DB_COLUMN_PLACE, place);
        db.update(Const.TABLE_NAME, contentValues, "_id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deletePerson (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Const.TABLE_NAME, "_id = ? ", new String[]{Integer.toString(id)});
    }


}
