package com.example.colors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // переход в выбор сложности
        val buttonMainMenuPlay: Button = findViewById(R.id.buttonPlay)
        buttonMainMenuPlay.setOnClickListener {
            val toActivitySelectDiff = Intent(this, SelectDiff::class.java)
            startActivity(toActivitySelectDiff)
        }
        // переход в рекорды
        val buttonRecordsMenu: Button = findViewById(R.id.buttonRecords)
        buttonRecordsMenu.setOnClickListener {
            val toActivityRecords = Intent(this, Records::class.java)
            startActivity(toActivityRecords)
        }
    }
}