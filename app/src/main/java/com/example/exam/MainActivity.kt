package com.example.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.exam.databinding.ActivityMainBinding
import model.DBHelper

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DBHelper(this)
        val insertedId = dbHelper.insertData("John Doe")

// To retrieve data
        val data = dbHelper.getAllData()
        for (item in data) {
            Log.d("MyApp", "Name: $item")
        }

    }
}