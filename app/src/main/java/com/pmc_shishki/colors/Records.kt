package com.pmc_shishki.colors

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Records : ActivityWithoutBack() {
    private val NO_RECORD = -1L     // если рекорда нет, он -1
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var labelRecordEz: TextView
    private lateinit var labelRecordMedium: TextView
    private lateinit var labelRecordHard: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)
        labelRecordEz = findViewById(R.id.labelRecordsEasy)
        labelRecordMedium = findViewById(R.id.labelRecordsMedium)
        labelRecordHard = findViewById(R.id.labelRecordsHard)
        val buttonClearRecords: Button = findViewById(R.id.buttonClearRecords)
        val buttonBackToMenuRecords: Button = findViewById(R.id.buttonBackToMenu)

        sharedPrefs = getSharedPreferences("RecordsPrefs", Context.MODE_PRIVATE)
        // рекорды для лёгкой сложности
        labelRecordEz.text = getRecordStr("recordEz")
        // рекорды для средней сложности
        labelRecordMedium.text = getRecordStr("recordMedium")
        // рекорды для сложной сложности
        labelRecordHard.text = getRecordStr("recordHard")

        buttonBackToMenuRecords.setOnClickListener {
            val activityMainMenu = Intent(this, MainActivity::class.java)
            startActivity(activityMainMenu)
        }
        //сброс рекордов
        buttonClearRecords.setOnClickListener{
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder
                .setMessage(getString(R.string.clear_records))
                .setNegativeButton(getString(R.string.clear_records_yes)) { _, _ -> resetRecords() }
                .setPositiveButton(getString(R.string.clear_records_no)) { _, _ ->}
                .show()
        }
    }

    @SuppressLint("SetTextI18n")
    public fun resetRecords() {
        val editor = sharedPrefs.edit()
        editor.remove("recordEz")
        editor.remove("recordMedium")
        editor.remove("recordHard")
        editor.apply()
        labelRecordEz.text = getString(R.string.no_record)
        labelRecordMedium.text = getString(R.string.no_record)
        labelRecordHard.text = getString(R.string.no_record)
    }

    public fun getRecordStr(diffName: String): String {
        val record: Long = sharedPrefs.getLong(diffName, NO_RECORD)
        if (record == NO_RECORD)
            return getString(R.string.no_record)
        return "%.3f сек".format(record / 1000.0)
    }
}