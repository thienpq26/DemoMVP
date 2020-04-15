package com.example.mvppattern.ui.main

import android.content.ContentValues
import android.content.Context
import com.example.mvppattern.data.repository.DBManager

class MainPresenter(var callback: MainContract.View) : MainContract.Presenter {

    override fun receiveHandlerGetAllStudent(dbManager: DBManager) {
        if (dbManager.getAllStudents().isNotEmpty()) {
            callback.showAllStudentSuccess(dbManager.getAllStudents())
        } else {
            callback.showAllStudentFail()
        }
    }

    override fun receiveHandlerDeleteStudent(
        dbManager: DBManager,
        selection: String?,
        selectionArgs: Array<String>?,
        position: Int
    ) {
        if (dbManager.deleteStudent(selection, selectionArgs) > 0) {
            callback.deleteStudentSuccess(position)
        } else {
            callback.deleteStudentFail()
        }
    }

    override fun receiveHandlerUpdateStudent(
        dbManager: DBManager,
        values: ContentValues,
        selection: String?,
        selectionArgs: Array<String>?
    ) {
        if (dbManager.updateStudent(values, selection, selectionArgs) > 0) {
            callback.updateStudentSuccess()
        } else {
            callback.updateStudentFail()
        }
    }

    override fun receiveHandlerCloseDatabase(dbManager: DBManager) {
        dbManager.close()
    }
}
