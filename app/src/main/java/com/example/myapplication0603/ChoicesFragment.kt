package com.example.myapplication0603

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ChoicesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choices, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.talkButton)?.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.container,TalkFragment())
                commit()
            }
        }
        view.findViewById<Button>(R.id.eatButton).setOnClickListener {
            val intent = Intent(activity, ListViewFood::class.java)
            startActivity(intent)
        }
        view.findViewById<Button>(R.id.gameButton).setOnClickListener {
            val eatCount = activity?.findViewById<TextView>(R.id.eatText)?.text.toString()
            val workCount = activity?.findViewById<TextView>(R.id.workText)?.text.toString()
            if(eatCount == "0" && workCount == "0"){
                val dialogFragment = GameDialogFragment()
                dialogFragment.show(parentFragmentManager, "")
            }else{
                val toast = Toast.makeText(activity, "食事か仕事に行っています。", Toast.LENGTH_LONG)
                toast.show()
            }
        }



    }

}