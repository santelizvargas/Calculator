package com.santeliz.calculator.models

import android.annotation.SuppressLint
import android.widget.Button
import android.widget.TextView
import com.santeliz.calculator.R
import com.santeliz.calculator.helpers.ButtonHelper
import com.santeliz.calculator.helpers.ViewHelper
import java.lang.ArithmeticException

class Calculator : ButtonHelper() {
    private var firstOperator = 0.0
    private var secondOperator = 0.0
    private var operation = "none"
    private lateinit var textViewResult: TextView

    fun builder() {
        textViewResult = view.findViewById(R.id.tvResult)
        addNumberToCalculator()
        handledOperation()
        handledResult()
        handledReset()
        handledDropLastDigit()
    }

    @SuppressLint("SetTextI18n")
    private fun addNumberToCalculator() {
        getNumericButtons().forEach {
            val button = it
            val numberPressed = button.text.toString()
            it.setOnClickListener {
                if(textViewResult.text.contains(".") && numberPressed == ".") {
                    return@setOnClickListener
                } else if(textViewResult.text == "0" && numberPressed != ".") {
                    textViewResult.text = numberPressed
                } else {
                    textViewResult.text = "${textViewResult.text}${numberPressed}"
                }

                if(operation === "none") {
                    firstOperator = textViewResult.text.toString().toDouble()
                } else {
                    secondOperator = textViewResult.text.toString().toDouble()
                }
            }
        }
    }

    private fun handledOperation() {
        getSignsButtons().forEach {
            val sign = it.text.toString().trim()
            it.setOnClickListener {
                operation = sign
                firstOperator = textViewResult.text.toString().toDouble()
                textViewResult.text = "0"
            }
        }
    }

    private fun handledResult() {
        getEqualButton().setOnClickListener {
            var result = when(operation) {
                "+" -> firstOperator + secondOperator
                "-" -> firstOperator - secondOperator
                "x" -> firstOperator * secondOperator
                "/" -> if(firstOperator !== 0.0 && secondOperator !== 0.0) firstOperator / secondOperator else handledZeroDivision()
                else -> 0
            }

            if(result === 0) {
                result = 0.0
            }

            if(result.toString() !== "invalid") {
                firstOperator = result as Double
                textViewResult.text = if("$result".endsWith(".0")) { "$result".replace(".0","") } else { "%.2f".format(result) }
            }
        }
    }

    private fun handledReset() {
        getResetButton().setOnClickListener {
            textViewResult.text = "0"
            firstOperator = 0.0
            secondOperator = 0.0
        }
    }

    private fun handledZeroDivision(): String {
        handledReset()
        ViewHelper.getInstance().showMessage("No se puede dividir por cero")
        return "invalid"
    }

    private fun handledDropLastDigit() {
        getDropButton().setOnClickListener {
            val currentValue = textViewResult.text.toString()

            textViewResult.text = if (currentValue.isNotEmpty() && currentValue.length > 1) currentValue.dropLast(1) else "0"

            if(textViewResult.text == "-") {
                textViewResult.text = "0"
            }
        }
    }

    private fun getEqualButton(): Button = view.findViewById(R.id.btnEquals)

    private fun getResetButton(): Button = view.findViewById(R.id.btnClear)

    private fun getDropButton(): Button = view.findViewById(R.id.btnDrop)
}