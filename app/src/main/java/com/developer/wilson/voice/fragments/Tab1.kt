package com.developer.wilson.voice.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.wilson.voice.R
import com.developer.wilson.voice.modelos.Nota
import kotlinx.android.synthetic.main.tab1.*
import kotlinx.android.synthetic.main.tab1.view.*

class Tab1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v: View = inflater.inflate(R.layout.tab1, container, false)
        var nota = Nota(0, "", "", "")

        val find = nota.find(v.context)

        print("lista: "+find.size)

        v.textQuantidade.setText("Quantidade de Notas: "+find.size)

        return v
    }
}