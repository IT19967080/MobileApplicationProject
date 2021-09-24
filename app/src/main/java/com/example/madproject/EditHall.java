package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditHall extends AppCompatActivity {
    private EditText hallId,capacity,ac,hallManager,teacher;
    private Button edit;
    private DbHandler dbHandler;
    private Context context;
    private Long updateDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hall);

        context= this;
        dbHandler= new DbHandler(context);

        hallId= findViewById(R.id.editHallText_hallid);
        capacity= findViewById(R.id.editHallText_capacity);
        ac= findViewById(R.id.editHallText_ac);
        hallManager= findViewById(R.id.editHallText_hallmanager);
        teacher= findViewById(R.id.editHallText_teacher);
        edit= findViewById(R.id.buttonEdit);

        final String id= getIntent().getStringExtra("id");
        HallModel hallmodel= dbHandler.getSingleHallModel(Integer.parseInt(id));

        hallId.setText(hallmodel.getHallId());
        capacity.setText(hallmodel.getCapacity());
        ac.setText(hallmodel.getAc());
        hallManager.setText(hallmodel.getHallManager());
        teacher.setText(hallmodel.getTeacher());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hallIdText= hallId.getText().toString();
                String capacityText= capacity.getText().toString();
                String acText= ac.getText().toString();
                String hallManagerText= hallManager.getText().toString();
                String teacherText= teacher.getText().toString();
                updateDate= System.currentTimeMillis();

                HallModel hallModel=new HallModel(Integer.parseInt(id),hallIdText,capacityText,acText,hallManagerText,teacherText,updateDate,0);

                int state= dbHandler.updateSingleHallModel(hallModel);
                System.out.println(state);
                startActivity(new Intent(context,MainActivityHallList.class));
            }
        });


    }
}










