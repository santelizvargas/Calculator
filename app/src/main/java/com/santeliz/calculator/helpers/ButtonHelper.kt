package com.santeliz.calculator.helpers

import android.widget.Button
import androidx.core.view.children

open class ButtonHelper {
    protected val view = ViewHelper.getInstance().viewGroup!!

    private fun getButtons(): List<Button> = view
        .children
        .filter { it is Button }
        .map { it as Button }
        .toList()

    protected fun getNumericButtons(): List<Button> = getButtons()
        .filter { it.text.toString().contains(".") || it.text.toString().all { char -> char.isDigit() } }

    protected fun getSignsButtons(): List<Button> = getButtons()
        .filter { !it.text.toString().contains(".") && it.text.toString().all { char -> !char.isDigit() } }

}