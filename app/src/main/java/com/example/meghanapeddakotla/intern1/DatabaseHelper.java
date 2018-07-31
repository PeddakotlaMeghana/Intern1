package com.example.meghanapeddakotla.intern1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by meghana.peddakotla on 7/30/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Employee.db";
    public static final String TABLE_NAME = "Employee_Table";
    public static final String COL_2 = "FName";
    public static final String COL_3 = "LName";
    public static final String COL_4 = "email";
    public static final String COL_5 = "password";

    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="create table " + TABLE_NAME + "( FName TEXT, " +
                "LName TEXT, email TEXT, password TEXT);";
        sqLiteDatabase.execSQL(query);
        this.sqLiteDatabase = sqLiteDatabase;
    }
    public void insert(contact c)
    {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_2, c.getFName());
        values.put(COL_3, ""+c.getLName());
        values.put(COL_4, c.getMail());
        values.put(COL_5, c.getPassword());
        long count=sqLiteDatabase.insert(TABLE_NAME, null, values);
        sqLiteDatabase.close();

    }

    public boolean searchpass(String name, String password)
    {
        sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,new String[]{"FName","password"},null,null,null,null,null);
        String a, b;
        boolean exist;
        int size=cursor.getCount();
        exist = false;

        if (cursor.moveToFirst())
        {
            do {
                a = cursor.getString(cursor.getColumnIndex("FName"));
                b = cursor.getString(cursor.getColumnIndex("password"));
                if (a.equals(name) && b.equals(password)){
                    exist = true;
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return exist;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        String action="DROP TABLE IF EXISTS"+ TABLE_NAME;
        sqLiteDatabase.execSQL(action);
        onCreate(sqLiteDatabase);
    }

}
