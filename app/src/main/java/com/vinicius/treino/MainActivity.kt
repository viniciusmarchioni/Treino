package com.vinicius.treino

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vinicius.treino.adapter.AdapterList

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
        val createView = findViewById<RelativeLayout>(R.id.createView)
        val newTrainingName = findViewById<EditText>(R.id.newName)
        val newButton = findViewById<Button>(R.id.newButton)
        val listaDeTreino = mutableListOf<Treino>()
        val adapter = AdapterList(this, listaDeTreino)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter



        appbar.title = "Bom dia"

        add.setOnClickListener {
            createView.isVisible = true;
            it.isClickable = false;
        }
        createView.setOnClickListener {
            add.isClickable = true
            it.isVisible = false
            newTrainingName.text.clear()
        }

        newButton.setOnClickListener {

            if (newTrainingName.text.length > 5) {
                createView.isVisible = false
                add.isClickable = true
                val x = Treino(newTrainingName.text.toString(), mutableListOf())

                Log.d("TAG", x.getExercicios().size.toString())

                listaDeTreino.add(x)
                adapter.notifyItemInserted(listaDeTreino.size)
                newTrainingName.text.clear()

            } else {
                Log.d("TAG", "Erro")
            }


        }


    }
}