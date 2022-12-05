package com.kudu.sweets_admin.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kudu.common.model.Products
import com.kudu.common.util.Constants
import com.kudu.common.util.GlideLoader
import com.kudu.sweets_admin.R
import com.kudu.sweets_admin.databinding.ActivityAddProductBinding
import java.io.IOException

@Suppress("DEPRECATION")
class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    private val mFireStore = FirebaseFirestore.getInstance()
    private var mSelectedImageFileUri: Uri? = null
    private var mProductImageUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBar()

        //add image button
        binding.ivAddProduct.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Constants.showImageChooser(this)
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    Constants.READ_STORAGE_PERMISSION_CODE
                )
            }
        }

        //submit button
        binding.btnSubmitAP.setOnClickListener {
            if (validateProductDetails()) {
                uploadProductImage()
            }
        }
    }

    //setting up actionbar with back button
    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbarAddProductActivity)

        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.back)
        }
        binding.toolbarAddProductActivity.setNavigationOnClickListener { onBackPressed() }
    }

    //upload product image
    private fun uploadProductImage() {
        /*  val productRef: StorageReference = FirebaseStorage.getInstance().reference.child(
              Constants.PRODUCT_IMAGE + System.currentTimeMillis() + "." + Constants.getFileExtension(
                  this, mSelectedImageFileUri
              )
          )*/
        val productRef: StorageReference =
            FirebaseStorage.getInstance().reference.child(
                "product-images/${Constants.PRODUCT_IMAGE}-${System.currentTimeMillis()}.${
                    Constants.getFileExtension(
                        this,
                        mSelectedImageFileUri
                    )
                }"
            )
        productRef.putFile(mSelectedImageFileUri!!).addOnSuccessListener { taskSnapshot ->
            Log.e(
                "Product Image URL", taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
            )
            taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { uri ->
                Log.e("Downloadable Image URL", uri.toString())
                mProductImageUrl = uri.toString()
                uploadProductDetails()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(
                this,
                "Something went wrong while uploading product image. Please try again...",
                Toast.LENGTH_SHORT
            ).show()
            Log.e(this.javaClass.simpleName, exception.message, exception)
        }
    }

    //upload product details
    private fun uploadProductDetails() {
        val username =
            this.getSharedPreferences(Constants.SWEETS_APP_ADMIN_PREFERENCES, MODE_PRIVATE)
                .getString(Constants.LOGGED_IN_USERNAME, "")!!

        val product = Products(
            user_id = getCurrentUserId(),
            user_name = username,
            title = binding.etProductTitle.text.toString().trim { it <= ' ' },
            price = binding.etProductPrice.text.toString().trim { it <= ' ' },
            category = "",
            description = binding.etProductDescription.text.toString().trim { it <= ' ' },
            stock_quantity = binding.etProductQuantity.text.toString().trim { it <= ' ' },
            image = mProductImageUrl,
        )

        mFireStore.collection(Constants.PRODUCTS)
            .document()
            .set(product, SetOptions.merge())
            .addOnSuccessListener {
                Toast.makeText(this, "Product Upload Successful", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    this,
                    "Something went wrong while uploading product...",
                    Toast.LENGTH_SHORT
                )
                    .show()
                Log.e(this.javaClass.simpleName, "Error uploading product details...", e)
            }
    }

    //validate product details
    private fun validateProductDetails(): Boolean {
        return when {
            mSelectedImageFileUri == null -> {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show()
                false
            }
            TextUtils.isEmpty(binding.etProductTitle.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this, "Please enter product title", Toast.LENGTH_SHORT).show()
                false
            }
            TextUtils.isEmpty(binding.etProductPrice.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this, "Please enter product price", Toast.LENGTH_SHORT).show()
                false
            }
            TextUtils.isEmpty(binding.etProductDescription.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this, "Please enter product description", Toast.LENGTH_SHORT).show()
                false
            }
            TextUtils.isEmpty(binding.etProductQuantity.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(this, "Please enter product quantity", Toast.LENGTH_SHORT).show()
                false
            }
            else -> {
                true
            }
        }
    }

    //get current user id
    private fun getCurrentUserId(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUserId = ""
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }
        return currentUserId
    }

    //request permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE) {
            //if permission granted
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                showErrorSnackBar("The storage permission is granted.", false)
                Constants.showImageChooser(this@AddProductActivity)
            } else {
                //displaying another toast if permission not granted
                Toast.makeText(this, "Read Storage Permission Denied", Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.PICK_IMAGE_REQUEST_CODE) {
                if (data != null) {
                    binding.ivAddProduct.setImageDrawable(
                        ContextCompat.getDrawable(this, R.drawable.edit)
                    )
                    mSelectedImageFileUri = data.data!!
                    try {
                        GlideLoader(this).loadUserPicture(
                            mSelectedImageFileUri!!, binding.ivProductImage
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Toast.makeText(this, "Image Selection Failed", Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.e("Request Cancelled", "Image selection cancelled")
        }
    }

}