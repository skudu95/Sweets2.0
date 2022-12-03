package com.kudu.common.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap

object Constants {
    const val USERS: String = "users"
    const val LOGGED_IN_USERNAME: String = "logged_in_username"
    const val EXTRA_USER_DETAILS: String = "extra_user_details"
    const val READ_STORAGE_PERMISSION_CODE = 2
    const val PICK_IMAGE_REQUEST_CODE = 1
    const val FIRST_NAME: String = "firstName"
    const val LAST_NAME: String = "lastName"
    const val MOBILE: String = "mobile"
    const val IMAGE: String = "image"
    const val PRODUCT_IMAGE: String = "Product_Image"
    const val PRODUCTS: String = "products"
    const val USER_ID: String = "user_id"
    const val EXTRA_PRODUCT_ID: String = "extra_product_id"
    const val DEFAULT_CART_QUANTITY: String = "1"
    const val CART_ITEMS: String = "cart_items"
    const val PRODUCT_ID: String = "product_id"
    const val CART_QUANTITY: String = "cart_quantity"
    const val ORDERS: String = "orders"
    const val STOCK_QUANTITY: String = "stock_quantity"
    const val EXTRA_MY_ORDER_DETAILS: String = "extra_my_order_details"
    const val SOLD_PRODUCTS: String = "sold_products"
    const val EXTRA_SOLD_PRODUCT_DETAILS: String = "extra_sold_product_details"

    fun showImageChooser(activity: Activity) {
        //an intent for launching the image selection of phone storage
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        //launches the image selection of phone storage using the constant code
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    fun getFileExtension(activity: Activity, uri: Uri?): String? {
        /*
        MimeTypeMap: Two-way map that maps MIME-types to file extensions and vice versa.
        getSingleton(): get the singleton instance of MimeTypeMap
        getExtensionFromMimeType: Return the registered extension for the given MIME type.
        contentResolver.getType: return the MIME type of the given content URL
        */
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }
}