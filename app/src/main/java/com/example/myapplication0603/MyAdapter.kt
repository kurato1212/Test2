package com.example.myapplication0603

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(context: Context?, resource: Int, list: List<ListRow>) :
    ArrayAdapter<ListRow?>(context!!, resource, list) {
    private var mRayoutInflater: LayoutInflater? = null
    private var mList: List<ListRow>
    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup): View {
        val view: View
        if(convertView != null){
            view = convertView
        }else{
            view = mRayoutInflater?.inflate(R.layout.list_layout, null)!!
        }
        val item = mList[i]
        //サムネイル画像
        val thumbnail = view.findViewById<ImageView>(R.id.thumbnail)
        thumbnail.setImageBitmap(item.thumbnail)
        //タイトル
        val title = view.findViewById<TextView>(R.id.title)
        title.text = item.title
        //text
        val text = view.findViewById<TextView>(R.id.textView)
        text.text = item.text
        return view
    }

    init {
        mRayoutInflater = LayoutInflater.from(context)
        mList = list
    }
}