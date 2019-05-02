package com.example.ldtrong.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tnthanh.contact.R;

import java.util.ArrayList;

public class giao_dien extends AppCompatActivity {
    EditText edt1,edt2,edt3;
    database database;
    doituong doituong;
    Button btnadd;
    Intent intent;
    int str1 = 0;
    boolean kt;
    ArrayList<doituong> doituongs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giao_dien);
        edt1 = (EditText)findViewById(R.id.edt1);
        edt2 = (EditText)findViewById(R.id.edt2);
        edt3 = (EditText)findViewById(R.id.edt3);

        intent = getIntent();
        String str = intent.getStringExtra("id");
        kt = intent.getBooleanExtra("boolean",false);
        System.out.println(kt);


        database = new database(this);

        if(kt){
            doituongs = database.getThongTin(str);
            str1 = doituongs.get(0).getId();
            edt1.setText(doituongs.get(0).getFilsname());
            edt2.setText(doituongs.get(0).getMobile());
            edt3.setText(doituongs.get(0).getEmail());
        }

        btnadd = findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kt == true){
                    try{
                        doituong = new doituong(str1,edt1.getText().toString(),edt2.getText().toString(),edt3.getText().toString());
                        database.update(doituong);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }else {
                    try{
                        doituong = new doituong(edt1.getText().toString(),edt2.getText().toString(),edt3.getText().toString());
                        database.addStudent(doituong);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                intent = new Intent(giao_dien.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
