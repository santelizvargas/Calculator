package com.santeliz.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.santeliz.calculator.helpers.ButtonHelper
import com.santeliz.calculator.helpers.ViewHelper
import com.santeliz.calculator.models.Calculator

class MainActivity : AppCompatActivity() {
    private val viewHelper = ViewHelper.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewHelper.viewGroup = findViewById(R.id.main_layout)
        Calculator().builder()
    }
}