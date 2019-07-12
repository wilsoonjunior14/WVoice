package com.developer.wilson.voice.fragments

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.tab2.*
import kotlinx.android.synthetic.main.tab2.view.*
import org.w3c.dom.Text
import android.R
import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.os.Build
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech.OnInitListener
import android.widget.Toast
import com.developer.wilson.voice.modelos.DataSet
import com.developer.wilson.voice.modelos.Database
import com.developer.wilson.voice.modelos.Nota
import com.developer.wilson.voice.utils.config
import java.util.*


class Tab2: Fragment(), RecognitionListener, OnInitListener {
    override fun onInit(status: Int) {
    }

    var nota = Nota(0, "", "", "")

    override fun onReadyForSpeech(params: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRmsChanged(rmsdB: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBufferReceived(buffer: ByteArray?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPartialResults(partialResults: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBeginningOfSpeech() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEndOfSpeech() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(error: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResults(results: Bundle?) {
        val matches = results!!.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        // leitura foi realizada corretamente
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            println("--> fim do speech")
            var matches = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches.size > 0){
                this.nota.setDescricao(matches[0])
                textDescricao.visibility = View.VISIBLE
                descricao.isEnabled = true
                descricao.setText(this.nota.getDescricao())
                descricao.visibility = View.VISIBLE
            }else{
                textDescricao.visibility = View.INVISIBLE
                descricao.isEnabled = false
                descricao.visibility = View.INVISIBLE
                this.nota.setDescricao("")
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v: View = inflater.inflate(com.developer.wilson.voice.R.layout.tab2, container, false);

        var speech = SpeechRecognizer.createSpeechRecognizer(v.context)
        speech.setRecognitionListener(this)

        var tts = TextToSpeech(v.context, this)
        tts.setLanguage(Locale.US)

        var sqlite = Database(v.context)

        v.recordingAudio.setOnClickListener{
            println("--> Recoding Audio")

            var intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale algo...")
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

            startActivityForResult(intent, 1)
        }

        v.tellAudio.setOnClickListener{
            println("--> Speaking audio")
            if (!this.nota.getDescricao().equals("")){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tts.speak(this.nota.getDescricao(), TextToSpeech.QUEUE_FLUSH, null, "")
                }
            }else{
                Toast.makeText(v.context, "Nenhum áudio foi encontrado", Toast.LENGTH_LONG).show()
            }
        }

        v.cancelarNota.setOnClickListener{
            this.nota = Nota(0,"", "", "")
            inicializaCampos(v)
            Toast.makeText(v.context, "Nota foi cancelada!", Toast.LENGTH_LONG).show()
        }

        v.salvarNota.setOnClickListener{

            this.nota.setTitulo(v.titulo.text.toString())
            this.nota.setData(config().convertData(v.data.text.toString()))
            this.nota.setDescricao(v.descricao.text.toString())

            println("titulo: "+this.nota.getTitulo()+" descricao: "+this.nota.getDescricao()+" data: "+this.nota.getData())

            if (this.nota.getTitulo().length <= 0 || this.nota.getTitulo().length > 255){
                Toast.makeText(v.context, "Título da nota inválido! Máximo de 255 caracteres são permitidos", Toast.LENGTH_LONG).show()
            }else if (this.nota.getDescricao().length <= 0 || this.nota.getDescricao().length > 5000){
                Toast.makeText(v.context, "Descrição inválida! Máximo de 5000 caracteres são permitidos", Toast.LENGTH_LONG).show()
            }else if (this.nota.getData().length <= 0 || this.nota.getData().length > 10){
                Toast.makeText(v.context, "Data da nota inválida!", Toast.LENGTH_LONG).show()
            }else{

                // function save nota
                var returns = this.nota.save(v.context, nota as Object)
                if (returns) this.inicializaCampos(v)
            }

        }

        return v
    }

    fun inicializaCampos(v: View){
        v.descricao.setText("")
        v.data.setText("")
        v.titulo.setText("")
    }

}