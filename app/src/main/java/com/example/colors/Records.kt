package com.example.colors

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Records : ActivityWithoutBack() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)
        val labelRecordEz: TextView = findViewById(R.id.labelRecordsEasy)
        val buttonClearRecords: Button = findViewById(R.id.buttonClearRecords)

        val sharedPrefs = getSharedPreferences("RecordsPrefs", Context.MODE_PRIVATE)
        // если рекорда нет, он -1
        var recordEz = sharedPrefs.getLong("recordEz", -1)

        // если рекорда нет, то он None, а если есть, то он записывавется в переменную
        val recordText = if (recordEz == -1L)
            "None"
        else if (recordEz < 10) {
            "0,00${recordEz} сек"
        }
        else if (recordEz < 100) {
            "0,0${recordEz} сек"
        }
        else "0,${recordEz} сек"
        labelRecordEz.text = recordText

        val buttonBackToMenuRecords: Button = findViewById(R.id.buttonBackToMenu)

        buttonBackToMenuRecords.setOnClickListener {
            val activityMainMenu = Intent(this, MainActivity::class.java)
            startActivity(activityMainMenu)
        }
        //сброс рекордов
        buttonClearRecords.setOnClickListener{
            //сброс easy сложности
            val editor = sharedPrefs.edit()
            editor.remove("recordEz")
            editor.apply()
            labelRecordEz.text = "None"
    }
    }
}