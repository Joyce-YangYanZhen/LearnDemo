package com.example.constraintlayouttest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.listener.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        val list = mutableListOf<String>()
        list.add("登录页面")
        val customerAdapter = CustomerAdapter(R.layout.adapter_item, list)
        recycler_view.adapter = customerAdapter

        customerAdapter.setOnItemClickListener(OnItemClickListener{ adapter, view, position ->
            when(position){
                0 -> startActivity(Intent(this, Demo1Activity::class.java))


            }
        })
    }
}
