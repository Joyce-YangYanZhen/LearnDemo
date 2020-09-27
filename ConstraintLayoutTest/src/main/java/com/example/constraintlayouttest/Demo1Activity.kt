package com.example.constraintlayouttest

import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_demo1.*

class Demo1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo1)
        xieyi_tv.paint.flags = Paint.UNDERLINE_TEXT_FLAG

    }
}