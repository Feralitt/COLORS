package com.example.colors


import androidx.appcompat.app.AppCompatActivity
open class ActivityWithoutBack: AppCompatActivity() {
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        //super.onBackPressed()
    }
}