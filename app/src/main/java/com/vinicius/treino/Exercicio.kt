package com.vinicius.treino

class Exercicio(
    private var nome: String,
    private var peso: Double,
    private var desc: String,
    private var youtubeUrl: String
) {
    fun getNome(): String {
        return nome
    }

    fun setNome(nome: String) {
        this.nome = nome
    }

    fun getPeso(): Double {
        return peso
    }

    fun setPeso(peso: Double) {
        this.peso = peso
    }

    fun getDesc(): String {
        return desc
    }

    fun setDesc(desc: String) {
        this.desc = desc
    }

    fun getYoutubeUrl(): String {
        return youtubeUrl
    }

    fun setYoutubeUrl(youtubeUrl: String) {
        this.youtubeUrl = youtubeUrl
    }
}
