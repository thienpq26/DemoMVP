package com.example.mvppattern.ui.main.view

import com.example.mvppattern.data.database.model.Student

interface MainView {
    fun showAllStudentSuccess(mList: ArrayList<Student>)
    fun showAllStudentFail()
    fun deleteStudentSuccess(position: Int)
    fun deleteStudentFail()
    fun updateStudentSuccess()
    fun updateStudentFail()
}
