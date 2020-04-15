package com.example.mvppattern.ui.createstudent

import android.content.ContentValues
import android.content.Context
import com.example.mvppattern.data.repository.DBManager

interface CreateStudentContract {
    interface View {
        fun createStudentSuccess()
        fun createStudentFail()
    }

    interface Presenter {
        fun receiveHandlerCreateStudent(dbManager: DBManager, values: ContentValues)
    }
}
