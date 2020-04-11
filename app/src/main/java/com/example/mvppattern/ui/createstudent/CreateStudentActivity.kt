package com.example.mvppattern.ui.createstudent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.example.mvppattern.util.requestStoragePermission
import com.example.mvppattern.util.BitmapUtils
import com.example.mvppattern.R
import com.example.mvppattern.data.Student
import com.example.mvppattern.util.setContentValues
import com.example.mvppattern.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_create_student.*

class CreateStudentActivity : AppCompatActivity(), CreateStudentContract.View {

    lateinit var createStudentPresenter: CreateStudentPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_student)
        createStudentPresenter = CreateStudentPresenter(this)

        buttonReset.setOnClickListener {
            resetEditText()
        }

        imageViewStudent.setOnClickListener {
            requestStoragePermission(this)
        }

        buttonCreate.setOnClickListener {
            createStudentPresenter.receiveHandlerCreateStudent(
                this,
                setContentValues(getEditText())
            )
        }
    }

    private fun resetEditText() {
        editTextName.text = null
        editTextPhone.text = null
        editTextAddress.text = null
        editTextEmail.text = null
        imageViewStudent.setImageResource(R.drawable.photo)
    }

    private fun getEditText(): Student {
        return Student(
            editTextName.text.toString(),
            editTextPhone.text.toString(),
            editTextAddress.text.toString(),
            editTextEmail.text.toString(),
            BitmapUtils.getBytes(imageViewStudent.drawable.toBitmap())
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 113) {
            imageViewStudent.setImageURI(data!!.data)
        }
    }

    override fun onCreateStudentSuccess() {
        Toast.makeText(this, "Insert student success !", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onCreateStudentFail() {
        Toast.makeText(this, "Insert student fail !", Toast.LENGTH_SHORT).show()
    }
}
