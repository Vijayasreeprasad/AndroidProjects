package com.microsoft.projectoxford.emotionsample.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.microsoft.projectoxford.emotionsample.Riddle;

import java.util.UUID;

/**
 * Created by Viji on 5/11/2017.
 */

public class RiddleCursorWrapper extends CursorWrapper {
    public RiddleCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Riddle getCrime() {
        String uuidString = getString(getColumnIndex(RiddleDbSchema.RiddleTable.Cols.UUID));
        String title = getString(getColumnIndex(RiddleDbSchema.RiddleTable.Cols.TITLE));
        String field=getString(getColumnIndex(RiddleDbSchema.RiddleTable.Cols.FIELD));
      //  long date = getLong(getColumnIndex(RiddleDbSchema.RiddleTable.Cols.FIELD));
        int isSolved = getInt(getColumnIndex(RiddleDbSchema.RiddleTable.Cols.SOLVED));

        Riddle riddle = new Riddle(UUID.fromString(uuidString));
        riddle.setTitle(title);
        riddle.setField(field);
        riddle.setUsed(isSolved != 0);

        return riddle;


    }

}