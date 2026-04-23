package com.example.helloworld.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.helloworld.R
import com.example.helloworld.model.Operators
import com.example.helloworld.presenter.Presenter
import com.example.helloworld.presenter.PresenterImp

class Calculadora : AppCompatActivity(), CalculadoraView {

    var aux: Double = 0.0
    var operacion: String = ""
    var resultado: Double = 0.0
    private lateinit var presenter: Presenter
    lateinit var tv_Resultado: TextView
    lateinit var tv_Resumen: TextView
    lateinit var btn_0: Button
    lateinit var btn_1: Button
    lateinit var btn_2: Button
    lateinit var btn_3: Button
    lateinit var btn_4: Button
    lateinit var btn_5: Button
    lateinit var btn_6: Button
    lateinit var btn_7: Button
    lateinit var btn_8: Button
    lateinit var btn_9: Button
    lateinit var btn_clear: Button
    lateinit var btn_sum: Button
    lateinit var btn_rest: Button
    lateinit var btn_div: Button
    lateinit var btn_mult: Button
    lateinit var btn_dec: Button
    lateinit var btn_calc: Button
    lateinit var btn_volver: Button

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
        initViews()
        presenter = PresenterImp(this)
        initListeners()
    }

    fun initViews() {
        tv_Resultado = findViewById(R.id.tv_textoResultado)
        tv_Resumen = findViewById(R.id.tv_Resumen)

        btn_0 = findViewById(R.id.btn_0)
        btn_1 = findViewById(R.id.btn_1)
        btn_2 = findViewById(R.id.btn_2)
        btn_3 = findViewById(R.id.btn_3)
        btn_4 = findViewById(R.id.btn_4)
        btn_5 = findViewById(R.id.btn_5)
        btn_6 = findViewById(R.id.btn_6)
        btn_7 = findViewById(R.id.btn_7)
        btn_8 = findViewById(R.id.btn_8)
        btn_9 = findViewById(R.id.btn_9)

        btn_clear = findViewById(R.id.btn_clear)
        btn_sum = findViewById(R.id.btn_sumar)
        btn_rest = findViewById(R.id.btn_restar)
        btn_div = findViewById(R.id.btn_div)
        btn_mult = findViewById(R.id.btn_mult)
        btn_dec = findViewById(R.id.btn_dec)
        btn_calc = findViewById(R.id.btn_calc)
        btn_volver = findViewById(R.id.btn_volver)
    }

    fun initListeners() {
        val digits = listOf(btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9)

        digits.forEachIndexed { digit, button ->
            button.setOnClickListener { presenter.onClickDigit(digit) }
        }
        btn_clear.setOnClickListener { presenter.onClickClear() }
        btn_calc.setOnClickListener { presenter.onClickEquals() }
        btn_sum.setOnClickListener { presenter.onClickOperation(Operators.ADDITION) }
        btn_rest.setOnClickListener { presenter.onClickOperation(Operators.SUBTRACTION) }
        btn_mult.setOnClickListener { presenter.onClickOperation(Operators.MULTIPLICATION) }
        btn_div.setOnClickListener { presenter.onClickOperation(Operators.DIVISION) }
        btn_volver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun appenddisplayResult(result: String) {
        tv_Resultado.append(result)
    }

    override fun appenddisplayResumen(resumen: String) {
        tv_Resumen.append(resumen)
    }

    override fun displayResult(result: String) {
        tv_Resultado.text = result
    }

    override fun displayResumen(resumen: String) {
        tv_Resumen.text = resumen
    }
}