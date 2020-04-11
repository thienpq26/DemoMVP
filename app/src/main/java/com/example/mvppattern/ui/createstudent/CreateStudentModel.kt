package com.example.mvppattern.ui.createstudent

import android.content.ContentValues
import android.content.Context
import com.example.mvppattern.data.database.DBManager

class CreateStudentModel(var callback: OnCreateStudentListener) {

    lateinit var dbManager: DBManager
    fun handlerCreateStudent(context: Context, values: ContentValues) {
        dbManager = DBManager(context)
        if (dbManager.insertStudent(values) > 0) {
            callback.onCreateStudentSuccess()
        } else {
            callback.onCreateStudentFail()
        }
    }
}
