package com.example.myapplication0603

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class GameDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("ゲーム")
        builder.setMessage("ゲームをすると1日が経過しますが良いですか？")
        builder.setPositiveButton("やる", DialogButtonClickListener())
        builder.setNegativeButton("キャンセル", DialogButtonClickListener())
        val dialog = builder.create()
        return dialog
    }
    private inner class DialogButtonClickListener: DialogInterface.OnClickListener{
        override fun onClick(dialog: DialogInterface?, which: Int) {
            when(which){
                DialogInterface.BUTTON_POSITIVE -> {
                    val intent = Intent(activity, GameActivity::class.java)
                    startActivity(intent)
                }
                DialogInterface.BUTTON_NEGATIVE -> {

                }
            }
        }
    }
}