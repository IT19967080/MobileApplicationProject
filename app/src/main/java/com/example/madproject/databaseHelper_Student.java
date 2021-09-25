package com.example.madproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class databaseHelper_Student extends SQLiteOpenHelper {

    //database name
    public static final String DATABASE_NAME = "NilwalaEducation_student.db";
    //table name
    public static final String TABLE_NAME = "student_table";
    //column names
    public static final String Col_1 = "id";
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

        db.execSQL("CREATE TABLE student_table ( id String PRIMARY KEY AUTOINCREMENT,First_Name TEXT, Last_Name TEXT, Address TEXT, Gender TEXT, Phone_Number NUMBER, Parents_Guardians_Number NUMBER)");

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

    //get all students into a list
    public List<StudentModel> getAllStudentModals(){

        List<StudentModel> studentModels= new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor= db.rawQuery("SELECT * FROM "+TABLE_NAME+" ",null);

        if(cursor.moveToFirst()){
            do{
                //create new hall object
                StudentModel studentModel= new StudentModel();

                studentModel.setStudentFname(cursor.getString(0));
                studentModel.setStudentLname(cursor.getString(1));
                studentModel.setStudentAddress(cursor.getString(2));
                studentModel.setStudentGender(cursor.getString(3));
                studentModel.setStudentnumber(cursor.getString(4));
                studentModel.setParentnumber(cursor.getString(5));

                studentModels.add(studentModel);
            }while (cursor.moveToNext());

        }
        return studentModels;

    }
    // get one student details
    public StudentModel getOneStudentDetails(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor=  db.query(TABLE_NAME,new String[]{Col_1,Col_2,Col_3,Col_4,Col_5,Col_6,Col_7},Col_1 + "= ?",new String[]{String.valueOf(id)},null,null,null);

        StudentModel studentModel;
        if(cursor != null){
            cursor.moveToFirst();

            studentModel = new StudentModel(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getLong(7),
                    cursor.getLong(8)


            );
            return studentModel;
        }
        return null;
    }

    //delete student
    public void deleteStudent(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,id+" =?",new String[]{String.valueOf(id)});
        db.close();
    }

    //update student
    public int updateStudent(StudentModel studentmodel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_2,studentmodel.getStudentFname());
        contentValues.put(Col_3,studentmodel.getStudentLname());
        contentValues.put(Col_4, studentmodel.getStudentAddress());
        contentValues.put(Col_5, studentmodel.getStudentGender());
        contentValues.put(Col_6, studentmodel.getStudentnumber());
        contentValues.put(Col_7, studentmodel.getParentnumber());

        int status= db.update(TABLE_NAME,contentValues,Col_1 +" =?",new String[]{ String.valueOf(studentmodel.getStudentId())});

        db.close();
        return status;

    }
}


