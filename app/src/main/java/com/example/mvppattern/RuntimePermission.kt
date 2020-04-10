package com.example.mvppattern

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.provider.MediaStore
import android.widget.Toast
import com.example.mvppattern.data.database.model.Student
import com.example.mvppattern.data.database.DBManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

fun requestStoragePermission(activity: Activity) {
    Dexter.withActivity(activity).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        .withListener(object : PermissionListener {
            override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                openGallery(activity)
            }

            override fun onPermissionRationaleShouldBeShown(
                permission: PermissionRequest?,
                token: PermissionToken?
            ) {
                token!!.continuePermissionRequest()
            }

            override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                Toast.makeText(
                    activity,
                    "Check permission stogare",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }).check()
}

private fun openGallery(activity: Activity) {
    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    activity.startActivityForResult(intent, 113)
}

fun setContentValues(student: Student): ContentValues {
    return ContentValues().apply {
        put(DBManager.NAME, student.mName)
        put(DBManager.PHONE, student.mPhone)
        put(DBManager.ADDRESS, student.mAddress)
        put(DBManager.EMAIL, student.mEmail)
        put(DBManager.IMAGE, student.mImage)
    }
}
