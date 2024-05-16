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
        val labelRecordMedium: TextView = findViewById(R.id.labelRecordsMedium)
        val labelRecordHard: TextView = findViewById(R.id.labelRecordsHard)
        val buttonClearRecords: Button = findViewById(R.id.buttonClearRecords)

        val sharedPrefs = getSharedPreferences("RecordsPrefs", Context.MODE_PRIVATE)
        val sharedPrefs2 = getSharedPreferences("RecordsPrefs2", Context.MODE_PRIVATE)
        val sharedPrefs3 = getSharedPreferences("RecordsPrefs3", Context.MODE_PRIVATE)
        // если рекорда нет, он -1
        var recordEz = sharedPrefs.getLong("recordEz", -1)
        var recordMedium = sharedPrefs2.getLong("recordMedium", -1)
        var recordHard = sharedPrefs3.getLong("RecordHard", -1)

        // рекорды для лёгкой сложности
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

        // рекорды для средней сложности
        // если рекорда нет, то он None, а если есть, то он записывавется в переменную
        val recordTextMedium = if (recordMedium == -1L)
            "None"
        else if (recordMedium < 10) {
            "0,00${recordMedium} сек"
        }
        else if (recordMedium < 100) {
            "0,0${recordMedium} сек"
        }
        else "0,${recordMedium} сек"
        labelRecordMedium.text = recordTextMedium

        // рекорды для сложной сложности
        // если рекорда нет, то он None, а если есть, то он записывавется в переменную
        val recordTextHard = if (recordHard == -1L)
            "None"
        else if (recordHard < 10) {
            "0,00${recordHard} сек"
        }
        else if (recordHard < 100) {
            "0,0${recordHard} сек"
        }
        else "0,${recordHard} сек"
        labelRecordHard.text = recordTextHard



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

            //сброс рекорда средней сложности
            val editorMedium = sharedPrefs2.edit()
            editorMedium.remove("recordMedium")
            editorMedium.apply()
            labelRecordMedium.text = "None"

            //сброс рекорда сложной сложности
            val editorHard = sharedPrefs3.edit()
            editorHard.remove("recordHard")
            editorHard.apply()
            labelRecordHard.text = "None"
    }
    }
}