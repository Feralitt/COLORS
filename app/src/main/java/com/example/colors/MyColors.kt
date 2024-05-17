package com.example.colors

import android.graphics.Color

enum class MyColors(val index: Int) {
    RED(0) {
        override fun getColor(): Int = Color.parseColor("#FFFF0000")
        override fun getString(): String = "красный"
    },
    ORANGE(1) {
        override fun getColor(): Int = Color.parseColor("#FFED7014")
        override fun getString(): String = "оранжевый"
    },
    YELLOW(2) {
        override fun getColor(): Int = Color.parseColor("#FFFFFF00")
        override fun getString(): String = "жёлтый"
    },
    GREEN(3) {
        override fun getColor(): Int = Color.parseColor("#FF00FF00")
        override fun getString(): String = "зелёный"
    },
    LIGHT_BLUE(4) {
        override fun getColor(): Int = Color.parseColor("#FF00BFFF")
        override fun getString(): String = "голубой"
    },
    BLUE(5) {
        override fun getColor(): Int = Color.parseColor("#FF0000FF")
        override fun getString(): String = "синий"
    },
    PURPLE(6) {
        override fun getColor(): Int = Color.parseColor("#FF8B00FF")
        override fun getString(): String = "фиолетовый"
    };

    abstract fun getColor(): Int
    abstract fun getString(): String
}