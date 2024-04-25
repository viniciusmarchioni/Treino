package com.vinicius.treino

import android.content.Intent
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
import com.vinicius.treino.adapter.OnItemClickListener
import java.util.Objects

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

        adapter.setOnItemClickListener(object : OnItemClickListener{

            val intent: Intent = Intent(this@MainActivity, TreinoActivity::class.java)


            override fun onItemClick(position: Int) {
                intent.putExtra("name",listaDeTreino[position].getNome())
                startActivity(intent)
            }

        })


        try {
            val db = openOrCreateDatabase("DB_Treinos", MODE_PRIVATE, null)
            db.execSQL("Create Table if not exists Treinos(nome varchar(20),qnt INT)")

            val cursor = db.rawQuery("Select nome,qnt from Treinos", null)

            val inome = cursor.getColumnIndex("nome")
            val iqnt = cursor.getColumnIndex("qnt")
            cursor.moveToFirst()

            while (cursor != null) {
                val nome = cursor.getString(inome)
                val qnt = cursor.getInt(iqnt)

                if (qnt > 0) {

                    try {
                        val consulta = "Select nome,peso,descr,yturl from $nome"
                        val cursorExercicio = db.rawQuery(consulta, null)

                        val inomeEx = cursor.getColumnIndex("nome")
                        val ipeso = cursor.getColumnIndex("peso")
                        val idesc = cursor.getColumnIndex("descr")
                        val iyturl = cursor.getColumnIndex("yturl")

                        val listHelper = mutableListOf<Exercicio>()

                        while (cursorExercicio != null) {
                            val nomeEx = cursor.getString(inomeEx)
                            val peso = cursor.getDouble(ipeso)
                            val desc = cursor.getString(idesc)
                            val ytUrl = cursor.getString(iyturl)
                            listHelper.add(Exercicio(nomeEx, peso, desc, ytUrl))
                            cursor.moveToNext()
                        }

                        listaDeTreino.add(Treino(nome, listHelper))


                    } catch (_: Exception) {

                    }

                } else {

                    listaDeTreino.add(Treino(nome, mutableListOf()))

                }
                cursor.moveToNext()
            }



            adapter.notifyDataSetChanged()


        } catch (e: Exception) {
            Log.d("TAG", "ERRO: $e")
        }





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

                try {

                    val db = openOrCreateDatabase("DB_Treinos", MODE_PRIVATE, null)
                    db.execSQL("Insert into Treinos(nome,qnt) VALUES ('${newTrainingName.text}',0)")

                } catch (_: Exception) {

                    Log.d("TAG", "ERRO INSERÇÂO")

                }

                listaDeTreino.add(x)
                adapter.notifyItemInserted(listaDeTreino.size)
                newTrainingName.text.clear()

            } else {
                Log.d("TAG", "Erro")
            }


        }


    }
}