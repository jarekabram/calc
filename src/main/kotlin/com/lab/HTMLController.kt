package com.lab

import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
import kotlin.browser.document
import kotlin.browser.window

class HTMLController {
    private val insertTextLabel = document.getElementById("insert_text_label") as HTMLInputElement
    private val doEquationButton = document.getElementById("do_equation_button") as HTMLButtonElement
    private val numberButton0 = document.getElementById("number_0_button") as HTMLButtonElement
    private val numberButton1 = document.getElementById("number_1_button") as HTMLButtonElement
    private val numberButton2 = document.getElementById("number_2_button") as HTMLButtonElement
    private val numberButton3 = document.getElementById("number_3_button") as HTMLButtonElement
    private val numberButton4 = document.getElementById("number_4_button") as HTMLButtonElement
    private val numberButton5 = document.getElementById("number_5_button") as HTMLButtonElement
    private val numberButton6 = document.getElementById("number_6_button") as HTMLButtonElement
    private val numberButton7 = document.getElementById("number_7_button") as HTMLButtonElement
    private val numberButton8 = document.getElementById("number_8_button") as HTMLButtonElement
    private val numberButton9 = document.getElementById("number_9_button") as HTMLButtonElement
    private val ceButton = document.getElementById("ce_button") as HTMLButtonElement
    private val operatorDivButton = document.getElementById("operator_div_button") as HTMLButtonElement
    private val operatorModButton = document.getElementById("operator_mod_button") as HTMLButtonElement
    private val operatorPowButton = document.getElementById("operator_pow_button") as HTMLButtonElement
    private val operatorSqrtButton = document.getElementById("operator_sqrt_button") as HTMLButtonElement
    private val operatorAddButton = document.getElementById("operator_add_button") as HTMLButtonElement
    private val operatorMultButton = document.getElementById("operator_mult_button") as HTMLButtonElement
    private val operatorClButton = document.getElementById("cl_button") as HTMLButtonElement
    private val operatorOpButton = document.getElementById("op_button") as HTMLButtonElement
    private val operatorSubsButton = document.getElementById("operator_subs_button") as HTMLButtonElement



    private var equation: String = ""

    fun onClick() {
        doEquationButton.addEventListener("click", {
            equation = insertTextLabel.value
            if (isStringAnEquation(equation)) {
                val calc = Calculator()
                var str = calc.run(equation)
//                window.alert("Equation: $str")
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
                    '(',')','-','+','*','/','%','^', 'v', '.' -> temp = true
                else -> return false
            }
        }
        return temp
    }

    fun clickNumberOrOperator() {
        val calculator = Calculator()
        numberButton0.addEventListener("click", {
            insertTextLabel.value += "0"
        })
        numberButton1.addEventListener("click", {
            insertTextLabel.value += "1"
        })
        numberButton2.addEventListener("click", {
            insertTextLabel.value += "2"
        })
        numberButton3.addEventListener("click", {
            insertTextLabel.value += "3"
        })
        numberButton4.addEventListener("click", {
            insertTextLabel.value += "4"
        })
        numberButton5.addEventListener("click", {
            insertTextLabel.value += "5"
        })
        numberButton6.addEventListener("click", {
            insertTextLabel.value += "6"
        })
        numberButton7.addEventListener("click", {
            insertTextLabel.value += "7"
        })
        numberButton8.addEventListener("click", {
            insertTextLabel.value += "8"
        })
        numberButton9.addEventListener("click", {
            insertTextLabel.value += "9"
        })
        ceButton.addEventListener("click", {
            insertTextLabel.value = ""
        })
        operatorDivButton.addEventListener("click", {
            insertTextLabel.value += "/"
        })
        operatorModButton.addEventListener("click", {
            insertTextLabel.value += "%"
        })
        operatorPowButton.addEventListener("click", {
            insertTextLabel.value += "^"
        })
        operatorSqrtButton.addEventListener("click", {
            insertTextLabel.value += "v"
        })
        operatorAddButton.addEventListener("click", {
            insertTextLabel.value += "+"
        })
        operatorMultButton.addEventListener("click", {
            insertTextLabel.value += "*"
        })
        operatorClButton.addEventListener("click", {
            insertTextLabel.value += ")"
        })
        operatorOpButton.addEventListener("click", {
            insertTextLabel.value += "("
        })
        operatorSubsButton.addEventListener("click", {
            insertTextLabel.value += "-"
        })

    }
}