package com.example.helloworld.views

interface CalculadoraView {
    fun displayResult(result: String)
    fun displayResumen(resumen: String)

    fun appenddisplayResult(result: String)
    fun appenddisplayResumen(resumen: String)
}