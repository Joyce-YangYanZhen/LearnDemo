package com.example.datebindingdemo;

import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class User extends BaseObservable {//观察某个数据，及时监听它的改变
    private String name;
    private String nike_name;
    private boolean vip;
    private String email;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private String icon;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int level;
    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    @Bindable//表示name会被观察到， 然后及时改变
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNike_name() {
        return nike_name;
    }

    public void setNike_name(String nike_name) {
        this.nike_name = nike_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void clickName(View view){
        Toast.makeText(view.getContext(),"点击用户名"+name,Toast.LENGTH_SHORT).show();
    }
    public boolean longClickNickName(View view){
        Toast.makeText(view.getContext(),"长按点击事件",Toast.LENGTH_SHORT).show();
        return true;
    }

    public void click(View view){
        setName(getName()+"(已点击)");
        notifyPropertyChanged(BR.name);
    }


}
