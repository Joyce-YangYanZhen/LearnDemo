package com.example.datebindingdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }


    public void danxiang(View view){
        Intent intent = new Intent(MainActivity2.this,TestActivity2.class);
        startActivity(intent);
    }
    public void shuangxiang(View view){
        Intent intent = new Intent(MainActivity2.this,TestActivity3.class);
        startActivity(intent);
    }
    public void shijian(View view){
        Intent intent = new Intent(MainActivity2.this,BindThingActivity.class);
        startActivity(intent);
    }
    public void bindingAdapter(View view){
        Intent intent = new Intent(MainActivity2.this,BindingAdapterActivity.class);
        startActivity(intent);
    }


}