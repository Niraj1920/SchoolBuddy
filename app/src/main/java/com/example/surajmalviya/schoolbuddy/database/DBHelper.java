package com.example.surajmalviya.schoolbuddy.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Suraj Malviya on 02/06/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="school.db";
    public static final int DB_VERSION=1;

    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME , null, DB_VERSION);
        Log.d("in DBHelper","");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("in onCreate","");
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        dropTables(db);
        onCreate(db);
    }

    private void createTables(SQLiteDatabase db)
    {
        db.execSQL(DataSchema.StudentTable.CREATE_TABLE);
        db.execSQL(DataSchema.SubjectPrefereceTable.CREATE_TABLE);
        db.execSQL(DataSchema.ScoreTable.CREATE_TABLE);
    }

    private void dropTables(SQLiteDatabase db)
    {
        db.execSQL(DataSchema.StudentTable.DROP_TABLE);
        db.execSQL(DataSchema.ScoreTable.DROP_TABLE);
    }

}
