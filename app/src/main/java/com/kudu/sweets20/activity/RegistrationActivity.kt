package com.kudu.sweets20.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.kudu.common.model.User
import com.kudu.common.util.Constants
import com.kudu.sweets20.R
import com.kudu.sweets20.databinding.ActivityRegistrationBinding

@Suppress("DEPRECATION")
class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private val mFireStore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBar()

        //login button
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        //register button
        binding.btnRegister.setOnClickListener {
            registerUser()
        }

    }

    //setting up actionbar with back button
    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbarRegistrationActivity)

        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.back)
        }
        binding.toolbarRegistrationActivity.setNavigationOnClickListener { onBackPressed() }
    }

    //validating inputs
    private fun validateRegistrationDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.etFirstName.text.toString().trim { it <= ' ' }) -> {
//                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                Toast.makeText(this, "Error in First Name", Toast.LENGTH_SHORT).show()
                false
            }
            TextUtils.isEmpty(binding.etLastName.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this, "Error in Last Name", Toast.LENGTH_SHORT).show()
                false
            }
            TextUtils.isEmpty(binding.etEmail.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this, "Error in Email", Toast.LENGTH_SHORT).show()
                false
            }
            TextUtils.isEmpty(binding.etPassword.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this, "Error in Password", Toast.LENGTH_SHORT).show()
                false
            }
            TextUtils.isEmpty(binding.etConfirmPassword.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this, "Error in Confirm Password", Toast.LENGTH_SHORT).show()
                false
            }
            binding.etPassword.text.toString()
                .trim { it <= ' ' } != binding.etConfirmPassword.text.toString()
                .trim { it <= ' ' } -> {
                Toast.makeText(
                    this,
                    "Password and Confirm Password do not match!",
                    Toast.LENGTH_SHORT
                ).show()
                false
            }
            else -> {
                true
            }
        }
    }

    //register user
    private fun registerUser() {
        if (validateRegistrationDetails()) {
            val email: String = binding.etEmail.text.toString().trim { it <= ' ' }
            val password: String = binding.etPassword.text.toString().trim { it <= ' ' }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!

                        val user = User(
                            firebaseUser.uid,
                            binding.etFirstName.text.toString().trim { it <= ' ' },
                            binding.etLastName.text.toString().trim { it <= ' ' },
                            binding.etEmail.text.toString().trim { it <= ' ' }
                        )

                        mFireStore.collection(Constants.USERS)
                            .document(user.id)
                            .set(user, SetOptions.merge())
                            .addOnSuccessListener {
                                Toast.makeText(
                                    this, "Registration Successful", Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener { e ->
                                Log.e(this.javaClass.simpleName, "Error while registering user", e)
                            }
                    } else {
                        Toast.makeText(this, "Error while registering user", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }
    }
}