package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditStudent extends AppCompatActivity {

    private databaseHelper_Student stu_Db;
    private EditText editFname,editLname,editAddress,editGender,editPnumber,editParentPnumber;
    private Button edit;
    private Context context;
    private Long updateStu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        context= this;
        stu_Db = new databaseHelper_Student(context);

        editFname = (EditText) findViewById(R.id.stu_edit_fname2);
        editLname = (EditText) findViewById(R.id.stu_edit_lname2);
        editAddress = (EditText) findViewById(R.id.stu_edit_address2);
        editGender = (EditText) findViewById(R.id.stu_edit_gender2);
        editPnumber = (EditText) findViewById(R.id.stu_edit_number2);
        editParentPnumber = (EditText) findViewById(R.id.stu_edit_parent_numbr2);
        edit = (Button) findViewById(R.id.stu_edit_submit_button);

        final String id= getIntent().getStringExtra("id");
        StudentModel studentmodel= stu_Db.getOneStudentDetails(Integer.parseInt(id));

        editFname.setText(studentmodel.getStudentFname());
        editLname.setText(studentmodel.getStudentLname());
        editAddress.setText(studentmodel.getStudentAddress());
        editGender.setText(studentmodel.getStudentGender());
        editPnumber.setText(studentmodel.getStudentnumber());
        editParentPnumber.setText(studentmodel.getParentnumber());



        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FnameText= editFname.getText().toString();
                String LnameText= editLname.getText().toString();
                String AddressText= editAddress.getText().toString();
                String GenderText= editGender.getText().toString();
                String NumberText= editPnumber.getText().toString();
                String ParentNumberText= editParentPnumber.getText().toString();
                updateStu = System.currentTimeMillis();

                StudentModel studentModel=new StudentModel(Integer.parseInt(id),FnameText,LnameText,AddressText,GenderText,NumberText,ParentNumberText,updateStu,0);

                int state= stu_Db.updateStudent(studentModel);
                System.out.println(state);
                startActivity(new Intent(context,viewStudent.class));
            }
        });


    }
}