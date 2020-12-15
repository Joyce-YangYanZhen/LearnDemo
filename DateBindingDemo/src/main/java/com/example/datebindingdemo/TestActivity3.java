package com.example.datebindingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.datebindingdemo.databinding.ActivityTest3Binding;

public class TestActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTest3Binding activityTest3Binding = DataBindingUtil.setContentView(this,R.layout.activity_test3);
        Student student = new Student();
        activityTest3Binding.setStudent(student);
    }
}