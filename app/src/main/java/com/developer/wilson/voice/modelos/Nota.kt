package com.developer.wilson.voice.modelos

class Nota(desc: String, titulo: String, data: String) {

    private var descricao: String = desc
    private var titulo: String = titulo
    private var data: String = data

    fun getDescricao(): String{
        return this.descricao
    }

    fun setDescricao(desc: String){
        this.descricao = desc
    }

    fun setTitulo(tit: String){
        this.titulo = tit
    }

    fun setData(data: String){
        this.data = data
    }

}