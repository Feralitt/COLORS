package com.example.colors

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class activity_hard_game : ActivityWithoutBack() {
    var currentColor = -1
    var previousColor = -1
    val running = true
    var generation = true
    var goal1 = 0
    var goal2 = 0
    var startTime: Long = 0
    var endTime: Long = 0
    var randomLuck = -1

    @SuppressLint("ClickableViewAccessibility", "MissingInflatedId")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hard_game)

        val startButton: Button = findViewById(R.id.buttonStart)
        val goal1Text: TextView = findViewById(R.id.textViewGoal1Hard)
        val goal2Text: TextView = findViewById(R.id.textViewGoal2Hard)
        val goalText: TextView = findViewById(R.id.textViewGoalTextHard)
        val ezButton: Button = findViewById(R.id.easyButton)
        val red: ImageView = findViewById(R.id.red)
        val orange: ImageView = findViewById(R.id.orange)
        val yellow: ImageView = findViewById(R.id.yellow)
        val green: ImageView = findViewById(R.id.green)
        val lightBlue: ImageView = findViewById(R.id.lightBlue)
        val blue: ImageView = findViewById(R.id.blue)
        val purple: ImageView = findViewById(R.id.purple)


        // tooltip  0 - red, 1 - orange, 2 - yellow, 3 - green, 4 - light_blue, 5 - blue, 6 - purple
        // последнее число в random это не включительно


        while (generation) {
            goal1 = (0 until 6 + 1).random() //генерация цветов для цели
            goal2 = (0 until 6 + 1).random()
            if (goal1 != goal2) {
                generation = false
            }
        }

        if (goal1 == 0)
            goal1Text.text = "красный"
        else if (goal1 == 1)
            goal1Text.text = "оранжевый"
        else if (goal1 == 2)
            goal1Text.text = "жёлтый"
        else if (goal1 == 3)
            goal1Text.text = "зелёный"
        else if (goal1 == 4)
            goal1Text.text = "голубой"
        else if (goal1 == 5)
            goal1Text.text = "синий"
        else if (goal1 == 6)
            goal1Text.text = "фиолетовый"

        if (goal2 == 0)
            goal2Text.text = "красный"
        else if (goal2 == 1)
            goal2Text.text = "оранжевый"
        else if (goal2 == 2)
            goal2Text.text = "жёлтый"
        else if (goal2 == 3)
            goal2Text.text = "зелёный"
        else if (goal2 == 4)
            goal2Text.text = "голубой"
        else if (goal2 == 5)
            goal2Text.text = "синий"
        else if (goal2 == 6)
            goal2Text.text = "фиолетовый"

        fun game() = runBlocking {

            GlobalScope.launch(context = Dispatchers.Main) {
                while (running) {
                    delay(500)
                    previousColor = currentColor

                    if (previousColor == goal1) {
                        randomLuck = (0 until 9 + 1).random()
                        if (randomLuck < 3) {
                            currentColor = goal2
                        } else {currentColor = (0 until 6 + 1).random()}
                    } else {currentColor = (0 until 6 + 1).random()}


                    red.visibility = View.INVISIBLE
                    orange.visibility = View.INVISIBLE
                    yellow.visibility = View.INVISIBLE
                    green.visibility = View.INVISIBLE
                    lightBlue.visibility = View.INVISIBLE
                    blue.visibility = View.INVISIBLE
                    purple.visibility = View.INVISIBLE
                    if (currentColor == 0) {
                        red.setImageResource(R.drawable.red)
                        red.visibility = View.VISIBLE
                    } else if (currentColor == 1) {
                        orange.setImageResource(R.drawable.orange)
                        orange.visibility = View.VISIBLE
                    } else if (currentColor == 2) {
                        yellow.setImageResource(R.drawable.yellow)
                        yellow.visibility = View.VISIBLE
                    } else if (currentColor == 3) {
                        green.setImageResource(R.drawable.green)
                        green.visibility = View.VISIBLE
                    } else if (currentColor == 4) {
                        lightBlue.setImageResource(R.drawable.light_blue)
                        lightBlue.visibility = View.VISIBLE
                    } else if (currentColor == 5) {
                        blue.setImageResource(R.drawable.blue)
                        blue.visibility = View.VISIBLE
                    } else if (currentColor == 6) {
                        purple.setImageResource(R.drawable.purple)
                        purple.visibility = View.VISIBLE
                    }

                    startTime = System.currentTimeMillis()
                }
            }
        }

        startButton.setOnClickListener {
            goal1Text.visibility = View.GONE
            goal2Text.visibility = View.GONE
            goalText.visibility = View.GONE
            startButton.visibility = View.GONE
            ezButton.visibility = View.VISIBLE
            game()
        }

        ezButton.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                // код после нажатия
                if (currentColor == goal2 && previousColor == goal1) {
                    endTime = System.currentTimeMillis()
                    val reactionTime = endTime - startTime
                    val winText = Toast.makeText(this, "Вы победили!", Toast.LENGTH_SHORT)
                    winText.show()
                    // обработка времени реакции
                    val reactionTimeString = when {
                        reactionTime < 10 -> "0,00$reactionTime сек"
                        reactionTime < 100 -> "0,0$reactionTime сек"
                        else -> "0,$reactionTime сек"
                    }
                    val reactionShow = Toast.makeText(this, "время реакции $reactionTimeString", Toast.LENGTH_SHORT)
                    reactionShow.show()
                    // сохранение рекорда
                    val sharedPrefs3 = getSharedPreferences("RecordsPrefs3", Context.MODE_PRIVATE)
                    val recordHard = sharedPrefs3.getLong("recordMedium", Long.MAX_VALUE)
                    if (reactionTime < recordHard) {
                        val editor = sharedPrefs3.edit()
                        editor.putLong("recordHard", reactionTime)
                        editor.apply()
                    }
                    // переход к выбору сложности
                    val toActivitySelectDiff = Intent(this, selectDiff::class.java)
                    startActivity(toActivitySelectDiff)
                } else {
                    // обработка проигрыша
                    val loseText = Toast.makeText(this, "Вы проиграли!", Toast.LENGTH_SHORT)
                    loseText.show()
                    // переход к выбору сложности
                    val toActivitySelectDiff = Intent(this, selectDiff::class.java)
                    startActivity(toActivitySelectDiff)
                }
                true // дальше нужная шняга чтобы setOnTouch работал
            } else {
                false
            }
        }
    }
}