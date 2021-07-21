package com.example.myapplication0603

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.timer

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val handler = Handler(Looper.getMainLooper())
        timer(period = 500){
            handler.post {
                if (findViewById<ProgressBar>(R.id.loadingBar).progress < 100) {
                    findViewById<ProgressBar>(R.id.loadingBar).progress += 10

                }else{
                    val data = getSharedPreferences("Data", MODE_PRIVATE)
                    val progress = data.getInt("progress",0)
                    val day = data.getInt("day",0)
                    val editor = data.edit()
                    val newProgress = progress + 15
                    editor.putInt("progress",newProgress)
                    editor.putInt("day",day + 1)
                    editor.remove("eatCount")
                    editor.remove("workCount")
                    editor.apply()
                    val intent = Intent(this@GameActivity, MainActivity::class.java)
                    if(newProgress >= 100){
                        intent.putExtra("finish","finish")
                    }
                    startActivity(intent)
                    this.cancel()
                }
            }
        }
    }
}