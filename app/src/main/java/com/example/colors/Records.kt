package com.example.colors

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Records : ActivityWithoutBack() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)

        val buttonBackToMenuRecords: Button = findViewById(R.id.buttonBackToMenuRecords)

        buttonBackToMenuRecords.setOnClickListener {
            val activityMainMenu = Intent(this, MainActivity::class.java)
            startActivity(activityMainMenu)
        }
    }
}