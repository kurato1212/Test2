package com.example.myapplication0603

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class TalkFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_talk, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = activity?.getSharedPreferences("Data", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        view.findViewById<Button>(R.id.praiseButton)?.setOnClickListener {
            editor?.putString("TalkButton", "praiseButton")
            editor?.apply()
            parentFragmentManager.beginTransaction().apply {
                add(R.id.container, PraiseButtonFragment())
                addToBackStack(null)
                commit()
            }
        }
        view.findViewById<Button>(R.id.angryButton)?.setOnClickListener {
            editor?.putString("TalkButton", "angryButton")
            editor?.apply()
            parentFragmentManager.beginTransaction().apply {
                add(R.id.container, PraiseButtonFragment())
                addToBackStack(null)
                commit()
            }
        }
        view.findViewById<Button>(R.id.persuadeButton)?.setOnClickListener {
            editor?.putString("TalkButton", "persuadeButton")
            editor?.apply()
            parentFragmentManager.beginTransaction().apply {
                add(R.id.container, PraiseButtonFragment())
                addToBackStack(null)
                commit()
            }
        }

    }

}