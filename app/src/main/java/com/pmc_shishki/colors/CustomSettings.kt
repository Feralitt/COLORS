package com.pmc_shishki.colors

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

class CustomSettings : ActivityWithoutBack() {
    private lateinit var seekBar: SeekBar
    private lateinit var textViewTime: TextView
    private lateinit var buttonPlayCustomDiff: Button
    private lateinit var buttonColorPrototipe: Button
    private lateinit var layoutColors: LinearLayout
    private lateinit var layoutSelectedColors: LinearLayout
    private lateinit var colorsScroll: ScrollView

    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private var delay: Long = 0L
    private var colors: MutableList<Int> = mutableListOf()

    private val DELAY_STEP = 5

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_settings)

        sharedPrefs = getSharedPreferences("RecordsPrefs", Context.MODE_PRIVATE)
        editor = sharedPrefs.edit()
        delay = sharedPrefs.getLong("custom_delay", 500L)
        colors = getColors()

        seekBar = findViewById(R.id.seekBar)
        seekBar.progress = delay.toInt() / DELAY_STEP
        textViewTime = findViewById(R.id.textViewTime)
        buttonPlayCustomDiff = findViewById(R.id.buttonPlayCustomDiff)
        layoutColors = findViewById(R.id.layoutColors)
        buttonColorPrototipe = findViewById(R.id.buttonColorPrototipe)
        layoutSelectedColors = findViewById(R.id.layoutSelectedColors)
        colorsScroll = findViewById(R.id.colorsScroll)

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
            if (colors.isEmpty()) {
                Toast
                    .makeText(this, getString(R.string.errorCustomSettings), Toast.LENGTH_SHORT)
                    .show()
            } else if (colors.size > 3) {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder
                    .setMessage(getString(R.string.customSettingsAlert))
                    .setNegativeButton(getString(R.string.clear_records_yes)) { _, _ -> toCustomGame() }
                    .setPositiveButton(getString(R.string.clear_records_no)) { _, _ ->}
                    .show()
            } else toCustomGame()
        }
        updateDelay()
        generateColorButtons()
        updateSelectedColors()
    }

    private fun toCustomGame(){
        val toCustomGame = Intent(this, ActivityGame::class.java)
        toCustomGame.putExtra("count colors", colors.size)
        toCustomGame.putExtra("delay", delay)
        toCustomGame.putExtra("colors", colors.toIntArray())
        startActivity(toCustomGame)
    }

    private fun updateDelay() {
        delay = seekBar.progress.toLong() * DELAY_STEP
        textViewTime.text = getString(R.string.custom_settings_time).format(delay / 1000.0)
    }

    private fun generateColorButtons() {
        MyColors.entries.map {
            val newButton = ButtonColor(this)
            newButton.layoutParams = buttonColorPrototipe.layoutParams
            newButton.color = it
            layoutColors.addView(newButton)
            newButton.setOnClickListener {
                colors.add((it as ButtonColor).color!!.index)
                updateSelectedColors()
            }
        }
        layoutColors.removeView(buttonColorPrototipe)
        buttonColorPrototipe.visibility = View.INVISIBLE
    }

    private fun updateSelectedColors() {
        // Удаление повторяющихся цветов
        layoutSelectedColors.removeAllViews()
        for (i in colors.size - 1 downTo 1) {
            if (colors[i] == colors[i - 1]) {
                colors.removeAt(i)
            }
        }
        saveColors()

        colors.map {
            val newText = TextView(this)
            newText.text = MyColors.entries[it].getString() + " \uD83D\uDDD1"
            newText.textSize = 23F
            newText.textAlignment = View.TEXT_ALIGNMENT_CENTER
            layoutSelectedColors.addView(newText)
            newText.setOnClickListener {
                val index = layoutSelectedColors.indexOfChild(it)
                colors.removeAt(index)
                layoutSelectedColors.removeViewAt(index)
                updateSelectedColors()
            }
        }
        colorsScroll.fullScroll(View.FOCUS_DOWN)
        colorsScroll.scrollBy(0, 100)
    }

    private fun getColors(): MutableList<Int> {
        try {
            return sharedPrefs
                .getString("custom_colors", "")!!
                .split(',')
                .map { it.toInt() }
                .toMutableList()
        } catch (e: Exception) {
            return mutableListOf()
        }
    }

    private fun saveColors() {
        val colorsString = StringBuilder()
        for (i in colors.indices) {
            if (i != 0) {
                colorsString.append(',')
            }
            colorsString.append(colors[i])
        }
        editor.putString("custom_colors", colorsString.toString())
        editor.apply()
    }
}