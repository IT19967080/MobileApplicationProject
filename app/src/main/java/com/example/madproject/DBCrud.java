package com.example.madproject;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBCrud extends SQLiteOpenHelper {
    public DBCrud(Context context) {
        super(context, "Teacher.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Teacherdetails(fullname TEXT primary key,contact TEXT,subject TEXT,nationalid TEXT,address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Teacherdetails");
    }

    public Boolean insertteacherdata(String fullname, String contact, String subject, String nationalid, String address) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", fullname);
        contentValues.put("contact", contact);
        contentValues.put("subject", subject);
        contentValues.put("nationalid", nationalid);
        contentValues.put("address", address);

        long result = DB.insert("Teacherdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateteacherdata(String fullname, String contact, String subject, String nationalid, String address) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("contact", contact);
        contentValues.put("subject", subject);
        contentValues.put("nationalid", nationalid);
        contentValues.put("address", address);

        Cursor cursor = DB.rawQuery("Select * from Teacherdetails where fullname=?", new String[]{fullname});
        if (cursor.getCount() > 0) {
            long result = DB.update("Teacherdetails", contentValues, "fullname=?", new String[]{fullname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
            }else
            {
                return false;
            }
        }


    public Boolean deleteteacherdata(String fullname) {
        SQLiteDatabase DB = this.getWritableDatabase();


        Cursor cursor = DB.rawQuery("Select * from Teacherdetails where fullname=?", new String[]{fullname});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Teacherdetails", "fullname=?", new String[]{fullname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else
        {
            return false;
        }
    }


    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();


        Cursor cursor = DB.rawQuery("Select * from Teacherdetails",null );
        return cursor;
    }






    }
