package com.kudu.sweets_admin.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.kudu.sweets_admin.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        Handler().postDelayed({
            val currentUserID = getCurrentUserId()

            if (currentUserID.isNotEmpty()) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 2500)//TODO: change this if needed
    }


    //get current user id
    private fun getCurrentUserId(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUserId = ""
        //        var currentUserId = "R9i7gu4qsTZjkRfUtiJv7lOQ6nv2"
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }
        return currentUserId
    }
}