package com.hcunsal.catchgame

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*
import kotlin.collections.ArrayList

class GameActivity : AppCompatActivity() {

    var score = 0
    var dataScore = 0
    var characterArray = ArrayList<ImageView>()
    var runnable = java.lang.Runnable { }
    var handler : Handler = Handler(Looper.getMainLooper())
    var randomArray = arrayOf(7,1,5,4,8,0,2,6,3)
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        sharedPreferences = this.getSharedPreferences("com.hcunsal.catchgame", MODE_PRIVATE)
        dataScore = sharedPreferences.getInt("highscore", -1)

        val intent = intent
        Toast.makeText(applicationContext, "Game Started", Toast.LENGTH_LONG).show()

        if(dataScore != -1) run {
            hScoreText.text = "High Score: $dataScore"
        }



        characterArray.add(character)
        characterArray.add(character1)
        characterArray.add(character2)
        characterArray.add(character3)
        characterArray.add(character4)
        characterArray.add(character5)
        characterArray.add(character6)
        characterArray.add(character7)
        characterArray.add(character8)
        spawner()

        object : CountDownTimer(20500, 1000){
            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "Time: ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                handler.removeCallbacks(runnable)

                if(score > dataScore){
                    dataScore = score
                    sharedPreferences.edit().putInt("highscore", dataScore).apply()
                }

                val alert = AlertDialog.Builder(this@GameActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Are you restart game?")
                alert.setPositiveButton("YES") {dialog, which ->
                    Toast.makeText(applicationContext, "Game Restarted", Toast.LENGTH_LONG).show()
                    finish()
                    startActivity(intent)
                }

                alert.setNegativeButton("NO") {dialog, which ->
                    Toast.makeText(applicationContext, "Game Closed", Toast.LENGTH_LONG).show()
                    finish()
                }

                alert.show()

            }

        }.start()

    }


    fun scoreClick(view : View){
        score = score + 1
        scoreText.text = "Score: $score"
    }


    fun spawner(){
        runnable = object : Runnable{
            override fun run() {

                for (image in characterArray){
                    image.visibility = View.INVISIBLE
                }

                var random = Random()
                var randomIndex = random.nextInt(9)
                // var randomIndex2 = random.nextInt(9)

                characterArray[randomArray[randomIndex]].visibility = View.VISIBLE
                handler.postDelayed(runnable, 350)

            }

        }
        handler.post(runnable)

    }

}