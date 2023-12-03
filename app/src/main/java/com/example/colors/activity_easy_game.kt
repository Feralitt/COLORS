package com.example.colors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class activity_easy_game : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_game)


        val goalText: TextView = findViewById(R.id.textViewGoalEasy)

        // tooltip  0 - red, 1 - orange, 2 - yellow, 3 - green, 4 - light_blue, 5 - blue, 6 - purple
        // последнее число в random это не включительно
        val goal = (0 until 6 + 1).random()
        if (goal == 0)
            goalText.text = "Твоя цель - красный цвет"
        else if (goal == 1)
            goalText.text = "Твоя цель - оранжевый цвет"
        else if (goal == 2)
            goalText.text = "Твоя цель - жёлтый цвет"
        else if (goal == 3)
            goalText.text = "Твоя цель - зелёный цвет"
        else if (goal == 4)
            goalText.text = "Твоя цель - голубой цвет"
        else if (goal == 5)
            goalText.text = "Твоя цель - синий цвет"
        else if (goal == 6)
            goalText.text = "Твоя цель - фиолетовый цвет"






    }
}