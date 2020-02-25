package com.example.myappeventos.event

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myappeventos.R
import com.example.myappeventos.data.CallPost
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CallDialog {
    fun callDialogCheckin(
        eventCheckin: FloatingActionButton,
        context: Context?,
        mView: View,
        eventId: String
    ) {
        eventCheckin.setOnClickListener(View.OnClickListener {
            val mBuilder = AlertDialog.Builder(context)
            val mEmail = mView.findViewById<View>(R.id.userEmail) as EditText
            val mName = mView.findViewById<View>(R.id.userName) as EditText
            val mCheckin: Button = mView.findViewById<View>(R.id.btnLogin) as Button
            mBuilder.setView(mView)
            val dialog: AlertDialog = mBuilder.create()
            if (mView.getParent() != null) {
                (mView.getParent() as ViewGroup).removeView(mView) // <- fix
            }
            dialog.show()
            mCheckin.setOnClickListener(View.OnClickListener {
                if (!mEmail.text.toString().isEmpty() && !mName.text.toString().isEmpty()) {
                    val createCheckin = CallPost()
                    Log.d("eventId", eventId)
                    Log.d("mEmail", mEmail.text.toString())
                    Log.d("mName", mName.text.toString())
                    createCheckin.createCheckin(eventId,mEmail.text.toString(),mName.text.toString())
                    Toast.makeText(
                        context!!,
                        R.string.success_login_msg,
                        Toast.LENGTH_SHORT
                    ).show()
                    dialog.dismiss()
                } else {
                    Toast.makeText(
                        context,
                        R.string.error_login_msg,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        })
    }
}