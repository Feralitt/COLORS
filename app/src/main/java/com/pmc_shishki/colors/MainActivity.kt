package com.pmc_shishki.colors

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button


class MainActivity : ActivityWithoutBack() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // скрываем все панели и кнопки
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

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