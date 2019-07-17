package com.developer.wilson.voice

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.speech.tts.TextToSpeech
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.wilson.voice.modelos.Nota
import com.developer.wilson.voice.utils.config
import kotlinx.android.synthetic.main.note_item.view.*
import org.w3c.dom.Text
import java.util.*
import android.media.AudioManager



class NoteListAdapter(context: Context, listNotes: ArrayList<Nota>) : Adapter<NoteListAdapter.NotesHolder>(), TextToSpeech.OnInitListener {
    override fun onInit(status: Int) {
        print("Initialize the speaker notes")
    }

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

        holder.titulo.setText("Título: "+note.getTitulo())
        holder.descricao.setText("Descrição: "+note.getDescricao())
        holder.data.setText("Data: "+ config().convertDataNormal(note.getData()))

        holder.btnPlay.setOnClickListener{
            holder.reproduceAudio()
        }
    }

    class NotesHolder(itemView: View) : RecyclerView.ViewHolder(itemView), TextToSpeech.OnInitListener {
        override fun onInit(status: Int) {
            System.err.println("Initializing speaker audio")
        }

        var titulo    = itemView.noteTitulo
        var descricao = itemView.noteDescription
        var data      = itemView.noteDate
        var btnPlay   = itemView.btnPlay
        var tts       = TextToSpeech(itemView.context, this)

        fun reproduceAudio(){
            val audiomanager = itemView.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            val max = audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
            audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC, max, 0)
            tts.speak("ola mundo", TextToSpeech.QUEUE_FLUSH, null)
        }
    }

}
