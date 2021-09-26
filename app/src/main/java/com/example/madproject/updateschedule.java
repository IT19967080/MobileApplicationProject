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
import android.widget.ImageView;
import android.widget.Toast;

public class updateschedule extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5,ed6;
    Button b1,b2,b3;
    ImageView img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateschedule);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//this line hides the action bar
        setContentView(R.layout.activity_updateschedule);

        ed1 = findViewById(R.id.ClassId);
        ed2 = findViewById(R.id.teachername);
        ed3 = findViewById(R.id.classday);
        ed4 = findViewById(R.id.duration);
        ed5 = findViewById(R.id.startingtime);
        ed6 = findViewById(R.id.id);
        img1 = findViewById(R.id.back_button);

        b1 = findViewById(R.id.btnadd);
        b2 = findViewById(R.id.btnview);
        b3 = findViewById(R.id.btnback);

        Intent i = getIntent();
        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("classid").toString();
        String t3 = i.getStringExtra("teachername").toString();
        String t4 = i.getStringExtra("duration").toString();
        String t5 = i.getStringExtra("day").toString();
        String t6 = i.getStringExtra("time").toString();

        ed6.setText(t1);
        ed1.setText(t2);
        ed2.setText(t3);
        ed3.setText(t5);
        ed4.setText(t4);
        ed5.setText(t6);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateschedule.this,view_schedule.class);
                startActivity(intent);
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),view_schedule.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

    }

    public void delete(){
        try{

            String id = ed6.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("management", Context.MODE_PRIVATE,null);

            String sql = "delete from records where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Schedule is Deleted",Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed6.setText("");
            ed1.requestFocus();

        }
        catch(Exception ex){
            Toast.makeText(this,"Schedule is not Deleted",Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }



    public void update(){
        try{
            String classid = ed1.getText().toString();
            String teachername = ed2.getText().toString();
            String day = ed3.getText().toString();
            String duration = ed4.getText().toString();
            String time = ed5.getText().toString();
            String id = ed6.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("management", Context.MODE_PRIVATE,null);

            String sql = "update records set classid = ?,teachername = ?, day= ? , duration = ? , time = ? where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,classid);
            statement.bindString(2,teachername);
            statement.bindString(3,day);
            statement.bindString(4,duration);
            statement.bindString(5,time);
            statement.bindString(6,id);
            statement.execute();
            Toast.makeText(this,"Schedule is updated",Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed6.setText("");
            ed1.requestFocus();

        }
        catch(Exception ex){
            Toast.makeText(this,"Schedule is not updated",Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

}