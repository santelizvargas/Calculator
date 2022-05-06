package com.santeliz.calculator.helpers

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.Toast

class ViewHelper {
    var viewGroup: ViewGroup? = null

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: ViewHelper? = null

        @Synchronized
        fun getInstance(): ViewHelper {
            if (instance === null) {
                instance = ViewHelper()
            }

            return instance!!
        }
    }

    fun showMessage(message: String) {
        Toast.makeText(viewGroup?.context, message, Toast.LENGTH_SHORT).show()
    }
}