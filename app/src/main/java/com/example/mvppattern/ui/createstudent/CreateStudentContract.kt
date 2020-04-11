package com.example.mvppattern.ui.createstudent

import android.content.ContentValues
import android.content.Context

interface CreateStudentContract {
    interface View {
        fun onCreateStudentSuccess()
        fun onCreateStudentFail()
    }

    interface Presenter {
        fun receiveHandlerCreateStudent(context: Context, values: ContentValues)
    }
}
