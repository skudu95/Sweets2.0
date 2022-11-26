package com.kudu.sweets20.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kudu.sweets20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            Toast.makeText(this, "Register Activity", Toast.LENGTH_SHORT).show()
        }

        binding.btnSignIn.setOnClickListener {
            Toast.makeText(this, "Login Activity", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnDribble.setOnClickListener {
            Toast.makeText(this, "Dribble", Toast.LENGTH_SHORT).show()
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://dribbble.com/")))
        }
        binding.btnInstagram.setOnClickListener {
            Toast.makeText(this, "Instagram", Toast.LENGTH_SHORT).show()
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/")))
        }
        binding.btnFacebook.setOnClickListener {
            Toast.makeText(this, "Dribble", Toast.LENGTH_SHORT).show()
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/")))
        }
        binding.btnTwitter.setOnClickListener {
            Toast.makeText(this, "Dribble", Toast.LENGTH_SHORT).show()
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/")))
        }
    }
}