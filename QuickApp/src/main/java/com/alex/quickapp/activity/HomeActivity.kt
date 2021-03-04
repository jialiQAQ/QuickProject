package com.alex.quickapp.activity

import android.os.Bundle
import com.alex.quickapp.R
import com.alex.quickapp.base.BaseActivity

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}