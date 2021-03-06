package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.common.collect.Range;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class incomecal extends AppCompatActivity {
public incomecal(){
    System.out.println("sdadad");
}

    EditText ed1,ed2,ed3,ed4;
    Button btn1,btn2;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomecal);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//this line hides the action bar
        setContentView(R.layout.activity_incomecal);
        awesomeValidation = new AwesomeValidation(BASIC);

        ed1 = findViewById(R.id.teacher_id);
        ed2 = findViewById(R.id.teacher_sal);
        ed3 = findViewById(R.id.company_profit);
        ed4 = findViewById(R.id.net_sal);

        btn1 = findViewById(R.id.btnsal_1);
        btn2 = findViewById(R.id.btn_sal2);

        awesomeValidation.addValidation(incomecal.this, R.id.teacher_sal, Range.closed(5000.0f, 1000000000f), R.string.err_teachersal);
        awesomeValidation.addValidation(incomecal.this, R.id.teacher_id, "[A-Za-z\\s]+", R.string.errteacherid);




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(TextUtils.isEmpty(ed1.getText().toString())){
                    ed1.setError("Teacher name is compulsory");
                    return ;
                }
                if(TextUtils.isEmpty(ed2.getText().toString())){
                    ed2.setError("Teacher sal is compulsory");
                    return ;
                }

                if(awesomeValidation.validate()){
                double netsalary ;

                double salary = Double.parseDouble(ed2.getText().toString());

                double company_profit = calculateSalary(Double.parseDouble(ed2.getText().toString()));

                ed3.setText(String.valueOf(company_profit));


                netsalary = calculateNetsalary(salary,company_profit);

                ed4.setText(String.valueOf(netsalary));
                    Toast.makeText(incomecal.this,"Calculation Success",Toast.LENGTH_LONG).show();
                }
                else{
                   Toast.makeText(incomecal.this,"Calculation Failed",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

    }

     public static double calculateSalary(double salary){
        double company_profit;
        if(salary>100000)
        {
            company_profit = salary * 20/100;
        }
        else if(salary>50000)
        {
            company_profit = salary * 10/100;
        }
        else{
            company_profit = 0;
        }
        return company_profit;
    }

    public static double calculateNetsalary(double salary,double company_profit){
        return  salary- company_profit;
    }


    public void clear(){
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
        ed1.requestFocus();
    }

}