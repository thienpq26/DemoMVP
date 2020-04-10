package com.example.mvppattern.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.mvppattern.data.database.DBManager

class StudentsProvider : ContentProvider() {

    lateinit var uriMatcher: UriMatcher
    lateinit var dbManager: DBManager
    lateinit var cursor: Cursor

    override fun onCreate(): Boolean {
        uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        uriMatcher.addURI(AUTHORITY, TABLE_NAME, 1)
        dbManager = DBManager(context!!)
        return true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return dbManager.updateStudent(values, selection, selectionArgs)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return dbManager.deleteStudent(selection, selectionArgs)
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        dbManager.insertStudent(values)
        return null
    }


    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        cursor = dbManager.queryStudents(
            projection,
            selection,
            selectionArgs,
            sortOrder
        )
        return cursor
    }

    companion object {
        const val AUTHORITY = "com.example.mvppattern.Provider.StudentsProvider"
        const val TABLE_NAME = "students"
    }
}
