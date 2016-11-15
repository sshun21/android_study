package com.example.shin.quickcoding2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    int count=0;
    int max_num = 100;
    int min_num = 1;
    int mid_num = (max_num+min_num)/2;
    int master_num;

    Random random = new Random();
    int rand_num = random.nextInt(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bnt01 = (Button)findViewById(R.id.bnt01);
        Button bnt02 = (Button)findViewById(R.id.bnt02);
        Button bnt03 = (Button)findViewById(R.id.bnt03);
        Button bnt04 = (Button)findViewById(R.id.bnt04);

        bnt01.setOnClickListener(this);
        bnt02.setOnClickListener(this);
        bnt03.setOnClickListener(this);
        bnt04.setOnClickListener(this);

    }

    public void onClick(View v) {


        EditText tv01 = (EditText)findViewById(R.id.et01);
        TextView tv02 = (TextView)findViewById(R.id.tv01);


    if(v.getId()==R.id.bnt01){
        master_num = Integer.parseInt(String.valueOf(tv01.getText()));
        tv02.setText("입력하신 숫자가 " + rand_num + "입니까?");
        mid_num  = rand_num;

    }
    if(v.getId()==R.id.bnt02){
        if(master_num != mid_num) {
            min_num = mid_num + 1;
            mid_num = (min_num + max_num) / 2;
            count++;
            tv02.setText("입력하신 숫자가 " + mid_num + "입니까?");
        }
    }
    if(v.getId()==R.id.bnt03){
        if(master_num != mid_num) {
             max_num = mid_num - 1;
             mid_num = (min_num + max_num) / 2;
             count++;
             tv02.setText("입력하신 숫자가 " + mid_num + "입니까?");
         }
    }
    if(v.getId()==R.id.bnt04){
        tv02.setText("빙고~!!  " + count + "번 만에 맞추셨습니다.");
    }

    }

}
