package com.example.madproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HallModelAdapter extends ArrayAdapter<HallModel> {

    private Context context;
    private int resource;
    List<HallModel> hallmodels;

    HallModelAdapter(Context context, int resource, List<HallModel> hallmodels){
        super(context,resource,hallmodels);
        this.context= context;
        this.resource= resource;
        this.hallmodels= hallmodels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater inflater= LayoutInflater.from(context);
        View row= inflater.inflate(resource,parent,false);

        TextView hallId= row.findViewById(R.id.hallid);
        TextView capacity= row.findViewById(R.id.capacity);
        TextView ac= row.findViewById(R.id.ac);
        TextView hallManager= row.findViewById(R.id.hallmanager);
        TextView teacher= row.findViewById(R.id.teacher);
        ImageView imageView= row.findViewById(R.id.onGoing);

        HallModel hallModel= hallmodels.get(position);
        hallId.setText(hallModel.getHallId());
        capacity.setText(hallModel.getCapacity());
        ac.setText(hallModel.getAc());
        hallManager.setText(hallModel.getHallManager());
        teacher.setText((hallModel.getTeacher()));
        imageView.setVisibility(row.INVISIBLE);

        if(hallModel.getFinished() > 0){
            imageView.setVisibility(View.VISIBLE);
        }
         return row;
    }
}





















