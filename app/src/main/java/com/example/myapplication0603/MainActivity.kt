package com.example.myapplication0603

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //リセット
        findViewById<Button>(R.id.button).setOnClickListener {
            val sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
        }

        //選択ボタン表示
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, ChoicesFragment())
            commit()
        }
        //ワークイメージボタン
        findViewById<ImageButton>(R.id.work_image_button).setOnClickListener {
            if(findViewById<TextView>(R.id.mentalValue).text.toString().toInt() >= 10){
                if (findViewById<TextView>(R.id.workText).text == "1"){
                    val toast = Toast.makeText(applicationContext, "すでに仕事に行っています。", Toast.LENGTH_LONG)
                    toast.show()
                }else{
                    val intent = Intent(this, WorkActivity::class.java)
                    startActivity(intent)
                }
            }else{
                val toast = Toast.makeText(applicationContext, "メンタルが足りません", Toast.LENGTH_LONG)
                toast.show()
            }
        }
        //イートイメージボタン
        findViewById<ImageButton>(R.id.eat_image_button).setOnClickListener {
            val sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE)
            val name = sharedPreferences.getString("name", null)
            if(name != null){
                if(findViewById<TextView>(R.id.eatText).text.toString() == "3"){
                    val toast = Toast.makeText(applicationContext, "3食食べています。", Toast.LENGTH_LONG)
                    toast.show()
                }else{
                    val dialogFragment = BentouDialogFragment()
                    dialogFragment.show(supportFragmentManager, "")
                }
            }

        }
        //終了ボタン
        findViewById<Button>(R.id.button2).setOnClickListener {
            val dialogFragment = FinishDialogFragment()
            dialogFragment.show(supportFragmentManager, "")
        }
    }

    override fun onResume() {
        super.onResume()

        val data = getSharedPreferences("Data", Context.MODE_PRIVATE)
        val eatCount = data.getInt("eatCount",0)
        val workCount = data.getInt("workCount",0)
        val day = data.getInt("day",0)
        val progress = data.getInt("progress",0)
        val money = data.getInt("money",0)
        if(eatCount != 0){
            findViewById<TextView>(R.id.eatText).text = eatCount.toString()
        }
        if (workCount != 0){
            findViewById<TextView>(R.id.workText).text = workCount.toString()
        }
        if (day != 0){
            findViewById<TextView>(R.id.day).text = day.toString()
        }else{
            findViewById<TextView>(R.id.day).text = "0"
        }
        if(progress != 0){
            findViewById<ProgressBar>(R.id.progressBar).progress = progress
            findViewById<TextView>(R.id.mentalValue).text = progress.toString()
        }else{
            findViewById<ProgressBar>(R.id.progressBar).progress = 0
            findViewById<TextView>(R.id.mentalValue).text = "0"
        }
        if (money != 0){
            findViewById<TextView>(R.id.money).text = money.toString()
        }
        val editor = data.edit()
        editor.remove("eatCount")
        editor.remove("workCount")
        editor.remove("day")
        editor.remove("progress")
        editor.remove("money")
        editor.apply()

        val work = intent.getIntExtra("work",0)
        if(work == 1){
            findViewById<TextView>(R.id.workText).text = "1"
            findViewById<ProgressBar>(R.id.progressBar).progress -= 10
            findViewById<TextView>(R.id.money).text =
                    (findViewById<TextView>(R.id.money).text.toString().toInt() + 3000).toString()
            findViewById<TextView>(R.id.mentalValue).text =
                    (findViewById<TextView>(R.id.mentalValue).text.toString().toInt() - 10).toString()
            val toast = Toast.makeText(applicationContext, "メンタル値-10", Toast.LENGTH_LONG)
            toast.show()
        }
        val finish = intent.getStringExtra("finish")
        if (finish == "finish"){
            val handler = Handler(Looper.getMainLooper())
            kotlin.concurrent.timer(period = 5000){
                handler.post{
                    val intent = Intent(applicationContext,LastActivity::class.java)
                    startActivity(intent)
                    this.cancel()
                }
            }
        }

    }

    override fun onPause() {
        super.onPause()
        //データを保存
        val data = getSharedPreferences("Data", Context.MODE_PRIVATE)
        val editor = data.edit()
        editor.putInt("progress", findViewById<ProgressBar>(R.id.progressBar).progress)
        editor.putInt("day", findViewById<TextView>(R.id.day).text.toString().toInt())
        editor.putInt("money",findViewById<TextView>(R.id.money).text.toString().toInt())
        editor.putInt("eatCount",findViewById<TextView>(R.id.eatText).text.toString().toInt())
        editor.putInt("workCount",findViewById<TextView>(R.id.workText).text.toString().toInt())
        editor.apply()
    }
}