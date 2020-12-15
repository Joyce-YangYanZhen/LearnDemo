package com.example.datebindingdemo;

import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;

public class CustomBindMethod {
    @BindingAdapter("android:imageUrl")
    public static void bindImageUrl(ImageView view, String imageUrl){
        //可以在此处下载图片
        if(null!=imageUrl){
            Log.e("改变的Url地址",imageUrl);
        }
    }
    @BindingAdapter("android:text")
    public static void setText(Button view, String text) {
        view.setText(text + "-后缀");
    }
    //以下方法会将布局文件中所有以@{String}方式引用到的String类型变量加上后缀-conversionString
    @BindingConversion
    public static String conversionString(String text) {
        return text + "-conversionString";
    }
}
