package com.example.calculoimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var imc = 0.0f
    private lateinit var faixa: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btCalcular = buttonCalcular
        btCalcular.setOnClickListener { calcularIMC() }

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

            verifcarIMC()
            chamarActivity()
        }

    }

    //Verifica a integridade dos campos para fazer o cálculo
    private fun verificarCampos(): Boolean {

        val edtPeso = findViewById<EditText>(R.id.editTextPeso)
        val edtAltura = findViewById<EditText>(R.id.editTextAltura)

        if (edtPeso.text.toString() == "" || edtPeso.text.toString() == "0") {
            peso_layout.error = getString(R.string.preencher_campos)
            altura_layout.error = null
            return false
        }

        if (edtAltura.text.toString() == "" || edtAltura.text.toString() == "0") {
            altura_layout.error = getString(R.string.preencher_campos)
            peso_layout.error = null
            return false
        }

        if (edtPeso.text.toString() == "" || edtAltura.text.toString() == "") {
            altura_layout.error = getString(R.string.preencher_campos)
            peso_layout.error = getString(R.string.preencher_campos)
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
        result_activity.putExtra("faixa", faixa)
        startActivity(result_activity)
    }

    //Verifica qual faixa o IMC calculado se encontra
    private fun verifcarIMC() {
        if (imc <= 17) {
            faixa = "Muito abaixo do peso."
        } else if (imc > 17 && imc <= 18.49) {
            faixa = "Abaixo do peso."
        } else if (imc > 18.49 && imc <= 24.99) {
            faixa = "Peso normal."
        } else if (imc > 24.99 && imc <= 29.99) {
            faixa = "Acima do peso."
        } else if (imc > 29.99 && imc <= 34.99) {
            faixa = "Obesidade I."
        } else if (imc > 34.99 && imc <= 39.99) {
            faixa = "Obesidade II Severa."
        } else if (imc > 39.99) {
            faixa = "Obesidade III Mórbida."
        }
    }
}
