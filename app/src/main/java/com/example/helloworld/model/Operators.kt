package com.example.helloworld.model

enum class Operators (val operation : String) {
    ADDITION("+"){
        override fun calculate(a: Double, b: Double): Double {
            return a + b
        }
    },
    SUBTRACTION("-"){
        override fun calculate(a: Double, b: Double): Double {
            return a - b
        }
    },
    MULTIPLICATION("*"){
        override fun calculate(a: Double, b: Double): Double {
            return a * b
        }
    },
    DIVISION("/"){
        override fun calculate(a: Double, b: Double): Double {
            return a / b
        }
    };
    abstract fun calculate(a: Double, b: Double): Double
}