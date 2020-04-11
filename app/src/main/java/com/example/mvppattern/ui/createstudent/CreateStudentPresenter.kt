package com.example.mvppattern.ui.createstudent

import android.content.ContentValues
import android.content.Context

class CreateStudentPresenter(var callback: CreateStudentView) :
    OnCreateStudentListener {

    lateinit var createStudentModel: CreateStudentModel
    fun receiveHandlerCreateStudent(context: Context, values: ContentValues) {
        createStudentModel = CreateStudentModel(this)
        createStudentModel.handlerCreateStudent(context, values)
    }

    override fun onCreateStudentSuccess() {
        callback.createStudentSuccess()
    }

    override fun onCreateStudentFail() {
        callback.createStudentFail()
    }
}
