package com.example.myapplication0603

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class WorkActivity : AppCompatActivity() {
    // リストに表示するデータ
    val listItems = ArrayList<ListRow>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)
        val image = listOf(
                R.drawable.cleaning,
                R.drawable.construction,
                R.drawable.factory
        )
        val listTitle = listOf(
                "清掃員",
                "工事員",
                "工場員"
        )
        val listText = listOf(
                "2000円",
                "3000円",
                "4000円"
        )
        for ((index,elem) in image.withIndex()){
            val bmp = BitmapFactory.decodeResource(resources, elem)
            val item = ListRow(bmp, listTitle[index],listText[index])
            listItems.add(item)
        }
        // MyAdapterを作成し、データを設定
        val adapter = MyAdapter(this, R.layout.list_layout, listItems)
        // ListViewにArrayAdapterを関連付け、データの表示を行う
        val listView = findViewById<View>(R.id.listView1) as ListView
        listView.adapter = adapter

        listView.onItemClickListener = ListItemClickListener()
    }
    private inner class ListItemClickListener: AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val target = listItems[position]
            val data = getSharedPreferences("Data",Context.MODE_PRIVATE)
            val editor = data.edit()
            editor.putString("salary",target.text)
            editor.apply()
            val dialogFragment = WorkDialogFragment()
            dialogFragment.show(supportFragmentManager,"")
        }
    }
}