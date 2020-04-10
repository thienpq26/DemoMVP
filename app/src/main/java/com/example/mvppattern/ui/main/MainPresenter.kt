package com.example.mvppattern.ui.main

import android.content.ContentValues
import android.content.Context
import com.example.mvppattern.data.database.model.Student
import com.example.mvppattern.ui.main.view.MainView

class MainPresenter(var callback: MainView) : OnMainListener {

    lateinit var mainModel: MainModel
    fun receiveHandlerGetAllStudent(context: Context) {
        mainModel = MainModel(this)
        mainModel.handlerGetAllStudent(context)
    }

    fun receiveHandlerDeleteStudent(
        context: Context,
        selection: String?,
        selectionArgs: Array<String>?,
        position: Int
    ) {
        mainModel = MainModel(this)
        mainModel.handlerDeleteStudent(context, selection, selectionArgs, position)
    }

    fun receiveHandlerUpdateStudent(
        context: Context,
        values: ContentValues,
        selection: String?,
        selectionArgs: Array<String>?
    ) {
        mainModel = MainModel(this)
        mainModel.handlerUpdateStudent(context, values, selection, selectionArgs)
    }

    fun receiveHandlerCloseDatabase(context: Context) {
        mainModel = MainModel(this)
        mainModel.handlerCloseDatabase(context)
    }

    override fun onGetAllStudentSuccess(mList: ArrayList<Student>) {
        callback.showAllStudentSuccess(mList)
    }

    override fun onGetAllStudentFail() {
        callback.showAllStudentFail()
    }

    override fun onDeleteStudentSuccess(position: Int) {
        callback.deleteStudentSuccess(position)
    }

    override fun onDeleteStudentFail() {
        callback.deleteStudentFail()
    }

    override fun onUpdateStudentSuccess() {
        callback.updateStudentSuccess()
    }

    override fun onUpdateStudentFail() {
        callback.updateStudentFail()
    }
}
