package com.hcunsal.catchgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun exit(view : View){
        finish()
    }

    fun play(view: View){
        val intent = Intent(applicationContext, GameActivity :: class.java)
        startActivity(intent)
    }
}