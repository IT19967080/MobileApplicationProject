package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddHall extends AppCompatActivity {

    private EditText hallId,capacity,ac,hallManager,teacher;
    private Button add;
    private DbHandler dbHandler;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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



             //model class object create
                HallModel hallModel= new HallModel(userHallId,userCapacity,userAc,userHallManager,userTeacher,started,0);
                dbHandler.addHall(hallModel);


                startActivity(new Intent(context,MainActivityHallList.class));

            }

        });

    }
    public void showToast(View view) {
        Toast toast= Toast.makeText(getApplicationContext(),"Hall Added!",Toast.LENGTH_SHORT);
        toast.show();
    }

}












