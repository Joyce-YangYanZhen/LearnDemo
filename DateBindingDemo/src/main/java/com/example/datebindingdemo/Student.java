package com.example.datebindingdemo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Student extends BaseObservable {
    //如果是 private 修饰符，则在成员变量的 get 方法上添加 @Bindable 注解
    private int age;
    //如果是 public 修饰符，则可以直接在成员变量上方加上 @Bindable 注解
    @Bindable
    public String name;
    @Bindable
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
        //只更新本字段，BR 的生成通过注释 @Bindable 生成，可以通过 BR notify 特定属性关联的视图
        notifyPropertyChanged(BR.age);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        //更新所有字段
        notifyChange();
    }
}
