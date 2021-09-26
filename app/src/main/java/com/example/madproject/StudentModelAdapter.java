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

public class StudentModelAdapter extends ArrayAdapter<StudentModel> {

    private Context context;
    private int resource;
    List<StudentModel> studentmodels;

    StudentModelAdapter(Context context, int resource, List<StudentModel> studentmodels){
        super(context,resource, studentmodels);
        this.context= context;
        this.resource= resource;
        this.studentmodels= studentmodels;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater inflater= LayoutInflater.from(context);
        View row= inflater.inflate(resource,parent,false);

        TextView fname= row.findViewById(R.id.fname);
        TextView lname= row.findViewById(R.id.lname);
        TextView address= row.findViewById(R.id.address);
        TextView gender= row.findViewById(R.id.gender);
        TextView pnumber= row.findViewById(R.id.pnumber);
        TextView parentPnumber= row.findViewById(R.id.parentPnumber);


        StudentModel studentModel= studentmodels.get(position);

        fname.setText(studentModel.getStudentFname());
        lname.setText(studentModel.getStudentLname());
        address.setText(studentModel.getStudentAddress());
        gender.setText(studentModel.getStudentGender());
        pnumber.setText(studentModel.getStudentnumber());
        parentPnumber.setText(studentModel.getParentnumber());


        return row;
    }
}





















