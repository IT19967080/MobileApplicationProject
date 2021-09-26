package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class TeacherManagement extends AppCompatActivity {

    Button button,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_management);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_teacher_management);

        button = findViewById(R.id.btn_addteachers);
        button2=findViewById(R.id.btn_salary);



        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent c= new Intent(getApplicationContext(),Add_Teacher.class);
                startActivity(c);
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent c= new Intent(getApplicationContext(),incomecal.class);
                startActivity(c);
            }
        });





    }
}