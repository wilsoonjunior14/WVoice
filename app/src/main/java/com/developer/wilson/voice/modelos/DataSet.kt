package com.developer.wilson.voice.modelos

import android.content.Context
import android.database.sqlite.SQLiteDatabase

interface DataSet {

    fun save(context: Context, objeto: Object) : Boolean

    fun delete(context: Context, database: SQLiteDatabase, objeto: Object) : Boolean

    fun update(context: Context, database: SQLiteDatabase, objeto: Object) : Boolean

}