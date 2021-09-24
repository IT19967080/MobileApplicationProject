package com.example.madproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class databaseHelper_Student extends SQLiteOpenHelper {

    //database name
    public static final String DATABASE_NAME = "NilwalaEducation_student.db";
    //table name
    public static final String TABLE_NAME = "student_table";
    //column names
    public static final String Col_1 = "Student_ID";
    public static final String Col_2 = "First_Name";
    public static final String Col_3 = "Last_Name";
    public static final String Col_4 = "Address";
    public static final String Col_5 = "Gender";
    public static final String Col_6 = "Phone_Number";
    public static final String Col_7 = "Parents_Guardians_Number";


    //database creation method
    public databaseHelper_Student(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    //table creation method
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE student_table ( Student_ID INTEGER PRIMARY KEY AUTOINCREMENT,First_Name TEXT, Last_Name TEXT, Address TEXT, Gender TEXT, Phone_Number NUMBER, Parents_Guardians_Number NUMBER)");

    }

    //table deleting method
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+" ");
        onCreate(db);

    }

    //data insert method
    public boolean insertStudent(String fname, String lname, String address, String gender, String pnumber, String parentPnumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2,fname);
        contentValues.put(Col_3,lname);
        contentValues.put(Col_4,address);
        contentValues.put(Col_5,gender);
        contentValues.put(Col_6,pnumber);
        contentValues.put(Col_7,parentPnumber);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }

    //all data retrieve method
    public Cursor getAllStudentData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME+" ",null);
        return res;
    }
}
