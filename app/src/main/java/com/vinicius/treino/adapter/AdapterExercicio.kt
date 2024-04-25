package com.vinicius.treino.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vinicius.treino.Exercicio
import com.vinicius.treino.R

class AdapterExercicio(private val context: Context, private val exercicios:MutableList<Exercicio>):RecyclerView.Adapter<AdapterExercicio.ExViewHolder>() {


    private var mListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExViewHolder {
        val itemLista = LayoutInflater.from(context)
            .inflate(R.layout.activity_treino_list_placeholder, parent, false)

        return ExViewHolder(itemLista)
    }

    override fun getItemCount(): Int = exercicios.size

    override fun onBindViewHolder(holder: ExViewHolder, position: Int) {
        holder.nome.text = exercicios[position].getNome()
        holder.qnt.text = exercicios[position].getPeso().toString()

        holder.itemView.setOnClickListener {
            mListener?.onItemClick(position)
        }


    }

    inner class ExViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome = itemView.findViewById<TextView>(R.id.trainingText)
        val qnt = itemView.findViewById<TextView>(R.id.trainingQnt)
    }



}