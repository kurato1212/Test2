package com.example.myapplication0603

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class BentouDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val sharedPreferences = activity?.getSharedPreferences("Data", Context.MODE_PRIVATE)
        val name = sharedPreferences?.getString("name", null)
        val buider = AlertDialog.Builder(activity)
        buider.setTitle(name)
        buider.setMessage("選択された弁当をたべますか？")
        buider.setPositiveButton("食べる", DialogButtonClickListener())
        buider.setNegativeButton("キャンセル", DialogButtonClickListener())
        val dialog = buider.create()
        return dialog
    }
    private inner class DialogButtonClickListener: DialogInterface.OnClickListener{
        override fun onClick(dialog: DialogInterface?, which: Int) {
            when(which){
                DialogInterface.BUTTON_POSITIVE -> {
                    val sharedPreferences = activity?.getSharedPreferences("Data", Context.MODE_PRIVATE)
                    val editor = sharedPreferences?.edit()
                    editor?.putString("name", null)
                    editor?.apply()
                    val price = sharedPreferences?.getString("price", null)
                    if (price == "800円"){
                        val toast = Toast.makeText(context, "メンタル値+5", Toast.LENGTH_LONG)
                        toast.show()
                        val bar = activity?.findViewById<ProgressBar>(R.id.progressBar)
                        bar?.progress = bar?.progress?.plus(5)!!
                        var text = activity?.findViewById<TextView>(R.id.eatText)?.text
                        val intText = Integer.parseInt(text.toString())
                        activity?.findViewById<TextView>(R.id.eatText)?.text = (intText + 1).toString()
                        val mental = activity?.findViewById<TextView>(R.id.mentalValue)?.text
                        val intMental = (mental.toString()).toInt()
                        activity?.findViewById<TextView>(R.id.mentalValue)?.text = (intMental + 5).toString()

                    }
                }
                DialogInterface.BUTTON_NEGATIVE -> {

                }
            }
        }
    }
}