package com.example.datebindingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;

import android.os.Bundle;
import android.util.Log;

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

        activityMain2Binding.otherTv.setText("尽快尽快了");
        activityMain2Binding.setChange(new Change());

        //实现了 Observable 接口的类允许注册一个监听器，当可观察对象的属性更改时就会通知这个监听器
        student.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override //propertyId用于识别字段
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(propertyId==BR.age){
                    Log.e("改变的是age：",BR.age+"");
                }else if(propertyId==BR.name){
                    Log.e("改变的是name：",BR.name+"");
                }else if(propertyId==BR._all){
                    Log.e("全都改变了",BR._all+"");
                }
            }
        });
    }

    public class Change{
        public void changeAgeListener(){
            student.setAge(100);
        }
        public void changeAll(){
            student.setAge(120);
            student.setName("洗洗");
        }
    }

}