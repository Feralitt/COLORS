package com.example.colors


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class activity_easy_game : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_game)

        val startButton: Button = findViewById(R.id.buttonStart)
        val goalText: TextView = findViewById(R.id.textViewGoalEasy)
        val ezButton: Button = findViewById(R.id.easyButton)
        val red: ImageView = findViewById(R.id.red)
        val orange: ImageView = findViewById(R.id.orange)
        val yellow: ImageView = findViewById(R.id.yellow)
        val green: ImageView = findViewById(R.id.green)
        val lightBlue: ImageView = findViewById(R.id.lightBlue)
        val blue: ImageView = findViewById(R.id.blue)
        val purple: ImageView = findViewById(R.id.purple)
        val running = true
        val currentColor = 0

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

        startButton.setOnClickListener {
            goalText.visibility = View.GONE
            startButton.visibility = View.GONE
            ezButton.visibility = View.VISIBLE
        }


        ezButton.setOnClickListener {
            while (running == true) {
                //Thread.sleep(1000)
                val currentColor = (0 until 6 + 1).random()
                if (currentColor == 0) {
                    red.visibility = View.VISIBLE
                    orange.visibility = View.INVISIBLE
                    yellow.visibility = View.INVISIBLE
                    green.visibility = View.INVISIBLE
                    lightBlue.visibility = View.INVISIBLE
                    blue.visibility = View.INVISIBLE
                    purple.visibility = View.INVISIBLE
                }
                else if (currentColor == 1) {
                    orange.visibility = View.VISIBLE
                    red.visibility = View.INVISIBLE
                    yellow.visibility = View.INVISIBLE
                    green.visibility = View.INVISIBLE
                    lightBlue.visibility = View.INVISIBLE
                    blue.visibility = View.INVISIBLE
                    purple.visibility = View.INVISIBLE
                }
                else if (currentColor == 2) {
                    yellow.visibility = View.VISIBLE
                    red.visibility = View.INVISIBLE
                    orange.visibility = View.INVISIBLE
                    green.visibility = View.INVISIBLE
                    lightBlue.visibility = View.INVISIBLE
                    blue.visibility = View.INVISIBLE
                    purple.visibility = View.INVISIBLE
                }
                else if (currentColor == 3){
                    green.visibility = View.VISIBLE
                    red.visibility = View.INVISIBLE
                    orange.visibility = View.INVISIBLE
                    yellow.visibility = View.INVISIBLE
                    lightBlue.visibility = View.INVISIBLE
                    blue.visibility = View.INVISIBLE
                    purple.visibility = View.INVISIBLE
                }
                else if (currentColor == 4) {
                    lightBlue.visibility = View.VISIBLE
                    red.visibility = View.INVISIBLE
                    orange.visibility = View.INVISIBLE
                    yellow.visibility = View.INVISIBLE
                    green.visibility = View.INVISIBLE
                    blue.visibility = View.INVISIBLE
                    purple.visibility = View.INVISIBLE
                }
                else if (currentColor == 5) {
                    blue.visibility = View.VISIBLE
                    red.visibility = View.INVISIBLE
                    orange.visibility = View.INVISIBLE
                    yellow.visibility = View.INVISIBLE
                    green.visibility = View.INVISIBLE
                    lightBlue.visibility = View.INVISIBLE
                    purple.visibility = View.INVISIBLE
                }
                else if (currentColor == 6) {
                    purple.visibility = View.VISIBLE
                    red.visibility = View.INVISIBLE
                    orange.visibility = View.INVISIBLE
                    yellow.visibility = View.INVISIBLE
                    green.visibility = View.INVISIBLE
                    lightBlue.visibility = View.INVISIBLE
                    blue.visibility = View.INVISIBLE


                }
            }

            if (currentColor == goal) {
                val winText = Toast.makeText(this, "Вы победили!", Toast.LENGTH_SHORT)
                winText.show()
            }
            else {
                val loseText = Toast.makeText(this, "Вы проиграли!", Toast.LENGTH_SHORT)
                loseText.show()
            }
        }



    }
}