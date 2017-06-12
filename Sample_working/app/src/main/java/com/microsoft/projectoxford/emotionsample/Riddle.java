package com.microsoft.projectoxford.emotionsample;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Viji on 5/8/2017.
 */

public class Riddle {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mUsed;
    private String mField;


    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isUsed() {
        return mUsed;
    }

    public void setUsed(boolean used) {
        mUsed = used;
    }

    public Riddle() {
        this(UUID.randomUUID());
    }

    public Riddle(UUID id) {
        mId = id;
       // mDate = new Date();
    }

    public String getField() {
        return mField;
    }

    public void setField(String field) {
        mField = field;
    }
}
