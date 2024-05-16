package com.example.colors

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class customSettings : ActivityWithoutBack() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_settings)

        val buttonBack: Button = findViewById(R.id.buttonBackCustomDiff)
        buttonBack.setOnClickListener {
            val toSelectDiff = Intent (this, selectDiff::class.java)
            startActivity(toSelectDiff)
        }
    }
}