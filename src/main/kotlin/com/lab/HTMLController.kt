package com.lab

import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
import kotlin.browser.document
import kotlin.browser.window

class HTMLController {
    private val insertTextLabel = document.getElementById("insert_text_label") as HTMLInputElement
    private val doEquationButton = document.getElementById("do_equation_button") as HTMLButtonElement
    private var equation: String = ""

    fun onClick() {
        doEquationButton.addEventListener("click", {
            equation = insertTextLabel.value
            if (isStringAnEquation(equation)) {
                val calc = Calculator()
                var str = calc.run(equation)
                window.alert("Equation in onp: $str")
                val result = calc.calculate(str)
                window.alert("Equation result: $result")
            }
            else {
                window.alert("Introduced string is not an equation")
            }
        })
    }

    private fun isStringAnEquation(p_equation: String): Boolean {
        if (p_equation.isEmpty())
            return false

        var temp = true
        for (character in p_equation) {
            when (character) {
                '0','1','2','3','4','5','6','7','8','9',
                    '(',')','-','+','*','/','%','^' -> temp = true
                else -> return false
            }
        }
        return temp
    }
}