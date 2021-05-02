package com.example.calculoimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    private var imc = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val recycler = graph_imc_recycler
        recycler.adapter = GraphImcListAdapter(graphs(), this)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = layoutManager

        val main_activity = intent
        imc = main_activity.getFloatExtra("imc", 0.0f)
        val faixa = main_activity.getStringExtra("faixa")
        val faixaPeso = main_activity.getStringExtra("faixaPeso")

        val resultado = getString(R.string.resultado_imc)
        textViewImc.text = "$resultado %.2f".format(imc)
        textViewFaixa.text = faixa
        textViewFaixaPeso.text = faixaPeso
    }

    private fun graphs(): List<GraphImc> {
        return listOf(
            GraphImc("Abaixo do Peso",
            "Menor que 18.5",
            "#FFFBC02D"),
            GraphImc("Peso saudável",
                "18.5 - 24.9",
                "#FF11D80A"),
            GraphImc("Sobrepeso",
                "25.0 - 30.0",
                "#FFF57C00"),
            GraphImc("Obeso",
                "30.1 - 39.9",
                "#FFFB1818"),
            GraphImc("Obeso Mórbido",
                "Maior que 40",
                "#FF990000"),
        )
    }

    /* private fun mudarImagem() {
         if (imc <= 18.49) {
             imgTable.setImageResource(R.drawable.advertencia)
         } else if (imc > 18.49 && imc <= 24.99) {
             imgTable.setImageResource(R.drawable.normal)
         } else if (imc > 24.99) {
             imgTable.setImageResource(R.drawable.alerta)
         }
     }*/
}
