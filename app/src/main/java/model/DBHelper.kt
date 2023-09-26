package model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        companion object {
            private const val DATABASE_VERSION = 1
            private const val DATABASE_NAME = "MyDatabase"
            private const val TABLE_NAME = "MyTable"
            private const val KEY_ID = "_id"
            private const val KEY_NAME = "name"
        }

        override fun onCreate(db: SQLiteDatabase?) {
            val createTableQuery = "CREATE TABLE $TABLE_NAME ($KEY_ID INTEGER PRIMARY KEY, $KEY_NAME TEXT)"
            db?.execSQL(createTableQuery)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }


        fun insertData(name: String): Long {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(KEY_NAME, name)
            return db.insert(TABLE_NAME, null, values)
        }


        @SuppressLint("Range")
        fun getAllData(): ArrayList<String> {
            val data = ArrayList<String>()
            val db = this.readableDatabase
            val query = "SELECT * FROM $TABLE_NAME"
            val cursor: Cursor?
            try {
                cursor = db.rawQuery(query, null)
            } catch (e: SQLException) {
                db.execSQL(query)
                return ArrayList()
            }

            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                    data.add(name)
                } while (cursor.moveToNext())
            }
            cursor.close()
            return data
        }



}
