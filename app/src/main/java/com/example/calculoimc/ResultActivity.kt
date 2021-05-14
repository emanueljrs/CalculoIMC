package com.example.calculoimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {

    private var imc = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //Inicializa o RecyclerView que mostra o gráfico
        val recycler = graph_imc_recycler
        recycler.adapter = GraphImcListAdapter(graphs(), this)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = layoutManager

        //Pega os valores passados pela activity que a chamou
        val main_activity = intent
        imc = main_activity.getFloatExtra("imc", 0.0f)
        val faixaPeso = main_activity.getStringExtra("faixaPeso")

        val resultado = getString(R.string.resultado_imc)

        //Habilita o TextView de acordo com o valor do IMC
        when{
            imc <= 18.5 -> {
                textView0.visibility = VISIBLE
                textView0.text = "%.2f $resultado".format(imc)
            }
            imc in 18.6..24.9 -> {
                textView1.visibility = VISIBLE
                textView1.text = "%.2f $resultado".format(imc)
            }
            imc in 25.0..30.0 -> {
                textView2.visibility = VISIBLE
                textView2.text = "%.2f $resultado".format(imc)
            }
            imc in 30.1..39.9 -> {
                textView3.visibility = VISIBLE
                textView3.text = "%.2f $resultado".format(imc)
            }
            imc > 40.0 -> {
                textView4.visibility = VISIBLE
                textView4.text = "%.2f $resultado".format(imc)
            }
        }

        textViewFaixa.text = getString(R.string.faixa_peso_ideal)
        textViewFaixaPeso.text = faixaPeso
    }

    //Função para preencher o RecyclerView
    private fun graphs(): List<GraphImc> {
        return listOf(
            GraphImc(getString(R.string.abaixo),
            getString(R.string.menor_que),
            "#FFFBC02D"),
            GraphImc(getString(R.string.normal),
                "18.5 - 24.9",
                "#FF11D80A"),
            GraphImc(getString(R.string.sobrepeso),
                "25.0 - 30.0",
                "#FFF57C00"),
            GraphImc(getString(R.string.obesidade),
                "30.1 - 39.9",
                "#FFFB1818"),
            GraphImc(getString(R.string.obesidade_morbida),
                getString(R.string.maior_que),
                "#FF990000"),
        )
    }
}
