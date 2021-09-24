package com.example.madproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class  DbHandler extends SQLiteOpenHelper {

    private static final int VERSION=1;
    private static final String DB_NAME= "nilwala";
    private static final String TABLE_NAME= "nilwala";

    //column names
    private static final String ID= "id";
    private static final String HALL_ID= "hallId";
    private static final String CAPACITY= "capacity";
    private static final String AC= "ac";
    private static final String HALL_MANAGER= "hallManager";
    private static final String TEACHER= "teacher";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";


    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY ="CREATE TABLE "+TABLE_NAME+" "+
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +HALL_ID+ " TEXT,"
                +CAPACITY+ " TEXT,"
                +AC+ " TEXT,"
                +HALL_MANAGER+ " TEXT,"
                +TEACHER+ " TEXT,"
                +STARTED+ " TEXT,"
                +FINISHED+ " TEXT" +
                ");";
        db.execSQL(TABLE_CREATE_QUERY);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY= "DROP TABLE IF EXISTS "+ TABLE_NAME;
        //Drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);
        // create table again
        onCreate(db);

    }
    //create table 1 row
    public void addHall(HallModel hallModel){
       try {

           SQLiteDatabase sqLiteDatabase = getWritableDatabase();

           ContentValues contentValues = new ContentValues();

           contentValues.put(HALL_ID, hallModel.getHallId());
           contentValues.put(CAPACITY, hallModel.getCapacity());
           contentValues.put(AC, hallModel.getAc());
           contentValues.put(HALL_MANAGER, hallModel.getHallManager());
           contentValues.put(TEACHER, hallModel.getTeacher());
           contentValues.put(STARTED, hallModel.getStarted());
           contentValues.put(FINISHED, hallModel.getFinished());

           //save to table(database)
           sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
           //close database
           sqLiteDatabase.close();
       }
       catch(Exception e){
           e.printStackTrace();
       }
    }


    //calculation
    public int countHall(){
        SQLiteDatabase db= getReadableDatabase();
        String query= "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor= db.rawQuery(query,null);
        return cursor.getCount();
    }

    //get all hall into a list
    public List<HallModel> getAllHallModels(){

        List<HallModel> hallModels= new ArrayList();
        SQLiteDatabase db= getReadableDatabase();
        String query= "SELECT * FROM "+TABLE_NAME;

        Cursor cursor= db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                //create new hall object
                HallModel hallModel= new HallModel();

                hallModel.setId(cursor.getInt(0));
                hallModel.setHallId(cursor.getString(1));
                hallModel.setCapacity(cursor.getString(2));
                hallModel.setAc(cursor.getString(3));
                hallModel.setHallManager(cursor.getString(4));
                hallModel.setTeacher(cursor.getString(5));
                hallModel.setStarted(cursor.getLong(6));
                hallModel.setFinished(cursor.getLong(7));

                hallModels.add(hallModel);
            }while (cursor.moveToNext());

        }
        return hallModels;

    }

    //delete hall
    public void deleteHallModel(int id){
        SQLiteDatabase db= getWritableDatabase();
        db.delete(TABLE_NAME,ID+" =?",new String[]{String.valueOf(id)});
        db.close();
    }

    //get a single hall
    public HallModel getSingleHallModel(int id){
        SQLiteDatabase db= getWritableDatabase();

      Cursor cursor=  db.query(TABLE_NAME,new String[]{ID,HALL_ID,CAPACITY,AC,HALL_MANAGER,TEACHER,STARTED,FINISHED},ID + "= ?",new String[]{String.valueOf(id)},null,null,null);

      HallModel hallModel;
      if(cursor != null){
          cursor.moveToFirst();

        hallModel= new HallModel(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getLong(6),
                cursor.getLong(7)

        );
        return hallModel;
      }
      return null;
    }

    //update single hall
    public int updateSingleHallModel(HallModel hallModel){
        SQLiteDatabase db= getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(HALL_ID, hallModel.getHallId());
        contentValues.put(CAPACITY, hallModel.getCapacity());
        contentValues.put(AC, hallModel.getAc());
        contentValues.put(HALL_MANAGER, hallModel.getHallManager());
        contentValues.put(TEACHER, hallModel.getTeacher());
        contentValues.put(STARTED, hallModel.getStarted());
        contentValues.put(FINISHED, hallModel.getFinished());

        int status= db.update(TABLE_NAME,contentValues,ID +" =?",new String[]{ String.valueOf(hallModel.getId())});

        db.close();
        return status;

    }

}



























