package com.example.myapplication0603

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class SampleDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val buider = AlertDialog.Builder(activity)
        buider.setTitle("注文確認")
        buider.setMessage("選択された注文を完了しますか？")
        buider.setPositiveButton("注文", DialogButtonClickListener())
        buider.setNegativeButton("キャンセル", DialogButtonClickListener())
        val dialog = buider.create()
        return dialog
    }
    private inner class DialogButtonClickListener: DialogInterface.OnClickListener{
        override fun onClick(dialog: DialogInterface?, which: Int){
            var msg = ""
            when(which){
                DialogInterface.BUTTON_POSITIVE ->{
                    msg = "完了"
                    val sharedPreferences = activity?.getSharedPreferences("Data", Context.MODE_PRIVATE)
                    val money = sharedPreferences?.getInt("money", 0)
                    val price = sharedPreferences?.getString("price", null)
                    if (price == "800円"){
                        val newMoney = money!! - 800
                        val editor = sharedPreferences.edit()
                        editor.putInt("money", newMoney)
                        editor.apply()
                        activity?.findViewById<TextView>(R.id.textView)?.text = newMoney.toString() + "円"
                        val intent = Intent(activity, MainActivity::class.java)
                        intent.putExtra("name",sharedPreferences.getString("name",null))
                        startActivity(intent)
                    }

                }

                DialogInterface.BUTTON_NEGATIVE ->{
                    msg = "キャンセル"
                    val sharedPreferences = activity?.getSharedPreferences("Data", Context.MODE_PRIVATE)
                    val editor = sharedPreferences?.edit()
                    editor?.putString("price", null)
                    editor?.putString("name", null)
                }

            }
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        }
    }
}