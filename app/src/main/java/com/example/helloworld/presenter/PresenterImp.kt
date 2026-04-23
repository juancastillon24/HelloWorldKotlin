package com.example.helloworld.presenter

import android.widget.TextView
import com.example.helloworld.model.Operators
import com.example.helloworld.views.Calculadora
import com.example.helloworld.views.CalculadoraView

class PresenterImp(private var view: CalculadoraView?) : Presenter {

    var input: String = ""
    var aux: Double = 0.0
    var result: Double? = null
    var operationTODO: Operators? = null
    var operatorActive = false

    override fun onClickDigit(digit: Int) {
        if (result!=null){
            view?.displayResult("")
        }
        input += digit.toString()
        appenddisplays(digit.toString())
    }

    override fun onClickOperation(operation: Operators) {
        if (input == "" && result != null && !operatorActive) {
            view?.displayResult("")
            aux = result?: 0.0
            result = null
            appenddisplays(aux.toString())
        } else if(result==null && !operatorActive) {
            aux = input.toDoubleOrNull() ?: 0.0
            view?.displayResult(aux.toString())
        }
        if (!operatorActive) {
            input = ""
            operationTODO = operation
            operatorActive=true
            appenddisplays(operation.operation)
        } else if(input!="") {
            aux = calculate(operationTODO!!, aux, input.toDouble()) ?: 0.0
            input=""
            view?.displayResult(aux.toString())
            operationTODO = operation
            appenddisplays(operation.operation)
        }
    }

    override fun onClickClear() {
        clear()
    }

    override fun onClickEquals() {
        input.toDoubleOrNull() ?: 0.0
        if (operationTODO != null) {
            result = calculate(operationTODO!!, aux, input.toDouble())!!
            appenddisplays(" = $result\n")
            aux = result ?: 0.0
            input = ""
            operationTODO = null
            operatorActive=false
        }
    }

    private fun clear() {
        aux = 0.0
        input = ""
        result = null
        view?.displayResult("")
        view?.displayResumen("")
        operationTODO = null
        !operatorActive
    }

    private fun appenddisplays(disp: String) {
        view?.appenddisplayResult(disp)
        view?.appenddisplayResumen(disp)
    }
    private fun calculate(operation: Operators, a: Double, b: Double): Double? {
        return try {
            operation.calculate(a, b)
        } catch (_: Exception) {
            view?.displayResult("Error: Division by zero")
            null
        }
    }
}

