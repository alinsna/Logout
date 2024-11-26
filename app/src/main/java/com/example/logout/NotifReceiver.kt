package com.example.logout

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotifReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.getStringExtra("ACTION")
        if (context != null) {
            when (action) {
                "READ" -> {
                    val msg = intent.getStringExtra("MESSAGE")
                    if (msg != null) {
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
                }
                "LOGOUT" -> {
                    val prefManager = PrefManager.getInstance(context)
                    prefManager.setLoggedIn(false)
                    prefManager.clear()

                    val logoutIntent = Intent(context, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    context.startActivity(logoutIntent)
                }
            }
        }
    }
}
