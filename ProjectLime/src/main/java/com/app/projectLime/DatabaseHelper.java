package com.app.projectLime;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by luigi.pozzi on 15/02/2018.
 * Classe per l'interazione
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Database.db";
    public static final String TABLE_NAME = "ConsoleBrand_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";

    //costructor
    public DatabaseHelper(Context context){
        //creates the database
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

     public boolean insertData(String name){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put(COL_2,name);
         long result = db.insert(TABLE_NAME,null,contentValues);

        if(result==-1) return false;
        else return true;

    }
}
