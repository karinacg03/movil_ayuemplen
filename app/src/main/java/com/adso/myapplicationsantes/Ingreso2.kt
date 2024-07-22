package com.adso.myapplicationsantes

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Ingreso2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingreso2)

        // Inicializar las vistas
        val imageView = findViewById<ImageView>(R.id.imageView)
        val autoCompleteTextViewmunicipio = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewmunicipio)
        val autoCompleteTextViewdepartamento = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewdepartamento)
        val autoCompleteTextViewGenero = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewGenero)
        val editTextTelefono = findViewById<EditText>(R.id.editTextTelefono)
        val buttonSeleccionarFoto = findViewById<Button>(R.id.buttonSeleccionarFoto)
        val checkBoxPoliticas = findViewById<CheckBox>(R.id.checkBoxPoliticas)
        val buttonRegistrarse = findViewById<Button>(R.id.buttonRegistrarse)

        // Crear y configurar los ArrayAdapter
        val municipiosAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, resources.getStringArray(
            R.array.municipalities))
        autoCompleteTextViewmunicipio.setAdapter(municipiosAdapter)

        val departamentosAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, resources.getStringArray(
            R.array.departments))
        autoCompleteTextViewdepartamento.setAdapter(departamentosAdapter)

        val generoAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, resources.getStringArray(
            R.array.genders))
        autoCompleteTextViewGenero.setAdapter(generoAdapter)

        // Agrega un OnClickListener al botón "Regístrate"
        buttonRegistrarse.setOnClickListener {
            // Aquí es donde navegarás a la pantalla de inicio (home)
            val intent = Intent(this@Ingreso2, Home::class.java)
            startActivity(intent)
        }
    }
}