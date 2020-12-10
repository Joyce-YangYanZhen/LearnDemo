package com.example.datebindingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.datebindingdemo.databinding.ActivityTest2Binding;

public class TestActivity2 extends AppCompatActivity {
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_test2);//摒弃原有的设置布局方式，通过DataBindingUtil设置
        //ActivityTest2Binding是根据layout名字生成
        ActivityTest2Binding activityMain2Binding = DataBindingUtil.setContentView(this, R.layout.activity_test2);
        student = new Student();
        student.setAge(10);
        student.setName("小红");
        activityMain2Binding.setStudent(student);

    }

}