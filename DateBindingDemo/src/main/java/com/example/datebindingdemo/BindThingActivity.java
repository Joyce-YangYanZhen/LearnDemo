package com.example.datebindingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Toast;

import com.example.datebindingdemo.databinding.ActivityBindThingBinding;


public class BindThingActivity extends AppCompatActivity {
    Student student;
    ActivityBindThingBinding activityBindThingBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBindThingBinding = DataBindingUtil.setContentView(this,R.layout.activity_bind_thing);
        student = new Student();
        activityBindThingBinding.setStudent(student);
        activityBindThingBinding.setBindThing(new BindThing());
    }
    public class BindThing{
        public void onNameClick(Student s){
            Toast.makeText(getApplicationContext(),s.getName(),Toast.LENGTH_SHORT).show();
        }
        public void afterNameChanged(Editable editable){
            student.setName(editable.toString());
            activityBindThingBinding.setStudent(student);
        }
        public void afterAgeChanged(Editable editable){
            student.setAge(Integer.valueOf(editable.toString()));
            activityBindThingBinding.setStudent(student);

        }


    }
}