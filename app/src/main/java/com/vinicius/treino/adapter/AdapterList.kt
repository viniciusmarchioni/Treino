package com.vinicius.treino.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vinicius.treino.R
import com.vinicius.treino.Treino

class AdapterList(private val context: Context,private val treinos:MutableList<Treino>) :
    RecyclerView.Adapter<AdapterList.TreinoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreinoViewHolder {
        val itemLista = LayoutInflater.from(context)
            .inflate(R.layout.activity_treino_list_placeholder, parent, false)
        return TreinoViewHolder(itemLista)
    }

    override fun getItemCount(): Int = treinos.size

    override fun onBindViewHolder(holder: TreinoViewHolder, position: Int) {
        holder.nome.text = treinos[position].getNome()
        holder.qnt.text = treinos[position].getExercicios().size.toString()
    }


    inner class TreinoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome = itemView.findViewById<TextView>(R.id.trainingText)
        val qnt = itemView.findViewById<TextView>(R.id.trainingQnt)
    }


}