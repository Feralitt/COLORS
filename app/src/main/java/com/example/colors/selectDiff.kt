package com.example.colors

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class selectDiff : AppCompatActivity() {
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
            val toEasyDiffGame = Intent(this, activity_easy_game::class.java)
            startActivity(toEasyDiffGame)
        }
    }
}