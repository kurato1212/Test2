package com.example.myapplication0603

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class WorkDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val buider = AlertDialog.Builder(activity)
        buider.setTitle("選択された仕事に行かせますか？")
        buider.setMessage("仕事は1日一回しか行けません")
        buider.setPositiveButton("行かせる", DialogButtonClickListener())
        buider.setNegativeButton("キャンセル", DialogButtonClickListener())
        val dialog = buider.create()
        return dialog
    }
    private inner class DialogButtonClickListener: DialogInterface.OnClickListener{
        override fun onClick(dialog: DialogInterface?, which: Int){
            when(which){
                DialogInterface.BUTTON_POSITIVE ->{
                    val data = activity?.getSharedPreferences("Data", Context.MODE_PRIVATE)
                    val salary = data?.getString("salary",null)
                    val intent = Intent(activity,MainActivity::class.java)
                    if (salary == "3000円"){
                        intent.putExtra("work",1)
                    }
                    startActivity(intent)


                }
                DialogInterface.BUTTON_NEGATIVE ->{

                }
            }

        }
    }
}