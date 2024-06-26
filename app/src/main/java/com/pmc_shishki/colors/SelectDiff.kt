package com.pmc_shishki.colors

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class SelectDiff : ActivityWithoutBack() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_diff)

        val buttonBackToMenuSelectDiff: Button = findViewById(R.id.buttonBackSelectDiff)
        buttonBackToMenuSelectDiff.setOnClickListener {
            val toActivityMainMenu = Intent(this, MainActivity::class.java)
            startActivity(toActivityMainMenu)
        }

        val buttonEasyDiffGame: Button = findViewById(R.id.buttonEasyDiff)
        buttonEasyDiffGame.setOnClickListener {
            val toEasyDiffGame = Intent(this, ActivityGame::class.java)
            toEasyDiffGame.putExtra("count colors", 1)
            toEasyDiffGame.putExtra("diffName", "recordEz")
            toEasyDiffGame.putExtra("delay", 1000L)
            startActivity(toEasyDiffGame)
        }

        val buttonMediumDiffGame: Button = findViewById(R.id.buttonMediumDiff)
        buttonMediumDiffGame.setOnClickListener {
            val toMediumDiffGame = Intent (this, ActivityGame::class.java)
            toMediumDiffGame.putExtra("count colors", 2)
            toMediumDiffGame.putExtra("diffName", "recordMedium")
            toMediumDiffGame.putExtra("delay", 850L)
            startActivity(toMediumDiffGame)
        }

        val buttonHardDiffGame: Button = findViewById(R.id.buttonHardDiff)
        buttonHardDiffGame.setOnClickListener {
            val toHardDiffGame = Intent (this, ActivityGame::class.java)
            toHardDiffGame.putExtra("count colors", 2)
            toHardDiffGame.putExtra("diffName", "recordHard")
            toHardDiffGame.putExtra("delay", 500L)
            startActivity(toHardDiffGame)
        }

        val buttonCustomDiffGame: Button = findViewById(R.id.buttonCustomDiff)
        buttonCustomDiffGame.setOnClickListener {
            val toCustomSettings = Intent (this, CustomSettings::class.java)
            startActivity(toCustomSettings)
        }
    }
}