package com.example.calculoimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    var imc = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val main_activity = intent
        imc = main_activity.getFloatExtra("imc", 0.0f)
        val faixa = main_activity.getStringExtra("faixa")

        mudarImagem()
        textViewImc.text = "Seu IMC é: %.2f".format(imc)
        textViewFaixa.text = faixa
    }

    private fun mudarImagem() {
        if (imc <= 18.49) {
            imageViewResultado.setImageResource(R.drawable.advertencia)
        } else if (imc > 18.49 && imc <= 24.99) {
            imageViewResultado.setImageResource(R.drawable.normal)
        } else if (imc > 24.99) {
            imageViewResultado.setImageResource(R.drawable.alerta)
        }
    }
}
