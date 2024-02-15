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
import kotlinx.coroutines.*


class activity_easy_game : ActivityWithoutBack() {
    var currentColor = -1
    val running = true
    var goal = 0
    var startTime: Long = 0
    var endTime: Long = 0

    @SuppressLint("ClickableViewAccessibility")
    @OptIn(DelicateCoroutinesApi::class)
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

        // tooltip  0 - red, 1 - orange, 2 - yellow, 3 - green, 4 - light_blue, 5 - blue, 6 - purple
        // последнее число в random это не включительно
        goal = (0 until 6 + 1).random()
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



        fun game() = runBlocking {

            GlobalScope.launch(context = Dispatchers.Main) {
                while (running) {
                    delay(1000)
                    currentColor = (0 until 6 + 1).random()
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
            goalText.visibility = View.GONE
            startButton.visibility = View.GONE
            ezButton.visibility = View.VISIBLE
            game()
        }

        ezButton.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                // код после нажатия
                if (currentColor == goal) {
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
                    val sharedPrefs = getSharedPreferences("RecordsPrefs", Context.MODE_PRIVATE)
                    val recordEz = sharedPrefs.getLong("recordEz", Long.MAX_VALUE)
                    if (reactionTime < recordEz) {
                        val editor = sharedPrefs.edit()
                        editor.putLong("recordEz", reactionTime)
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