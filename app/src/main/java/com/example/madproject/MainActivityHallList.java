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

public class MainActivityHallList extends AppCompatActivity {
    private ImageView add;
    private ListView listView;
    private TextView count;
    Context context;

    private DbHandler dbHandler;

    private List<HallModel> hallModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hall_list);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_hall_list);

        context=this;
        dbHandler= new DbHandler(context);
        add= findViewById(R.id.add);
        listView= findViewById(R.id.halllist);
        count= findViewById(R.id.hallcount);
        hallModels= new ArrayList<>();

        hallModels= dbHandler.getAllHallModels();


         HallModelAdapter adapter= new HallModelAdapter(context,R.layout.single_hall,hallModels);
         listView.setAdapter(adapter);

        //get count from table
        int countHall=dbHandler.countHall();
        count.setText("You have "+countHall+" Halls");

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
              //  builder.setMessage(hallmodel.getTeacher());
                String alert1= hallmodel.getCapacity();
                String alert2= hallmodel.getAc();
                String alert3= hallmodel.getHallManager();
                String alert4= hallmodel.getTeacher();

                builder.setMessage(alert1+ "\n" + alert2+ "\n" + alert3+ "\n" + alert4);

                //finished -> Hall Full
                builder.setPositiveButton("Full", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hallmodel.setFinished(System.currentTimeMillis());
                        dbHandler.updateSingleHallModel(hallmodel);
                        startActivity(new Intent(context,MainActivityHallList.class));
                        Toast.makeText(getApplicationContext(),"Hall is Full",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteHallModel(hallmodel.getId());
                        startActivity(new Intent(context,MainActivityHallList.class));
                        Toast.makeText(getApplicationContext(),"Hall Deleted",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent= new Intent(context,EditHall.class);
                        intent.putExtra("id",String.valueOf(hallmodel.getId()));
                        startActivity(intent);

                    }
                });
                builder.show();

            }
        });


    }
}





































