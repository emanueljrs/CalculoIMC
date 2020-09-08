package com.example.calculoimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    var imc = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val tela1 = intent
        imc = tela1.getFloatExtra("imc", 0.0f)
        val faixa = tela1.getStringExtra("faixa")

        mudarImagem()
        textViewImc.setText("Seu IMC é: %.2f".format(imc))
        textViewFaixa.setText(faixa)
    }

    private fun mudarImagem() {
        if (imc <= 18.49) {
            imageViewResultado.setImageResource(R.drawable.excla)
        } else if (imc > 18.49 && imc <= 24.99) {
            imageViewResultado.setImageResource(R.drawable.ok1)
        } else if (imc > 24.99) {
            imageViewResultado.setImageResource(R.drawable.x1)
        }
    }
}
