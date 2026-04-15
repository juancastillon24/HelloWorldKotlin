package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Calculadora : AppCompatActivity(), View.OnClickListener {

    var aux: Double = 0.0
    var operacion: String = ""
    var resultado: Double = 0.0


    lateinit var tv_Resultado: TextView
    lateinit var tv_Resumen: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculadora)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setTitle("Calculadora")

        tv_Resultado = findViewById(R.id.tv_textoResultado)
        tv_Resumen = findViewById(R.id.tv_Resumen)
        /**
         * Inicializo el hint y el text del text view vacios
         * para que no falle si empiezas poniendo un simbolo
         */
        tv_Resultado.text = ""
        tv_Resultado.hint = ""

        val btn_0: Button = findViewById(R.id.btn_0)
        btn_0.setOnClickListener(this)
        val btn_1: Button = findViewById(R.id.btn_1)
        btn_1.setOnClickListener(this)
        val btn_2: Button = findViewById(R.id.btn_2)
        btn_2.setOnClickListener(this)
        val btn_3: Button = findViewById(R.id.btn_3)
        btn_3.setOnClickListener(this)
        val btn_4: Button = findViewById(R.id.btn_4)
        btn_4.setOnClickListener(this)
        val btn_5: Button = findViewById(R.id.btn_5)
        btn_5.setOnClickListener(this)
        val btn_6: Button = findViewById(R.id.btn_6)
        btn_6.setOnClickListener(this)
        val btn_7: Button = findViewById(R.id.btn_7)
        btn_7.setOnClickListener(this)
        val btn_8: Button = findViewById(R.id.btn_8)
        btn_8.setOnClickListener(this)
        val btn_9: Button = findViewById(R.id.btn_9)
        btn_9.setOnClickListener(this)
        val btn_clear: Button = findViewById(R.id.btn_clear)
        btn_clear.setOnClickListener(this)
        val btn_sum: Button = findViewById(R.id.btn_sumar)
        btn_sum.setOnClickListener(this)
        val btn_rest: Button = findViewById(R.id.btn_restar)
        btn_rest.setOnClickListener(this)
        val btn_div: Button = findViewById(R.id.btn_div)
        btn_div.setOnClickListener(this)
        val btn_mult: Button = findViewById(R.id.btn_mult)
        btn_mult.setOnClickListener(this)
        val btn_dec: Button = findViewById(R.id.btn_dec)
        btn_dec.setOnClickListener(this)
        val btn_calc: Button = findViewById(R.id.btn_calc)
        btn_calc.setOnClickListener(this)
        val btn_volver: Button = findViewById(R.id.btn_volver)
        btn_volver.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {

        /**
         * Añadir numeros al TextView para
         * el calculo que se va a realizar
         */
        when (p0?.id) {
            R.id.btn_0 -> {
                tv_Resultado.append("0")
                tv_Resumen.append("0")
            }

            R.id.btn_1 -> {
                tv_Resultado.append("1")
                tv_Resumen.append("1")
            }

            R.id.btn_2 -> {
                tv_Resultado.append("2")
                tv_Resumen.append("2")
            }

            R.id.btn_3 -> {
                tv_Resultado.append("3")
                tv_Resumen.append("3")
            }

            R.id.btn_4 -> {
                tv_Resultado.append("4")
                tv_Resumen.append("4")
            }

            R.id.btn_5 -> {
                tv_Resultado.append("5")
                tv_Resumen.append("5")
            }

            R.id.btn_6 -> {
                tv_Resultado.append("6")
                tv_Resumen.append("6")
            }

            R.id.btn_7 -> {
                tv_Resultado.append("7")
                tv_Resumen.append("7")
            }

            R.id.btn_8 -> {
                tv_Resultado.append("8")
                tv_Resumen.append("8")
            }

            R.id.btn_9 -> {
                tv_Resultado.append("9")
                tv_Resumen.append("9")
            }

            R.id.btn_clear -> {
                clear()
                tv_Resultado.hint = ""
                tv_Resumen.text = ""
            }

            /**
             * Acciones que se realizan al hacer click en el boton del operador,
             * si no se inserta número este se asigna automaticamente a 0
             */
            R.id.btn_div -> {
                try {
                    if (tv_Resultado.text.isEmpty() && tv_Resultado.hint.isEmpty()) {
                        tv_Resultado.hint = "0.0"
                        tv_Resultado.text = ""
                        tv_Resumen.text = "0.0"
                    } else {
                        if (!tv_Resultado.text.isEmpty()) {
                            aux = tv_Resultado.text.toString().toDouble()
                        } else {
                            aux = tv_Resultado.hint.toString().toDouble()
                            tv_Resumen.append(tv_Resultado.hint)
                        }
                        tv_Resultado.hint = tv_Resultado.text
                        tv_Resultado.text = ""
                    }
                    tv_Resumen.append(" / ")
                    operacion = "Division"

                } catch (e: NumberFormatException) {
                    tv_Resultado.hint = ""
                    tv_Resumen.append(" / ")
                    operacion = "Division"
                }
            }

            R.id.btn_mult -> {
                try {
                    if (tv_Resultado.text.isEmpty() && tv_Resultado.hint.isEmpty()) {
                        aux = 0.0
                        tv_Resultado.hint = "0.0"
                        tv_Resultado.text = ""
                    } else {
                        if (!tv_Resultado.text.isEmpty()) {
                            aux = tv_Resultado.text.toString().toDouble()
                        } else {
                            aux = tv_Resultado.hint.toString().toDouble()
                            tv_Resumen.append(tv_Resultado.hint)
                        }
                        tv_Resultado.hint = tv_Resultado.text
                        tv_Resultado.text = ""
                    }
                    tv_Resumen.append(" * ")
                    operacion = "Multiplicacion"

                } catch (e: NumberFormatException) {
                    tv_Resultado.hint = ""
                    tv_Resumen.append(" * ")
                    operacion = "Multiplicacion"
                }
            }

            R.id.btn_sumar -> {
                try {
                    if (tv_Resultado.text.isEmpty() && tv_Resultado.hint.isEmpty()) {
                        aux = 0.0
                        tv_Resultado.hint = "0.0"
                        tv_Resultado.text = ""
                    } else {
                        if (!tv_Resultado.text.isEmpty()) {
                            aux = tv_Resultado.text.toString().toDouble()
                        } else {
                            aux = tv_Resultado.hint.toString().toDouble()
                            tv_Resumen.append(tv_Resultado.hint)
                        }
                        tv_Resultado.hint = tv_Resultado.text
                        tv_Resultado.text = ""
                    }
                    tv_Resumen.append(" + ")
                    operacion = "Suma"
                } catch (e: NumberFormatException) {
                    tv_Resultado.hint = ""
                    tv_Resumen.append(" + ")
                    operacion = "Suma"
                }
            }

            R.id.btn_restar -> {
                try {
                    if (tv_Resultado.text.isEmpty() && tv_Resultado.hint.isEmpty()) {
                        aux = 0.0
                        tv_Resultado.hint = "0.0"
                        tv_Resultado.text = ""
                    } else {
                        if (!tv_Resultado.text.isEmpty()) {
                            aux = tv_Resultado.text.toString().toDouble()
                        } else {
                            aux = tv_Resultado.hint.toString().toDouble()
                            tv_Resumen.append(tv_Resultado.hint)
                        }
                        tv_Resultado.hint = tv_Resultado.text
                        tv_Resultado.text = ""
                    }
                    tv_Resumen.append(" - ")
                    tv_Resultado.append("-")
                    operacion = "Resta"
                } catch (e: NumberFormatException) {
                    tv_Resultado.hint = ""
                    tv_Resumen.append(" - ")
                    operacion = "Resta"
                }
            }

            R.id.btn_dec -> {
                if(!tv_Resultado.text.toString().contains(".")){
                    tv_Resultado.append(".")
                    tv_Resumen.append(".")
                }
            }

            R.id.btn_calc -> {
                /*
                Inicializo el resultado para poder asignarlo mas tarde
                 */
                val numero_act: Double = tv_Resultado.text.toString().toDoubleOrNull() ?: 0.0
                if (operacion.equals("Division")) {
                    if (numero_act == 0.0) {
                        tv_Resultado.hint = "ERROR: El divisor no puede ser 0"
                        tv_Resultado.text = ""
                        tv_Resumen.append(" = ${tv_Resultado.hint}\n")
                        clear()
                    } else {
                        resultado = aux / numero_act
                        aux = resultado
                        tv_Resumen.append(" = ${resultado.toString()} \n")
                        tv_Resultado.hint = resultado.toString()

                    }
                } else if (operacion.equals("Multiplicacion")) {
                    resultado = aux * numero_act
                    aux = resultado
                    tv_Resumen.append(" = ${resultado.toString()} \n")
                    tv_Resultado.hint = resultado.toString()

                } else if (operacion.equals("Suma")) {
                    resultado = aux + numero_act
                    aux = resultado
                    tv_Resumen.append(" = ${resultado.toString()} \n")
                    tv_Resultado.hint = resultado.toString()

                } else if (operacion.equals("Resta")) {
                    resultado = aux - numero_act
                    aux = resultado
                    tv_Resumen.append(" = ${resultado.toString()} \n")
                    tv_Resultado.hint = resultado.toString()

                } else if (operacion.isEmpty()) {
                    if (tv_Resultado.text.isEmpty() && tv_Resultado.hint.isEmpty()) {
                        tv_Resumen.append("No se han introducido valores\n")
                        tv_Resultado.hint = resultado.toString()
                    } else if (tv_Resultado.text.isEmpty()) {
                        tv_Resumen.append("${tv_Resultado.hint}\n")
                        tv_Resultado.hint = resultado.toString()
                    } else resultado = tv_Resultado.text.toString().toDouble()
                }
                tv_Resultado.text = ""
                operacion = ""
            }

            R.id.btn_volver -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun clear() {
        tv_Resultado.text = ""
        aux = 0.0;
        resultado = 0.0
    }
}