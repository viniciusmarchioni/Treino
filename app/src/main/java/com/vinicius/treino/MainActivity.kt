package com.vinicius.treino

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val appbar = findViewById<Toolbar>(R.id.appbar)
        val add = findViewById<View>(R.id.addTraining)


        appbar.title = "Bom dia"

        add.setOnClickListener {
            Log.d("TAG","OIIIIIIIIIIIII")
        }






    }
}