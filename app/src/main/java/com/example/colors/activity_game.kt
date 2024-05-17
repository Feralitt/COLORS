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

class activity_game : ActivityWithoutBack() {
    private var countColors = 0
    private var goals = mutableListOf<MyColors>()
    private var currentColors = mutableListOf<MyColors>()
    private var delayTime = 0L
    private var lenghtStrike = 0
    private lateinit var startButton: Button
    private var diffName: String? = null

    val running = true
    var startTime: Long = 0
    var endTime: Long = 0
//    var randomLuck = -1

    @SuppressLint("ClickableViewAccessibility", "MissingInflatedId")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        startButton = findViewById(R.id.buttonStart)
        val goalText: TextView = findViewById(R.id.textViewGoal)
        val goalTitleText: TextView = findViewById(R.id.textViewGoalTextHard)
        val colorView: ImageView = findViewById(R.id.color)

        countColors = intent.getIntExtra("count colors", 0)
        diffName = intent.getStringExtra("diffName")
        val colorsIndexes = intent.getIntArrayExtra("colors")
        delayTime = intent.getLongExtra("delay", 0L)

        if (colorsIndexes == null) {
            //генерация цветов для цели
            repeat(countColors) {
                lateinit var newColor: MyColors
                while (true) {
                    newColor = MyColors.entries.toTypedArray().random()
                    if (goals.isEmpty())
                        break
                    if (goals.last() != newColor)
                        break
                }
                goals.add(newColor)
            }
        } else {
            colorsIndexes.map {
                goals.add(MyColors.entries[it])
            }
        }

        // Вывод цветов
        var text = ""
        goals.map {
            if (text.isNotBlank())
                text += '\n'
            text += it.getString()
        }
        goalText.setText(text)


        fun game() = runBlocking {

            GlobalScope.launch(context = Dispatchers.Main) {
                while (running) {
                    delay(delayTime)

                    var newColor = MyColors.entries.toTypedArray().random()
                    while (currentColors.isNotEmpty() && currentColors.last() == newColor)
                        newColor = MyColors.entries.toTypedArray().random()
                    currentColors.add(newColor)
                    if (currentColors.size > countColors)
                        currentColors.removeFirst()
                    // TODO: Реализовать страйк

                    colorView.setBackgroundColor(newColor.getColor())
                    startTime = System.currentTimeMillis()
                }
            }
        }

        startButton.setOnClickListener {
            goalText.visibility = View.GONE
            goalTitleText.visibility = View.GONE
            startButton.visibility = View.GONE
            game()
        }
    }

    override fun onTouchEvent(motionEvent: MotionEvent?): Boolean {
        if (startButton.visibility == View.GONE && motionEvent?.action == MotionEvent.ACTION_DOWN) {
            // код после нажатия
            if (goals.equals(currentColors)) {
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
                if (diffName != null) {
                    val sharedPrefs2 = getSharedPreferences("RecordsPrefs", Context.MODE_PRIVATE)
                    val recordMedium = sharedPrefs2.getLong(diffName, Long.MAX_VALUE)
                    if (reactionTime < recordMedium) {
                        val editor = sharedPrefs2.edit()
                        editor.putLong(diffName, reactionTime)
                        editor.apply()
                    }
                }
            } else {
                // обработка проигрыша
                val loseText = Toast.makeText(this, "Вы проиграли!", Toast.LENGTH_SHORT)
                loseText.show()
            }
            // переход к выбору сложности
            val toActivitySelectDiff = Intent(this, selectDiff::class.java)
            startActivity(toActivitySelectDiff)
            return true // дальше нужная шняга чтобы setOnTouch работал
        }
        return super.onTouchEvent(motionEvent)
    }
}