package com.example.administrator.quickcoding05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    Button bnt01, bnt02;
    EditText ed01;
    TextView tv01, tv02;
    LinkedList<String> string_list = new LinkedList<>();
    LinkedList<Integer> integer_list = new LinkedList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed01 = (EditText)findViewById(R.id.ed01);
        bnt01 = (Button)findViewById(R.id.bnt01);
        bnt02 = (Button)findViewById(R.id.bnt02);
        tv01 = (TextView)findViewById(R.id.tv01);
        tv02 = (TextView)findViewById(R.id.tv02);
        bnt01.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String temp = ed01.getText().toString();
                int temp_int;
                try{
                    temp_int=Integer.parseInt(temp);
                    integer_list.add(temp_int);
                }catch (NumberFormatException e){
                    string_list.add(temp);
                }
            }
        });

        bnt02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv01.setText(integer_list.toString());
                tv02.setText(string_list.toString());
            }
        });
    }
}

