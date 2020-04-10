package com.example.mvppattern.ui.main

import android.content.ContentValues
import android.content.Context
import com.example.mvppattern.data.database.DBManager

class MainModel(var callback: OnMainListener) {

    lateinit var dbManager: DBManager

    fun handlerGetAllStudent(context: Context) {
        dbManager = DBManager(context)
        if (dbManager.getAllStudents().isNotEmpty()) {
            callback.onGetAllStudentSuccess(dbManager.getAllStudents())
        } else {
            callback.onGetAllStudentFail()
        }
    }

    fun handlerDeleteStudent(
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

    fun handlerUpdateStudent(
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

    fun handlerCloseDatabase(
        context: Context
    ) {
        dbManager = DBManager(context)
        dbManager.close()
    }
}
