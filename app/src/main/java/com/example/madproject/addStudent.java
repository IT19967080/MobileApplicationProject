package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addStudent extends AppCompatActivity {

    private databaseHelper_Student stu_Db;
    private EditText editFname,editLname,editAddress,editGender,editPnumber,editParentPnumber;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        stu_Db = new databaseHelper_Student(this);
        editFname = (EditText) findViewById(R.id.stu_add_fname2);
        editLname = (EditText) findViewById(R.id.stu_add_lname2);
        editAddress = (EditText) findViewById(R.id.stu_add_address2);
        editGender = (EditText) findViewById(R.id.stu_add_gender2);
        editPnumber = (EditText) findViewById(R.id.stu_add_number2);
        editParentPnumber = (EditText) findViewById(R.id.stu_add_parent_numbr2);
        addButton = (Button) findViewById(R.id.stu_add_submit_button);
        insertStudent();

    }

    //data insert method
    public void insertStudent()  {
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = stu_Db.insertStudent(editFname.getText().toString(),editLname.getText().toString(),
                                editAddress.getText().toString(),editGender.getText().toString(),
                                editPnumber.getText().toString(),editParentPnumber.getText().toString());
                        if (isInserted = true)
                            Toast.makeText(addStudent.this,"Student Added",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(addStudent.this,"Something Went Wrong!",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}