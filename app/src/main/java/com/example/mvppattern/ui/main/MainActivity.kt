package com.example.mvppattern.ui.main

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvppattern.*
import com.example.mvppattern.data.database.model.Student
import com.example.mvppattern.ui.createstudent.CreateStudentActivity
import com.example.mvppattern.ui.main.view.ItemTouchHelperCallback
import com.example.mvppattern.ui.main.view.MainView
import com.example.sqlitedemo1.adapter.StudentsAdapter
import com.example.mvppattern.data.database.DBManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_update_student.*

class MainActivity : AppCompatActivity(), MainView {

    lateinit var arrST: ArrayList<Student>
    lateinit var studentsAdapter: StudentsAdapter
    lateinit var imageStudent: ImageView
    lateinit var mainPresenter: MainPresenter
    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(this)
        mainPresenter.receiveHandlerGetAllStudent(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_student -> {
                val intent = Intent(this, CreateStudentActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setAdapter(mList: ArrayList<Student>) {
        if (recyclerviewStudent.adapter == null) {
            studentsAdapter = StudentsAdapter(mList)
            recyclerviewStudent.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = studentsAdapter
                addOnItemClickListener(object : OnItemClickListener {
                    override fun onItemClick(position: Int, view: View) {
                        showDialogUpdate(position)
                    }
                })
            }
        } else {
            recyclerviewStudent.adapter!!.notifyDataSetChanged()
        }
        addItemTouchCallback(recyclerviewStudent)
    }

    private fun addItemTouchCallback(recyclerView: RecyclerView) {
        val callback: ItemTouchHelper.Callback =
            ItemTouchHelperCallback(object : onItemTouchListenner {
                override fun onSwipe(position: Int) {
                    mainPresenter.receiveHandlerDeleteStudent(
                        this@MainActivity, "${DBManager.ID}=?",
                        arrayOf("${arrST[position].mID}"), position
                    )
                }
            })
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    fun showDialogUpdate(position: Int) {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_update_student)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        imageStudent = dialog.findViewById(R.id.dialogImageStudent)
        val textName: TextView = dialog.dialogTextName
        val textPhone: TextView = dialog.dialogTextPhone
        val textAddress: TextView = dialog.dialogTextAddress
        val textEmail: TextView = dialog.dialogTextEmail
        val buttonClose: Button = dialog.dialogButtonClose
        val buttonUpdate: Button = dialog.dialogButtonUpdate

        textName.text = arrST[position].mName
        textPhone.text = arrST[position].mPhone
        textAddress.text = arrST[position].mAddress
        textEmail.text = arrST[position].mEmail
        imageStudent.setImageBitmap(BitmapUtils.getImage(arrST[position].mImage))

        imageStudent.setOnClickListener {
            requestStoragePermission(this)
        }

        buttonClose.setOnClickListener {
            dialog.dismiss()
        }

        buttonUpdate.setOnClickListener {
            arrST[position].mName = textName.text.toString()
            arrST[position].mPhone = textPhone.text.toString()
            arrST[position].mAddress = textAddress.text.toString()
            arrST[position].mEmail = textEmail.text.toString()
            arrST[position].mImage = BitmapUtils.getBytes(imageStudent.drawable.toBitmap())
            mainPresenter.receiveHandlerUpdateStudent(
                this, setContentValues(arrST[position]), "${DBManager.ID}=?",
                arrayOf("${arrST[position].mID}")
            )
        }
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun showAllStudentSuccess(mList: ArrayList<Student>) {
        arrST = mList
        setAdapter(mList)
    }

    override fun showAllStudentFail() {
        Toast.makeText(this, "Database null", Toast.LENGTH_SHORT).show()
    }

    override fun deleteStudentSuccess(position: Int) {
        studentsAdapter.onSwipeAdapter(position)
        Toast.makeText(
            this@MainActivity,
            "Delete student success!",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun deleteStudentFail() {
        Toast.makeText(
            this@MainActivity,
            "Delete student fail!",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun updateStudentSuccess() {
        dialog.dismiss()
        Toast.makeText(this, "Update student success!", Toast.LENGTH_SHORT).show()
        setAdapter(arrST)
    }

    override fun updateStudentFail() {
        Toast.makeText(
            this@MainActivity,
            "Update student fail!",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 113) {
            imageStudent.setImageURI(data!!.data)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.receiveHandlerCloseDatabase(this)
    }
}
