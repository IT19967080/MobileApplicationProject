package com.example.madproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class viewStudent extends AppCompatActivity {

    private databaseHelper_Student stu_Db;
    private ListView studentList;
    private List<StudentModel> studentModels;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);


        context=this;
        stu_Db = new databaseHelper_Student(context);
        studentList = findViewById(R.id.stu_view_list);
        studentModels= new ArrayList<>();

        studentModels= stu_Db.getAllStudentModals();

        context=this;

        StudentModelAdapter studentadapter = new StudentModelAdapter(context,R.layout.single_student,studentModels);
        studentList.setAdapter(studentadapter);




        //viewAllStudents();

        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                final StudentModel studentmodel = studentModels.get(i);

                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle(studentmodel.getStudentFname());
                String alert1= studentmodel.getStudentLname();
                String alert2= studentmodel.getStudentAddress();
                String alert3= studentmodel.getStudentGender();
                String alert4= studentmodel.getStudentnumber();
                String alert5= studentmodel.getParentnumber();

                    builder.setMessage(alert1 + "\n" + alert2 + "\n" + alert3 + "\n" + alert4 + "\n" + alert5);


                    builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            stu_Db.deleteStudent(studentmodel.getStudentId());
                            startActivity(new Intent(context, viewStudent.class));

                        }
                    });

                    builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(context, EditStudent.class);
                            intent.putExtra("id", String.valueOf(studentmodel.getStudentId()));
                            startActivity(intent);

                        }
                    });


                    builder.show();
                }



        });

    }


    }
