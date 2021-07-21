package com.example.myapplication0603

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class FinishDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("終了")
        builder.setMessage("1日が終了しますが、良いですか？")
        builder.setPositiveButton("終了", DialogButtonClickListener())
        builder.setNegativeButton("キャンセル", DialogButtonClickListener())
        val dialog = builder.create()
        return dialog
    }
    private inner class DialogButtonClickListener: DialogInterface.OnClickListener{
        override fun onClick(dialog: DialogInterface?, which: Int) {
            when(which){
                DialogInterface.BUTTON_POSITIVE -> {
                    activity?.findViewById<TextView>(R.id.eatText)?.text = "0"
                    activity?.findViewById<TextView>(R.id.workText)?.text = "0"
                    activity?.findViewById<TextView>(R.id.day)?.text =
                            (activity?.findViewById<TextView>(R.id.day)?.text.toString().toInt() + 1).toString()
                }
                DialogInterface.BUTTON_NEGATIVE -> {

                }
            }
        }
    }
}