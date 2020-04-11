package com.example.mvppattern.ui.main

import android.content.ContentValues
import android.content.Context
import com.example.mvppattern.data.Student

interface MainContract {
    interface View {
        fun onShowAllStudentSuccess(mList: ArrayList<Student>)
        fun onShowAllStudentFail()
        fun onDeleteStudentSuccess(position: Int)
        fun onDeleteStudentFail()
        fun onUpdateStudentSuccess()
        fun onUpdateStudentFail()
    }

    interface Presenter {
        fun receiveHandlerGetAllStudent(context: Context)
        fun receiveHandlerDeleteStudent(
            context: Context,
            selection: String?,
            selectionArgs: Array<String>?,
            position: Int
        )

        fun receiveHandlerUpdateStudent(
            context: Context,
            values: ContentValues,
            selection: String?,
            selectionArgs: Array<String>?
        )

        fun receiveHandlerCloseDatabase(context: Context)
    }
}
