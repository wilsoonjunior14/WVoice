package com.developer.wilson.voice

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.wilson.voice.modelos.Nota
import kotlinx.android.synthetic.main.note_item.view.*

class NoteListAdapter(context: Context, listNotes: ArrayList<Nota>) : Adapter<NoteListAdapter.NotesHolder>() {

    var listNotes = listNotes
    var context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        var v: View = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        return NotesHolder(v)
    }

    override fun getItemCount(): Int {
        return this.listNotes.size
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        var note = this.listNotes[position]

        holder.titulo.setText(note.getTitulo())
        holder.descricao.setText(note.getDescricao())
        holder.data.setText(note.getData())

    }

    class NotesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titulo    = itemView.noteTitulo
        var descricao = itemView.noteDescription
        var data      = itemView.noteDate
    }

}
