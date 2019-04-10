package com.lab

import kotlin.browser.window
import kotlin.math.pow
import kotlin.math.sqrt

open class Calculator {

    fun run(input: String): String {

        val operators: Stack<Char> = Stack()
        var output = ""

        var lastCharacter = ""
        for (character in input) {
            if (character == '(') {
                if(lastCharacter.lastOrNull() in '0'..'9') {
                    output += " "
                }
                operators.push(character)
            }
            else if (character == ')') {
                if(lastCharacter.lastOrNull() in '0'..'9') {
                    output += " "
                }
                while (operators.peek() != '(') {
                    output += "${operators.peek()} "
                    operators.pop()
                }
                operators.pop()
            }
            else if (isOperator(character)) {
                if(lastCharacter.lastOrNull() in '0'..'9') {
                    output += " "
                }
                if((lastCharacter == "+"
                    || lastCharacter =="*" || lastCharacter =="/" || lastCharacter =="%"
                    || lastCharacter == "^" ) && character.toString() == "-" ) {

                    output += "-"
                }
                else {
                    if (operators.isEmpty() || priority(character) > priority(operators.peek())) {
                        operators.push(character)
                    } else {
                        while (priority(operators.peek()) >= priority(character)) {
                            output += "${operators.peek()} "
                            operators.pop()
                        }
                        operators.push(character)
                    }
                }
            }
            else {
                if((character in '0'..'9') &&
                   (lastCharacter.lastOrNull() in '0'..'9' || lastCharacter == "")
                    || lastCharacter == "-" || lastCharacter == "+"
                    || lastCharacter =="*" || lastCharacter =="/" || lastCharacter =="%"
                    || lastCharacter == "^" || lastCharacter == "v") {
                    output += "$character"
                }
                else {
                    output += "$character "
                }
            }

            lastCharacter = character.toString()
        }
        while (!operators.isEmpty()) {
            output += " ${operators.peek()}"
            operators.pop()
        }

        return output
    }

    fun calculate(input: String): Double?
    {
        var stackDouble: Stack<Double> = Stack()
        var result = 0.0
        var temp = ""
        var it = 1
        for (character in input) {
            if (isOperator(character)) {
                if(temp.lastOrNull() in '0'..'9') {
                    stackDouble.push(temp.toDouble())
                    temp = ""
                }
                if(character == '-' && input[it] != ' ') {
                    temp += character
                    if(it == input.length) {
                        result = doEquation(stackDouble, character)
                    }
                    continue
                }
                result = doEquation(stackDouble, character)
            }
            else if (character == ' ') {
                if(temp.lastOrNull() in '0'..'9') {
                    stackDouble.push(temp.toDouble())
                    temp = ""
                }
            }
            else {
                temp += character
            }
            it += 1
        }
        return result
    }

    private fun doEquation(stack: Stack<Double>, c: Char): Double {
        var temp = 0.0
        var d: Double

        if(stack.isEmpty()) {
            window.alert("Impossible operation")
            throw Exception("There were no numbers provided, please type another equation")
        }
        when (c) {
            '-' -> {
                temp = stack.peek()!!
                if(stack.size() >= 2) {
                    stack.pop()
                    d = stack.peek()!!
                    temp = d - temp
                    stack.replaceTop(temp)
                }
                else {
                    window.alert("Impossible operation")
                    throw Exception("Impossible operation")
                }
            }
            '+' -> {
                temp = stack.peek()!!
                if(stack.size() >= 2) {
                    stack.pop()
                    d = stack.peek()!!
                    temp = d + temp
                    stack.replaceTop(temp)
                }
                else {
                    window.alert("Impossible operation")
                    throw Exception("Impossible operation")
                }
            }
            '*' -> {
                temp = stack.peek()!!
                if(stack.size() >= 2) {
                    stack.pop()
                    d = stack.peek()!!
                    temp = d * temp
                    stack.replaceTop(temp)
                }
                else {
                    window.alert("Impossible operation")
                    throw Exception("Impossible operation")
                }

            }
            '/' -> {
                temp = stack.peek()!!
                if(stack.size() >= 2) {
                    stack.pop()
                    d = stack.peek()!!
                    temp = d / temp
                    stack.replaceTop(temp)
                }
                else {
                    window.alert("Impossible operation")
                    throw Exception("Impossible operation")
                }
            }
            '%' -> {
                temp = stack.peek()!!
                if(stack.size() >= 2) {
                    stack.pop()
                    d = stack.peek()!!
                    temp = d%temp
                    stack.replaceTop(temp)
                }
                else {
                    window.alert("Impossible operation")
                    throw Exception("Impossible operation")
                }
            }
            '^' -> {
                temp = stack.peek()!!
                if(stack.size() >= 2) {
                    stack.pop()
                    d = stack.peek()!!
                    temp = d.pow(temp)
                    stack.replaceTop(temp)
                }
                else {
                    window.alert("Impossible operation")
                    throw Exception("Impossible operation")
                }

            }
            'v' -> {
                temp = stack.peek()!!
                temp = sqrt(temp)
                stack.replaceTop(temp)
            }
        }
        return temp
    }

    private fun priority(peek: Char?): Int {
        return when (peek) {
            '^', 'v' -> 3
            '*', '/' -> 2
            '+', '-', ')' -> 1
            else -> 0
        }
    }

    private fun isOperator(character: Char): Boolean {
        return when (character) {
            '-', '+', '*', '/', '%', '^', 'v' -> true
            else -> false
        }
    }
}