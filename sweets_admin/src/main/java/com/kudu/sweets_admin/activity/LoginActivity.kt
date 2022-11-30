package com.kudu.sweets_admin.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kudu.sweets_admin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginAdmin.setOnClickListener {
            //TODO: check authentication here before logging into MainActivity
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}