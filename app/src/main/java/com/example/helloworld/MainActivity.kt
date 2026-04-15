package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setTitle("Menu Principal")
        val btnResetear: Button=findViewById(R.id.btn_reset)
        btnResetear.setOnClickListener(this)
        val btnCambiar: Button=findViewById(R.id.btn_cambiar)
        btnCambiar.setOnClickListener(this)
        val btnCalculadora: Button=findViewById(R.id.btn_calculadora)
        btnCalculadora.setOnClickListener (this)
    }

    override fun onClick(p0: View?){
        val toast: Toast
        val tvTexto: TextView=findViewById(R.id.tv_Texto)
        val tvTextoaCambiar: EditText= findViewById(R.id.editTextText)
        when (p0?.id){
            R.id.btn_reset -> {
                toast= Toast.makeText(this.application, "Texto reseteado a 'Hello World!'",Toast.LENGTH_SHORT)
                toast.show()
                tvTexto.text="Hello World!"
            }
            R.id.btn_cambiar -> {
                toast= Toast.makeText(this.application,"Texto cambiado con exito", Toast.LENGTH_SHORT)
                toast.show()
                tvTexto.text=tvTextoaCambiar.text
            }
            R.id.btn_calculadora ->{
                val intent = Intent(this, Calculadora::class.java)
                startActivity(intent)
            }
        }
    }


}