package com.example.madproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Add_Teacher extends AppCompatActivity {

    EditText fullname, contact,subject, nationalid, address;
    ImageView back;
    Button add,view,edit,delete;
    DBCrud DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        fullname = findViewById(R.id.pt_kFullName);
        contact=findViewById(R.id.pt_mobNum);
        subject=findViewById(R.id.pt_ksubject);
        nationalid=findViewById(R.id.pt_kidcard);
        address=findViewById(R.id.pt_kaddress);

        back=findViewById(R.id.imageView5);

        add=findViewById(R.id.btn_ksadd);
        view=findViewById(R.id.btn_ksview2);
        edit=findViewById(R.id.btn_ksedit);
        delete=findViewById(R.id.btn_ksdelete);

        DB=new DBCrud(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullnameTXT = fullname.getText().toString();
                String contactTXT = contact.getText().toString();
                String subjectTXT = subject.getText().toString();
                String nationalidTXT = nationalid.getText().toString();
                String addressTXT = address.getText().toString();

                Boolean checkinsertdata= DB.insertteacherdata(fullnameTXT,contactTXT,subjectTXT,nationalidTXT,addressTXT);
                if(checkinsertdata==true)
                    Toast.makeText(Add_Teacher.this, "Teacher Added", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Add_Teacher.this, "Teacher Not Added",Toast.LENGTH_SHORT).show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullnameTXT = fullname.getText().toString();
                String contactTXT = contact.getText().toString();
                String subjectTXT = subject.getText().toString();
                String nationalidTXT = nationalid.getText().toString();
                String addressTXT = address.getText().toString();

                Boolean checkupdatedata= DB.updateteacherdata(fullnameTXT,contactTXT,subjectTXT,nationalidTXT,addressTXT);
                if(checkupdatedata==true)
                    Toast.makeText(Add_Teacher.this, "Teacher Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Add_Teacher.this, "Teacher Not Updated",Toast.LENGTH_SHORT).show();
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullnameTXT = fullname.getText().toString();


                Boolean checkdeletedata= DB.deleteteacherdata(fullnameTXT);
                if(checkdeletedata==true)
                    Toast.makeText(Add_Teacher.this, "Teacher Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Add_Teacher.this, "Teacher Not Deleted",Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText( Add_Teacher.this,"No Details Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Full Name :"+res.getString(0)+"\n");
                    buffer.append("Contact Num :"+res.getString(1)+"\n");
                    buffer.append("Subject :"+res.getString(2)+"\n");
                    buffer.append("National ID :"+res.getString(3)+"\n");
                    buffer.append("Address :"+res.getString(4)+"\n\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(Add_Teacher.this);
                builder.setCancelable(true);
                builder.setTitle("Teacher Details");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_Teacher.this,TeacherManagement.class);
                startActivity(intent);
            }
        });
    }
}