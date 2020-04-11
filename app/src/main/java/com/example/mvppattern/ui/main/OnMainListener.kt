package com.example.mvppattern.ui.main

import com.example.mvppattern.data.database.model.Student

interface OnMainListener {
    fun onGetAllStudentSuccess(mList: ArrayList<Student>)
    fun onGetAllStudentFail()
    fun onDeleteStudentSuccess(position: Int)
    fun onDeleteStudentFail()
    fun onUpdateStudentSuccess()
    fun onUpdateStudentFail()
}
