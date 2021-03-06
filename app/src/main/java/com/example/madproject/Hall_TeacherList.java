package com.example.madproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Hall_TeacherList extends AppCompatActivity {
    private ImageView add;
    private ListView listView;
    private TextView count;
    Context context;

    ImageView imageView;

    private DbHandler dbHandler;

    private List<HallModel> hallModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_teacher_list);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_hall_teacher_list);

        imageView= findViewById(R.id.imageView4);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),Hall_Management_Home.class);
                startActivity(i);
            }
        });

        context=this;
        dbHandler= new DbHandler(context);
        add= findViewById(R.id.add);
        listView= findViewById(R.id.halllist1);
        count= findViewById(R.id.hallcount);
        hallModels= new ArrayList<>();

        hallModels= dbHandler.getAllHallModels();


        Hall_TeacherAdapter adapter= new Hall_TeacherAdapter(context,R.layout.single_teacher,hallModels);
        listView.setAdapter(adapter);

        //get count from table
        int countHall=dbHandler.countHall();
        count.setText(""+countHall+" Halls In Teachers");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,AddHall.class));

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final HallModel hallmodel= hallModels.get(position);

                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle(hallmodel.getHallId());
                //  builder.setMessage(hallmodel.getCapacity());
                //  builder.setMessage(hallmodel.getAc());
                //  builder.setMessage(hallmodel.getHallManager());
                 builder.setMessage(hallmodel.getTeacher());
              //  String alert1= hallmodel.getCapacity();
              //  String alert2= hallmodel.getAc();
              //  String alert3= hallmodel.getHallManager();
             //   String alert4= hallmodel.getTeacher();

              //  builder.setMessage(alert1+ "\n" + alert2+ "\n" + alert3+ "\n" + alert4);

                //finished -> Hall Full



                builder.show();

            }
        });


    }
}





































