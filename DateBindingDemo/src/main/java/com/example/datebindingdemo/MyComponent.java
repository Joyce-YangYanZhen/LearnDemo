package com.example.datebindingdemo;

import androidx.databinding.DataBindingComponent;


public class MyComponent implements DataBindingComponent {
    private Utils utils;

    public Utils getUtils() {
        if (utils == null) {
            utils = new Utils();
        }

        return utils;
    }
}
