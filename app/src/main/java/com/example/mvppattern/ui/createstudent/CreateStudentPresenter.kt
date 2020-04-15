package com.example.mvppattern.ui.createstudent

import android.content.ContentValues
import android.content.Context
import com.example.mvppattern.data.repository.DBManager

class CreateStudentPresenter(private val view: CreateStudentContract.View) :
    CreateStudentContract.Presenter {

    override fun receiveHandlerCreateStudent(dbManager: DBManager, values: ContentValues) {
        if (dbManager.insertStudent(values) > 0) {
            view.createStudentSuccess()
        } else {
            view.createStudentFail()
        }
    }
}
