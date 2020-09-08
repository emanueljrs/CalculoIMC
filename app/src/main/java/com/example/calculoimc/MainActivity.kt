package com.example.calculoimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var imc = 0.0f
    private lateinit var faixa: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btCalcular = findViewById<Button>(R.id.buttonCalcular)
        btCalcular.setOnClickListener { calcularIMC() }
    }

    private fun calcularIMC() {

        if (verificarCampos()) {
            val peso = editTextPeso.text.toString().toFloat()
            val altura = editTextAltura.text.toString().toFloat()

            imc = peso / (altura * altura)

            verifcarIMC()
            chamarActivity()
        }

    }

    private fun verificarCampos(): Boolean {

        val edtPeso = findViewById<EditText>(R.id.editTextPeso)
        val edtAltura = findViewById<EditText>(R.id.editTextAltura)

        if (edtPeso.text.toString() == "" && edtAltura.text.toString() == "") {
            toast("Preencha os campos!")
            return false
        } else if (edtPeso.text.toString() == "" && edtAltura.text.toString() == "0") {
            toast("Preencha o campo Peso e Altura não pode ser 0.")
            return false
        } else if (edtAltura.text.toString() == "" && edtPeso.text.toString() == "0") {
            toast("Preencha o campo Altura e o Peso não pode ser 0.")
            return false
        } else if (edtPeso.text.toString() == "0" && edtAltura.text.toString() == "0") {
            toast("Peso e Altura não podem ser 0.")
            return false
        }
        return true
    }

    private fun chamarActivity() {
        val tela2 = Intent(this, Main2Activity::class.java)
        tela2.putExtra("imc", imc)
        tela2.putExtra("faixa", faixa)
        startActivity(tela2)
    }

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

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
