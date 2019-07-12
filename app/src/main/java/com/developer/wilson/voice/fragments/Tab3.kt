package com.developer.wilson.voice.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.wilson.voice.NoteListAdapter
import com.developer.wilson.voice.R
import com.developer.wilson.voice.modelos.Nota
import kotlinx.android.synthetic.main.note_item.view.*
import kotlinx.android.synthetic.main.tab3.view.*

class Tab3: Fragment() {

    var nota = Nota(0, "", "", "")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v: View = inflater.inflate(R.layout.tab3, container, false);

        this.inicializaLista(v)

        v.updateList.setOnClickListener{
            inicializaLista(v)
        }

        return v
    }

    fun inicializaLista(v: View){
        var findNotes = nota.find(v.context)

        v.recyclerView.adapter = NoteListAdapter(v.context, findNotes as ArrayList<Nota>)
        v.recyclerView.layoutManager = GridLayoutManager(v.context, 1, RecyclerView.VERTICAL, false)
        v.recyclerView.setHasFixedSize(true)

        println("QUANTITY NOTES FOUND: "+findNotes.size)
    }

}