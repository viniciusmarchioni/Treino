package com.vinicius.treino

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vinicius.treino.adapter.AdapterExercicio
import com.vinicius.treino.adapter.OnItemClickListener

class TreinoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_treino)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val appbar = findViewById<Toolbar>(R.id.appbar)

        val listadeExercicios = mutableListOf<Exercicio>()
        val adapter = AdapterExercicio(this,listadeExercicios)
        val add = findViewById<View>(R.id.addTraining)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter





        val db = openOrCreateDatabase("DB_Treinos", MODE_PRIVATE, null)
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("TAG",listadeExercicios[position].getNome())
            }

        })

        appbar.title = intent.getStringExtra("name")

        try {
            db.execSQL("Create Table if not exists ${appbar.title}(nome varchar(20),peso real,descr text,yturl text)")

            val cursor = db.rawQuery("Select nome,peso,descr,yturl from ${appbar.title}", null)

            val inome = cursor.getColumnIndex("nome")
            val ipeso = cursor.getColumnIndex("qnt")
            val idescr = cursor.getColumnIndex("qnt")
            val iyturl = cursor.getColumnIndex("qnt")
            cursor.moveToFirst()

            while (cursor != null) {
                val nome = cursor.getString(inome)
                val peso = cursor.getDouble(ipeso)
                val descr = cursor.getString(idescr)
                val yturl = cursor.getString(iyturl)

                listadeExercicios.add(Exercicio(nome,peso,descr,yturl))


                cursor.moveToNext()
            }

            adapter.notifyDataSetChanged()


        } catch (e: Exception) {
            Log.d("TAG", "ERRO $e")
        }


        add.setOnClickListener {
            val ex = Exercicio("Puxada alta",(40).toDouble(),"barra","yt.com/puxada")
            try {
                //db.execSQL("Insert into ${appbar.title}(nome,peso,descr,yturl) VALUES ('${ex.getNome()}',${ex.getPeso()},'${ex.getDesc()}','${ex.getYoutubeUrl()}')")


                val cursor = db.rawQuery("Select qnt from Treinos where nome = \"${appbar.title}\"", null)
                cursor.moveToFirst()
                val qnt = cursor.getInt(0)
                Log.d("TAG","-----------------------------------------$qnt ------------------------------")

                cursor.close()

                                db.execSQL("UPDATE Treinos SET qnt = ${(qnt+1).toDouble()} where nome = '${appbar.title}'")




            } catch (e: Exception) {

                Log.d("TAG", "ERRO INSERÇÃO: $e")

            }
            listadeExercicios.add(ex)
            adapter.notifyItemInserted(listadeExercicios.size)
        }




    }
}