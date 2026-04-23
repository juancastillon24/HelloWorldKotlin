package com.example.helloworld.presenter

import com.example.helloworld.model.Operators

interface Presenter {

    fun onClickDigit(digit : Int)
    fun onClickOperation(operation : Operators)
    fun onClickClear()
    fun onClickEquals()
}