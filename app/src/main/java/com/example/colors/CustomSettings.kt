package com.example.colors

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet.Layout

class CustomSettings : ActivityWithoutBack() {
    private lateinit var seekBar: SeekBar
    private lateinit var textViewTime: TextView
    private lateinit var buttonPlayCustomDiff: Button
    private lateinit var buttonColorPrototipe: Button
    private lateinit var layoutColors: LinearLayout
    private lateinit var layoutSelectedColors: LinearLayout

    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor


    private var delay: Long = 0L
    private var colors: MutableList<Int> = mutableListOf(0, 1, 0)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_settings)

        sharedPrefs = getSharedPreferences("RecordsPrefs", Context.MODE_PRIVATE)
        editor = sharedPrefs.edit()
        delay = sharedPrefs.getLong("custom_delay", 500L)
        seekBar = findViewById(R.id.seekBar)
        seekBar.progress = delay.toInt()
        textViewTime = findViewById(R.id.textViewTime)
        buttonPlayCustomDiff = findViewById(R.id.buttonPlayCustomDiff)
        layoutColors = findViewById(R.id.layoutColors)
        buttonColorPrototipe = findViewById(R.id.buttonColorPrototipe)
        layoutSelectedColors = findViewById(R.id.layoutSelectedColors)

        val buttonBack: Button = findViewById(R.id.buttonBackCustomDiff)
        buttonBack.setOnClickListener {
            val toSelectDiff = Intent (this, SelectDiff::class.java)
            startActivity(toSelectDiff)
        }

        seekBar.setOnSeekBarChangeListener (object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateDelay()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                editor.putLong("custom_delay", delay)
                editor.apply()
            }
        })
        buttonPlayCustomDiff.setOnClickListener {
            val toCustomGame = Intent(this, ActivityGame::class.java)
            toCustomGame.putExtra("count colors", colors.size)
            toCustomGame.putExtra("delay", delay)
            toCustomGame.putExtra("colors", colors.toIntArray())
            startActivity(toCustomGame)
        }
        updateDelay()
        generateColorButtons()
        updateSelectedColors()
    }

    private fun updateDelay() {
        delay = seekBar.progress.toLong()
        textViewTime.text = getString(R.string.custom_settings_time).format(delay / 1000.0)
    }

    private fun generateColorButtons() {
        MyColors.entries.map {
            val newButton = Button(this)
            newButton.layoutParams = buttonColorPrototipe.layoutParams
            newButton.setBackgroundColor(it.getColor())
            layoutColors.addView(newButton)
        }
        layoutColors.removeView(buttonColorPrototipe)
        buttonColorPrototipe.visibility = View.INVISIBLE
    }

    private fun updateSelectedColors() {
        layoutSelectedColors.removeAllViews()
        colors.map {
            val newText = TextView(this)
            newText.text = MyColors.entries[it].getString() + " \uD83D\uDDD1"
            newText.textSize = 23F
            newText.textAlignment = View.TEXT_ALIGNMENT_CENTER
            layoutSelectedColors.addView(newText)
        }
    }
}