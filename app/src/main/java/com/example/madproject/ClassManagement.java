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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.common.collect.Range;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class ClassManagement extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5;
    Button b1,b2;
    ImageView img1;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_management);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//this line hides the action bar
        setContentView(R.layout.activity_class_management);
        img1 = findViewById(R.id.back_button);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassManagement.this,ClassIntroActivity.class);
                startActivity(intent);
            }
        });


        awesomeValidation = new AwesomeValidation(BASIC);


        ed1 = findViewById(R.id.ClassId);
        ed2 = findViewById(R.id.teachername);
        ed3 = findViewById(R.id.classday);
        ed4 = findViewById(R.id.duration);
        ed5 = findViewById(R.id.startingtime);

        awesomeValidation.addValidation(ClassManagement.this, R.id.ClassId, "[A-Z0-9\\s]+", R.string.err_classId);
        awesomeValidation.addValidation(ClassManagement.this, R.id.teachername, "[A-Za-z\\s]+", R.string.err_teachername);
        awesomeValidation.addValidation(ClassManagement.this, R.id.classday, "[A-Za-z\\s]+", R.string.err_classday);
        awesomeValidation.addValidation(ClassManagement.this, R.id.duration,Range.closed(1.5f, 6.0f), R.string.err_duration);
        awesomeValidation.addValidation(ClassManagement.this, R.id.startingtime, "[0-9:\\s]+", R.string.err_startingtime);


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
    public void insert() {
        if (awesomeValidation.validate()) {
            try {
                String classid = ed1.getText().toString();
                String teachername = ed2.getText().toString();
                String day = ed3.getText().toString();
                String duration = ed4.getText().toString();
                String time = ed5.getText().toString();

                SQLiteDatabase db = openOrCreateDatabase("management", Context.MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT,classid VARCHAR,teachername VARCHAR,day VARCHAR,duration VARCHAR,time VARCHAR)");

                String sql = "insert into records(classid,teachername,day,duration,time)values(?,?,?,?,?)";
                SQLiteStatement statement = db.compileStatement(sql);
                statement.bindString(1, classid);
                statement.bindString(2, teachername);
                statement.bindString(3, day);
                statement.bindString(4, duration);
                statement.bindString(5, time);
                statement.execute();
                Toast.makeText(this, "Schedule addded", Toast.LENGTH_LONG).show();

                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");
                ed5.setText("");
                ed1.requestFocus();

            } catch (Exception ex) {
                Toast.makeText(this, "Schedule is not addded", Toast.LENGTH_LONG).show();
                ex.printStackTrace();
            }
        }
        else{
            Toast.makeText(this, "Validation failed", Toast.LENGTH_LONG).show();
        }
    }
}