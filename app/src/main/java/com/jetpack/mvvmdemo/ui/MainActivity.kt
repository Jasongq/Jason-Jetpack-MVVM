package com.jetpack.mvvmdemo.ui

import android.os.Bundle
import android.widget.Toast
import com.jetpack.mvvmdemo.R
import com.jetpack.mvvmdemo.base.BaseActivity
import com.jetpack.mvvmdemo.databinding.ActivityMainBinding
import com.jetpack.mvvmdemo.viewmodel.MainViewModel

class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>() {

    override fun layoutId(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {

    }

    var exitTime=0L
    override fun onBackPressed() {
        if(System.currentTimeMillis()-exitTime>2000){
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
            exitTime=System.currentTimeMillis()
        }else{
            //exitProcess(0)
            super.onBackPressed()
        }
    }
}