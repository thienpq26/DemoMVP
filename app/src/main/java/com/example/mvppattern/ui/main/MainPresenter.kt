package com.example.mvppattern.ui.main

import android.content.ContentValues
import android.content.Context
import com.example.mvppattern.data.repository.DBManager

class MainPresenter(var callback: MainContract.View) : MainContract.Presenter {

    lateinit var dbManager: DBManager
    override fun receiveHandlerGetAllStudent(context: Context) {
        dbManager = DBManager(context)
        if (dbManager.getAllStudents().isNotEmpty()) {
            callback.onShowAllStudentSuccess(dbManager.getAllStudents())
        } else {
            callback.onShowAllStudentFail()
        }
    }

    override fun receiveHandlerDeleteStudent(
        context: Context,
        selection: String?,
        selectionArgs: Array<String>?,
        position: Int
    ) {
        dbManager = DBManager(context)
        if (dbManager.deleteStudent(selection, selectionArgs) > 0) {
            callback.onDeleteStudentSuccess(position)
        } else {
            callback.onDeleteStudentFail()
        }
    }

    override fun receiveHandlerUpdateStudent(
        context: Context,
        values: ContentValues,
        selection: String?,
        selectionArgs: Array<String>?
    ) {
        dbManager = DBManager(context)
        if (dbManager.updateStudent(values, selection, selectionArgs) > 0) {
            callback.onUpdateStudentSuccess()
        } else {
            callback.onUpdateStudentFail()
        }
    }

    override fun receiveHandlerCloseDatabase(context: Context) {
        dbManager = DBManager(context)
        dbManager.close()
    }
}
