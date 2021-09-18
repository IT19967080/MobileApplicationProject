package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ClassManagement extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_management);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//this line hides the action bar
        setContentView(R.layout.activity_class_management);


        ed1 = findViewById(R.id.ClassId);
        ed2 = findViewById(R.id.teachername);
        ed3 = findViewById(R.id.classday);
        ed4 = findViewById(R.id.duration);
        ed5 = findViewById(R.id.startingtime);

        b1 = findViewById(R.id.btnadd);
        b2 = findViewById(R.id.btnview);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),view_schedule.class);
                startActivity(i);
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });



    }
    public void insert(){
        try{
            String classid = ed1.getText().toString();
            String teachername = ed2.getText().toString();
            String day = ed3.getText().toString();
            String duration = ed4.getText().toString();
            String time = ed5.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("management",Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT,classid VARCHAR,teachername VARCHAR,day VARCHAR,duration VARCHAR,time VARCHAR)");

            String sql = "insert into records(classid,teachername,day,duration,time)values(?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,classid);
            statement.bindString(2,teachername);
            statement.bindString(3,day);
            statement.bindString(4,duration);
            statement.bindString(5,time);
            statement.execute();
            Toast.makeText(this,"Record addded",Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed1.requestFocus();

        }
        catch(Exception ex){
            Toast.makeText(this,"Record is notaddded",Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

}