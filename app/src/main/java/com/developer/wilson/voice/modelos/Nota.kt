package com.developer.wilson.voice.modelos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast

class Nota(id: Int, desc: String, titulo: String, data: String): DataSet {

    private var id: Int = id
    private var descricao: String = desc
    private var titulo: String = titulo
    private var data: String = data

    fun getId(): Int{
        return this.id
    }

    fun setId(id: Int){
        this.id = id
    }

    fun getDescricao(): String{
        return this.descricao
    }

    fun getTitulo(): String{
        return this.titulo
    }

    fun getData() : String{
        return this.data
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

    fun find(context: Context) : List<Nota>{
        var listaNotas = ArrayList<Nota>()
        try{
            var sqlite = Database(context).readableDatabase
            val cursorquery = sqlite.rawQuery("select id, titulo, descricao, data from nota", null)
            cursorquery.moveToFirst()

            println("searching for notes ...")
            println("QUANTITY OF NOTES FOUND: "+cursorquery.count)
            if (cursorquery.count > 0) {
                var i: Int = 0
                while (i < cursorquery.count){
                    cursorquery.moveToPosition(i)

                    println("ID DO ELEMENTO: "+cursorquery.getInt(0))
                    var nota = Nota(cursorquery.getInt(0),
                        cursorquery.getString(1),
                        cursorquery.getString(2),
                        cursorquery.getString(3))

                    listaNotas.add(nota)

                    i++
                }
            }
            println("notes were searched successfully ...")


        }catch (e: Exception){
            println("Error was generated when search for notes")
            System.err.println(e.printStackTrace())
            Toast.makeText(context, "Erro ao buscar notas salvas", Toast.LENGTH_LONG).show()
        }

        return listaNotas
    }

    override fun save(context: Context, objeto: Object) : Boolean {
        try{
            var nota = objeto as Nota
            var sqlite = Database(context).writableDatabase

            var cv = ContentValues()
            cv.put("titulo", nota.titulo)
            cv.put("descricao", nota.descricao)
            cv.put("data", nota.data)

            val insert = sqlite.insert("nota", null, cv)
            if (insert != (-1).toLong()){
                Toast.makeText(context, "Nota salva com sucesso!", Toast.LENGTH_LONG).show()
                return true
            }else{
                Toast.makeText(context, "Erro ao salvar a nota!", Toast.LENGTH_LONG).show()
                return false
            }

        }catch (e: Exception){
            Toast.makeText(context, "Erro ao salvar a nota", Toast.LENGTH_LONG).show()
            println(e.localizedMessage)
            return false
        }

        return true
    }

    override fun delete(context: Context, database: SQLiteDatabase, objeto: Object) : Boolean {
        return true
    }

    override fun update(context: Context, database: SQLiteDatabase, objeto: Object) : Boolean {
        return true
    }

}