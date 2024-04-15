package com.vinicius.treino

class Treino(
    private var nome: String,
    private var exercicios: MutableList<Exercicio>
) {
    fun getNome(): String {
        return nome
    }

    fun setNome(nome: String) {
        this.nome = nome
    }

    fun getExercicios(): MutableList<Exercicio> {
        return exercicios
    }

    fun setExercicio(exercicio: MutableList<Exercicio>) {
        this.exercicios = exercicio
    }
}
