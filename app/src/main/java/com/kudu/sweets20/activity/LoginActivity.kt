package com.kudu.sweets20.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kudu.common.model.User
import com.kudu.common.util.Constants
import com.kudu.sweets20.R
import com.kudu.sweets20.databinding.ActivityLoginBinding

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val mFireStore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBar()

        //login button
        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        //register button
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

    //setting up actionbar with back button
    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbarLoginActivity)

        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.back)
        }
        binding.toolbarLoginActivity.setNavigationOnClickListener { onBackPressed() }
    }

    //validate login details
    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.etEmail.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this, "Please enter email ID", Toast.LENGTH_SHORT).show()
                false
            }
            TextUtils.isEmpty(binding.etPassword.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                false
            }
            else -> {
                true
            }
        }
    }

    //current user id
    private fun getCurrentUserId(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserId = ""
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }
        return currentUserId
    }

    //login user
    private fun loginUser() {
        if (validateLoginDetails()) {
            val email: String = binding.etEmail.text.toString().trim { it <= ' ' }
            val password: String = binding.etPassword.text.toString().trim { it <= ' ' }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        mFireStore.collection(Constants.USERS)
                            .document(getCurrentUserId())
                            .get()
                            .addOnSuccessListener { document ->
                                Log.i(this.javaClass.simpleName, document.toString())

                                val user = document.toObject(User::class.java)!!

                                val sharedPrefs = this.getSharedPreferences(
                                    Constants.SWEETS_APP_PREFERENCES,
                                    MODE_PRIVATE
                                )

                                val editor: SharedPreferences.Editor = sharedPrefs.edit()
                                editor.putString(
                                    Constants.LOGGED_IN_USERNAME,
                                    "${user.firstName} ${user.lastName}"
                                )
                                editor.apply()
                                userLoggedInSuccess(user)
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(
                                    this,
                                    "Error while getting user details",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.e(
                                    this.javaClass.simpleName,
                                    "Error while getting user details",
                                    e
                                )
                            }
                    } else {
                        Log.e(this.javaClass.simpleName, task.exception!!.message.toString())
                        Toast.makeText(this, "Error while logging in user", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }

    }

    //user logged in successfully
    private fun userLoggedInSuccess(user: User) {
        Log.i("First Name: ", user.firstName)
        Log.i("Last Name: ", user.lastName)
        Log.i("Email: ", user.email)

        startActivity(Intent(this, HomeActivity::class.java))

        finish()
    }
}