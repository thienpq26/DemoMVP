package com.example.mvppattern.ui.main

import android.content.ContentValues
import android.content.Context
import com.example.mvppattern.data.Student
import com.example.mvppattern.data.repository.DBManager

interface MainContract {
    interface View {
        fun showAllStudentSuccess(mList: ArrayList<Student>)
        fun showAllStudentFail()
        fun deleteStudentSuccess(position: Int)
        fun deleteStudentFail()
        fun updateStudentSuccess()
        fun updateStudentFail()
    }

    interface Presenter {
        fun receiveHandlerGetAllStudent(dbManager: DBManager)
        fun receiveHandlerDeleteStudent(
            dbManager: DBManager,
            selection: String?,
            selectionArgs: Array<String>?,
            position: Int
        )

        fun receiveHandlerUpdateStudent(
            dbManager: DBManager,
            values: ContentValues,
            selection: String?,
            selectionArgs: Array<String>?
        )

        fun receiveHandlerCloseDatabase(dbManager: DBManager)
    }
}
