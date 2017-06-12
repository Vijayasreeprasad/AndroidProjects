package com.microsoft.projectoxford.emotionsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.microsoft.projectoxford.emotionsample.database.RiddleBaseHelper;
import com.microsoft.projectoxford.emotionsample.database.RiddleCursorWrapper;
import com.microsoft.projectoxford.emotionsample.database.RiddleDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Viji on 5/8/2017.
 */

public class RiddleLab {

    private static RiddleLab sRiddleLab;
    //private List<Riddle> mRiddles;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static RiddleLab get(Context context) {
        if (sRiddleLab == null) {
            sRiddleLab = new RiddleLab(context);
        }
        return sRiddleLab;
    }


    private RiddleLab(Context context) {

        mContext = context.getApplicationContext();
        mDatabase = new RiddleBaseHelper(mContext)
                .getWritableDatabase();
      //  mRiddles = new ArrayList<>();
      /*  for (int i = 0; i < 100; i++) {
            Riddle crime = new Riddle();
            crime.setTitle("Riddle #" + i);
            crime.setUsed(i % 2 == 0);
            //Every other one
            mRiddles.add(crime);
        } */
    }


    public void addCrime(Riddle r) {
        ContentValues values = getContentValues(r);
        mDatabase.insert(RiddleDbSchema.RiddleTable.NAME, null, values);
    }


    public List<Riddle> getCrimes() {
        List<Riddle> riddles = new ArrayList<>();
        RiddleCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                riddles.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return riddles;
    }


    public Riddle getCrime(UUID id) {
        RiddleCursorWrapper cursor = queryCrimes(
                RiddleDbSchema.RiddleTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }


    public void updateCrime(Riddle riddle) {
        String uuidString = riddle.getId().toString();
        ContentValues values = getContentValues(riddle);
        mDatabase.update(RiddleDbSchema.RiddleTable.NAME, values,
                RiddleDbSchema.RiddleTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }


    private RiddleCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
            Cursor cursor = mDatabase.query(
                    RiddleDbSchema.RiddleTable.NAME,
                    null, // columns - null selects all columns
                    whereClause,
                    whereArgs,
                    null, // groupBy
                    null, // having
                    null  // orderBy
            );
        return new RiddleCursorWrapper(cursor);
    }


    private static ContentValues getContentValues(Riddle riddle) {
        ContentValues values = new ContentValues();
        values.put(RiddleDbSchema.RiddleTable.Cols.UUID, riddle.getId().toString());
        values.put(RiddleDbSchema.RiddleTable.Cols.TITLE, riddle.getTitle());
        values.put(RiddleDbSchema.RiddleTable.Cols.FIELD, riddle.getField());
        values.put(RiddleDbSchema.RiddleTable.Cols.SOLVED, riddle.isUsed() ? 1 : 0);
        return values;
    }
}

