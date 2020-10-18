package com.example.fourth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LanguageInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_info)
        actionBar?.setHomeButtonEnabled(true)
        findViewById<TextView>(R.id.title).apply {
            text = intent.extras?.getString("TITLE")
        }
        findViewById<TextView>(R.id.description).apply {
            text = intent.extras?.getString("DESCRIPTION")
        }
    }
}