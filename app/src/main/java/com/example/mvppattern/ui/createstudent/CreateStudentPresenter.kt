package com.example.mvppattern.ui.createstudent

import android.content.ContentValues
import android.content.Context
import com.example.mvppattern.data.repository.DBManager

class CreateStudentPresenter(var callback: CreateStudentContract.View) :
    CreateStudentContract.Presenter {

    lateinit var dbManager: DBManager

    override fun receiveHandlerCreateStudent(context: Context, values: ContentValues) {
        dbManager = DBManager(context)
        if (dbManager.insertStudent(values) > 0) {
            callback.onCreateStudentSuccess()
        } else {
            callback.onCreateStudentFail()
        }
    }
}
