package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_page extends AppCompatActivity {

    EditText username,password;
    Button btn_klogin, btn_ksignUp2;
    DBRegister myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username=(EditText) findViewById(R.id.pt_email);
        password=(EditText) findViewById(R.id.pw_pwd);
        btn_klogin=(Button) findViewById(R.id.btn_klogin);
        btn_ksignUp2=findViewById(R.id.btn_ksignUp2);

        myDB=new DBRegister(this);

        btn_klogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(login_page.this,"Please Enter User name \n and Passwod Both!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean result=myDB.checkusernamePassword(user,pass);
                    if (result==true){
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(login_page.this,"Invalid User Name or Password! \n Try Again",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_ksignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registration_page.class);
                startActivity(intent);

            }
        });




            }
        }