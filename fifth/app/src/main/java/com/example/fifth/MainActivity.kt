package com.example.fifth

import android.app.Activity
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private fun alert(message: String) {
        AlertDialog.Builder(this as Activity).setMessage(message).setPositiveButton("Ok", object: DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, p1: Int) {
                dialog?.cancel()
            }
        }).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onRestart() {
        super.onRestart()
        alert("onRestart")
    }

    override fun onStart() {
        super.onStart()
        alert("onStart")
    }

    override fun onResume() {
        super.onResume()
        alert("onResume")
    }

    override fun onPause() {
        super.onPause()
        alert("onPause")
    }

    override fun onStop() {
        super.onStop()
        alert("onStop")
    }
}