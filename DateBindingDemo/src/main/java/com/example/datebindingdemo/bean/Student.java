package com.example.datebindingdemo.bean;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

public class Student {
    private ObservableInt age;
    private ObservableField<String> name;
    public ObservableInt getAge() {
        return age;
    }
    public void setAge(ObservableInt age) {
        this.age = age;
    }
    public ObservableField<String> getName() {
        return name;
    }
    public void setName(ObservableField<String> name) {
        this.name = name;
    }
}
