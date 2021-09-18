package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration_page extends AppCompatActivity {

    EditText username,email,password,repassword;
    Button btn_ksignUp,btn_ksignIn;
    DBRegister myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        username = (EditText)findViewById(R.id.username);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        repassword = (EditText)findViewById(R.id.repassword);

        btn_ksignUp=(Button) findViewById(R.id.btn_ksignUp);
        btn_ksignIn=(Button) findViewById(R.id.btn_ksignIn);

        myDB= new DBRegister(this);

        btn_ksignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String eml=email.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();

                if(user.equals("") || eml.equals("") || pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(Registration_page.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (pass.equals(repass))
                    {
                        Boolean usercheckResult = myDB.checkusername(user);
                        if(usercheckResult==false)
                        {
                           Boolean regResult= myDB.insertData(user,eml,pass);
                           if(regResult==true){
                               Toast.makeText(Registration_page.this,"Registration Successfull", Toast.LENGTH_SHORT).show();
                               Intent intent=new Intent(getApplicationContext(),login_page.class);
                               startActivity(intent);

                           }
                           else
                           {
                               Toast.makeText(Registration_page.this,"Registration Unsuccessfull", Toast.LENGTH_SHORT).show();
                           }
                        }
                        else
                        {
                            Toast.makeText(Registration_page.this, "User already Exist! \n Please Sign In", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(Registration_page.this,"Password not Matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_ksignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),login_page.class);
                startActivity(intent);
            }
        });



    }
}