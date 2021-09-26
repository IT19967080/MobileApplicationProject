package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class view_schedule extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//this line hides the action bar
        setContentView(R.layout.activity_view_schedule);

        SQLiteDatabase db = openOrCreateDatabase("management", Context.MODE_PRIVATE,null);
        lst1 = findViewById(R.id.lst1);

        final Cursor c = db.rawQuery("select * from records",null);
        int id = c.getColumnIndex("id");
        int classid = c.getColumnIndex("classid");
        int teachername = c.getColumnIndex("teachername");
        int day = c.getColumnIndex("day");
        int duration = c.getColumnIndex("duration");
        int time = c.getColumnIndex("time");
        titles.clear();

        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final  ArrayList<classmanage> clsd = new ArrayList<classmanage>();

        if(c.moveToFirst())
        {
            do{
                classmanage cls = new classmanage();
                cls.id = c.getString(id);
                cls.classid = c.getString(classid);
                cls.teachername = c.getString(teachername);
                cls.duration = c.getString(duration);
                cls.day = c.getString(day);
                cls.time = c.getString(time);

                clsd.add(cls);

                titles.add(c.getString(classid)+" \t"+c.getString(teachername)+" \t"+c.getString(day)+" \t"+c.getString(duration)+" \t"+c.getString(time));

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();

                classmanage cls = clsd.get(position);
                Intent i = new Intent(getApplicationContext(),updateschedule.class);

                i.putExtra("id",cls.id);
                i.putExtra("classid",cls.classid);
                i.putExtra("teachername",cls.teachername);
                i.putExtra("duration",cls.duration);
                i.putExtra("day",cls.day);
                i.putExtra("time",cls.time);
                startActivity(i);

            }
        });


    }
}