package com.example.calculoimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var imc = 0.0f
    private lateinit var faixa: String
    private lateinit var faixaPesoIdeal: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        buttonCalcular.setOnClickListener { calcularIMC() }

        editTextPeso.setOnKeyListener { _, _, _ ->
            if (editTextPeso.text.toString() == "") {
                peso_layout.error = null
            }
            false
        }

        editTextAltura.setOnKeyListener { _, _, _ ->
            if (editTextAltura.text.toString() == "") {
                altura_layout.error = null
            }
            false
        }
    }


    override fun onResume() {
        super.onResume()
        editTextPeso.text?.clear()
        editTextPeso.requestFocus()
        editTextAltura.text?.clear()
    }


    //Calcula o IMC com os valores digitados nos editTexts
    private fun calcularIMC() {

        if (verificarCampos()) {
            val peso = editTextPeso.text.toString().toFloat()
            val altura = editTextAltura.text.toString().toFloat()

            imc = peso / (altura * altura)

            faixaPesoIdeal = faixaPeso(altura)
            verifcarIMC()
            chamarActivity()
        }

    }

    private fun faixaPeso(altura: Float): String {

        return when(altura) {
            in 1.50..1.54 -> "45.5kg - 59.1kg"
            in 1.54..1.60 -> "47.7kg - 63.6kg"
            in 1.60..1.64 -> "50.1kg - 65.9kg"
            in 1.64..1.70 -> "52.3kg - 68.2kg"
            in 1.70..1.74 -> "54.5kg - 70.5kg"
            in 1.74..1.80 -> "56.8kg - 79.5kg"
            in 1.80..1.84 -> "61.4kg - 81.8kg"
            in 1.84..1.90 -> "63.6kg - 86.4kg"
            else -> "-"
        }

    }

    //Verifica a integridade dos campos para fazer o cálculo
    private fun verificarCampos(): Boolean {

        val edtPeso = editTextPeso
        val edtAltura = editTextAltura
        val msgError = getString(R.string.preencher_campos)

        if (edtPeso.text.toString() == "" || edtPeso.text.toString() == "0") {
            peso_layout.error = msgError
            altura_layout.error = null
            return false
        }

        if (edtAltura.text.toString() == "" || edtAltura.text.toString() == "0") {
            altura_layout.error = msgError
            peso_layout.error = null
            return false
        }

        if (edtPeso.text.toString() == "" || edtAltura.text.toString() == "") {
            altura_layout.error = msgError
            peso_layout.error = msgError
            return false
        }

        altura_layout.error = null
        peso_layout.error = null
        return true
    }

    //Chama a outra activity e mostra o resultado do IMC e sua faixa de peso
    private fun chamarActivity() {
        val result_activity = Intent(this, ResultActivity::class.java)
        result_activity.putExtra("imc", imc)
        result_activity.putExtra("faixaPeso", faixaPesoIdeal)
        startActivity(result_activity)
    }

    //Verifica qual faixa o IMC calculado se encontra
    private fun verifcarIMC() {

        val strFaixas = listOf(
            getString(R.string.abaixo),
            getString(R.string.normal),
            getString(R.string.sobrepeso),
            getString(R.string.obesidade),
            getString(R.string.obesidade_morbida))

        if (imc <= 18.5) {
            faixa = strFaixas[0]
        } else if (imc > 18.5 && imc <= 24.9) {
            faixa = strFaixas[1]
        } else if (imc > 24.9 && imc <= 30.0) {
            faixa = strFaixas[2]
        } else if (imc > 30.0 && imc <= 39.9) {
            faixa = strFaixas[3]
        } else if (imc >= 40.0) {
            faixa = strFaixas[4]
        }
    }
}
