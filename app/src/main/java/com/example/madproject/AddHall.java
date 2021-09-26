package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class AddHall extends AppCompatActivity {

    private EditText hallId,capacity,ac,hallManager,teacher;
    private Button add;
    private DbHandler dbHandler;
    private Context context;


    boolean isAllFieldsChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hall);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_hall);

        hallId= findViewById(R.id.editText_hallid);
        capacity= findViewById(R.id.editText_capacity);
        ac= findViewById(R.id.editText_ac);
        hallManager= findViewById(R.id.editText_hallmanager);
        teacher= findViewById(R.id.editText_teacher);
        add= findViewById(R.id.buttonAdd);

        context=this;

        dbHandler= new DbHandler(context);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //put in to local variables
             String userHallId= hallId.getText().toString();
             String userCapacity= capacity.getText().toString();
             String userAc= ac.getText().toString();
             String userHallManager= hallManager.getText().toString();
             String userTeacher= teacher.getText().toString();
             long started= System.currentTimeMillis();

             isAllFieldsChecked = CheckAllFields();

             //model class object create
             if (isAllFieldsChecked) {

                    HallModel hallModel= new HallModel(userHallId,userCapacity,userAc,userHallManager,userTeacher,started,0);
                    dbHandler.addHall(hallModel);

                    startActivity(new Intent(context,MainActivityHallList.class));
                    Toast.makeText(getApplicationContext(),"Hall Added",Toast.LENGTH_SHORT).show();
                }else{
                 Toast.makeText(getApplicationContext(),"Hall Not Added",Toast.LENGTH_SHORT).show();
             }
              //  startActivity(new Intent(context,MainActivityHallList.class));
            }
            private boolean CheckAllFields() {
                if (hallId.length() == 0) {
                    hallId.setError("This field is required");
                    return false;
                }

                if (capacity.length() >4) {
                    capacity.setError("Hall Capacity Maximum 1000");
                    return false;
                }

                if (ac.length() == 0) {
                    ac.setError("This field is required");
                    return false;
                }

                if (hallManager.length() == 0) {
                    hallManager.setError("This field is required");
                    return false;
                } else if (teacher.length() ==0) {
                    teacher.setError("This field is required");
                    return false;
                }
                return true;
            }

        });

    }


}












