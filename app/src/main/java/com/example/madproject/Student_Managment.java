package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Student_Managment extends AppCompatActivity {

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_managment);

        button1 = (Button)findViewById(R.id.stu_button);
        button2 = (Button)findViewById(R.id.stu_button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddStudent();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewDeleteStudent();
            }
        });
    }


    public void openAddStudent()
    {
        Intent intent = new Intent(this,addStudent.class);
        startActivity(intent);
    }

    public void openViewDeleteStudent()
    {
        Intent intent = new Intent(this,viewAndDeleteStudent.class);
        startActivity(intent);
    }
}