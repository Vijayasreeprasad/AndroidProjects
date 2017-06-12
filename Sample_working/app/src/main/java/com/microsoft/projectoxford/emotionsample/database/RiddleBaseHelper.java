package com.microsoft.projectoxford.emotionsample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.microsoft.projectoxford.emotionsample.database.RiddleDbSchema.RiddleTable;

/**
 * Created by Viji on 5/10/2017.
 */

public class RiddleBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "riddleBase.db";

    public RiddleBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + RiddleTable.NAME  + "(" +
                " _id integer primary key autoincrement, " +
                RiddleTable.Cols.UUID + ", " +
                RiddleTable.Cols.TITLE + ", " +
                RiddleTable.Cols.FIELD + ", " +
                RiddleTable.Cols.SOLVED +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
