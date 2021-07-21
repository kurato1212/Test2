package com.example.myapplication0603

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import java.util.*

class PraiseButtonFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_praise_button, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = activity?.getSharedPreferences("Data", Context.MODE_PRIVATE)
        val talkButton = sharedPreferences?.getString("TalkButton", null)
        if (talkButton == "praiseButton"){
            val talkList = listOf("頑張ってるよ", "元気出して", "まだ方法はあるよ", "これからいいことあるよ")
            val index: Int = Random().nextInt(talkList.size) // ランダムに選択された 0 以上 4 未満の整数
            view.findViewById<Button>(R.id.choiceButton1).text = talkList.get(index)
            view.findViewById<Button>(R.id.choiceButton2).text = talkList.get((index + 1)% 4)
            view.findViewById<Button>(R.id.choiceButton3).text = talkList.get((index + 2)% 4)
        }
        if (talkButton == "angryButton"){
            val talkList = listOf("働けばか", "くずが", "なにしてんだ", "動けよ")
            val index: Int = Random().nextInt(talkList.size) // ランダムに選択された 0 以上 4 未満の整数
            view.findViewById<Button>(R.id.choiceButton1).text = talkList.get(index)
            view.findViewById<Button>(R.id.choiceButton2).text = talkList.get((index + 1)% 4)
            view.findViewById<Button>(R.id.choiceButton3).text = talkList.get((index + 2)% 4)
        }
        if (talkButton == "persuadeButton"){
            val talkList = listOf("働かないと何も始まらないよ", "趣味を見つけよう", "稼げば何かおこるかも", "まずは外に出よう")
            val index: Int = Random().nextInt(talkList.size) // ランダムに選択された 0 以上 4 未満の整数
            view.findViewById<Button>(R.id.choiceButton1).text = talkList.get(index)
            view.findViewById<Button>(R.id.choiceButton2).text = talkList.get((index + 1)% 4)
            view.findViewById<Button>(R.id.choiceButton3).text = talkList.get((index + 2)% 4)
        }

    }

}