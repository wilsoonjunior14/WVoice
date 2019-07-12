package com.developer.wilson.voice.modelos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context): SQLiteOpenHelper(context, "WVoice", null, 1) {

    val sqlNota: String = "create table if not exists " +
            "nota(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "titulo VARCHAR(255) NOT NULL," +
            "descricao VARCHAR(5000) NOT NULL," +
            "data DATE)"

    override fun onCreate(db: SQLiteDatabase?) {
        // create table nota
        db!!.execSQL(this.sqlNota)
        println("Table Nota was created successfully")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // drop tables
        db!!.execSQL("DROP TABLE IF EXISTS nota")
        onCreate(db)
    }
}