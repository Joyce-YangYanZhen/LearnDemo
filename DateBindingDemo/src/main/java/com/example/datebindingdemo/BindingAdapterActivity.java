package com.example.datebindingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;


import com.example.datebindingdemo.databinding.ActivityBindingAdapterBinding;

import java.util.Random;

public class BindingAdapterActivity extends AppCompatActivity {
    ImageBean imageBean;
    ActivityBindingAdapterBinding adapterBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterBinding = DataBindingUtil.setContentView(this,R.layout.activity_binding_adapter);
        imageBean = new ImageBean();
        adapterBinding.setImageBean(imageBean);
        adapterBinding.setChangeUrl(new ChangeUrl());
    }
    public class ChangeUrl{
        public void changeMethod(ImageBean bean){
            bean.setUrl("图片下载地址");
            CustomBindMethod.bindImageUrl(adapterBinding.image,bean.getUrl());

        }
    }
}