package com.apackage.yvyor.androidfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yvyor on 3/10/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "scorescreen.db";
    public static final String TABLE_NAME = "score_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "SCORE";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlBase) {
        sqlBase.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,SCORE INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlBase, int i, int i1) {
        sqlBase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqlBase);
    }

    public boolean insertData(String name,int score) {
        SQLiteDatabase sqlBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,score);
        long result = sqlBase.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase sqlBase = this.getWritableDatabase();
        Cursor cur_res = sqlBase.rawQuery("select * from "+TABLE_NAME,null);
        return cur_res;
    }
}
