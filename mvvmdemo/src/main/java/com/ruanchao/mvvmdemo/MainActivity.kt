package com.ruanchao.mvvmdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ruanchao.mvvmdemo.view.AnimalActivity
import com.ruanchao.mvvmdemo.view.HomeBlogFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_layout, HomeBlogFragment())
            .commit()
    }
}