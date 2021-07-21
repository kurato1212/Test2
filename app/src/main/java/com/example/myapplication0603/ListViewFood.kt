package com.example.myapplication0603

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ListViewFood : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_food)

        val data = getSharedPreferences("Data", Context.MODE_PRIVATE)
        val money = data.getInt("money", 0)
        findViewById<TextView>(R.id.textView).text = money.toString() + "円"

        val lvmenu = findViewById<ListView>(R.id.lv_menu)
        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        var menu = mutableMapOf("name" to "唐揚げ定食", "price" to "800円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to "800円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "生姜焼き定食", "price" to "800円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ステーキ定食", "price" to "1000円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "野菜炒め定食", "price" to "700円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "とんかつ定食", "price" to "900円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ミンチかつ定食", "price" to "800円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "チキンカツ定食", "price" to "900円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "コロッケ定食", "price" to "800円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "焼き魚定食", "price" to "800円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "焼肉定食", "price" to "800円")
        menuList.add(menu)

        val from = arrayOf("name", "price")
        val to = intArrayOf(android.R.id.text1,android.R.id.text2)
        val adapter = SimpleAdapter(applicationContext, menuList, android.R.layout.simple_list_item_2,from, to)
        lvmenu.adapter = adapter

        findViewById<ListView>(R.id.lv_menu).onItemClickListener = ListItemClickListener()
    }
    private inner class ListItemClickListener: AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val price = view?.findViewById<TextView>(android.R.id.text2)?.text.toString()
            val name = view?.findViewById<TextView>(android.R.id.text1)?.text.toString()
            val data = getSharedPreferences("Data", Context.MODE_PRIVATE)
            val money = data.getInt("money", 0)
            val editor = data.edit()
            editor.putString("price", price)
            editor.putString("name", name)
            editor.apply()
            if(price == "800円"){
                if(money > 800){
                    val dialogFragment = SampleDialogFragment()
                    dialogFragment.show(supportFragmentManager, "")
                }
            }

        }
    }
}