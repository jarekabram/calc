package com.lab

import kotlin.math.pow

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
                if (operators.isEmpty() || priority(character) > priority(operators.peek())) {
                    operators.push(character)
                }
                else {
                    while (priority(operators.peek()) >= priority(character)) {
                        output += "${operators.peek()} "
                        operators.pop()
                    }
                    operators.push(character)
                }
            }
            else {
                if((character in '0'..'9') &&
                   (lastCharacter.lastOrNull() in '0'..'9' || lastCharacter == "")) {
                    output += "$character"
                }
                else {
                    output += "$character "
                }
            }

            lastCharacter += character
        }
        while (!operators.isEmpty()) {
            output += "${operators.peek()} "
            operators.pop()
        }

        return output
    }

    fun calculate(input: String): Double?
    {
        var stackDouble: Stack<Double> = Stack()
        var result = 0.0
        var temp = ""
        for (character in input) {
            if (isOperator(character)) {
                if(temp.lastOrNull() in '0'..'9') {
                    stackDouble.push(temp.toDouble())
                    temp = ""
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
        }
        return result
    }

    private fun doEquation(stack: Stack<Double>, c: Char): Double {
        var temp = 0.0
        var d: Double

        when (c) {
            '-' -> {
                temp = stack.peek()!!
                stack.pop()
                d = stack.peek()!!
                temp -= d.minus(temp)
                stack.replaceTop(temp)
            }
            '+' -> {
                temp = stack.peek()!!
                stack.pop()
                d = stack.peek()!!
                temp = d.plus(temp)
                stack.replaceTop(temp)
            }
            '*' -> {
                temp = stack.peek()!!
                stack.pop()
                d = stack.peek()!!
                temp = d.times(temp)
                stack.replaceTop(temp)
            }
            '/' -> {
                temp = stack.peek()!!
                stack.pop()
                d = stack.peek()!!
                temp = d.div(temp)
                stack.replaceTop(temp)
            }
            '%' -> {
                temp = stack.peek()!!
                stack.pop()
                d = stack.peek()!!
                temp = d.rem(temp)
                stack.replaceTop(temp)
            }
            '^' -> {
                temp = stack.peek()!!
                stack.pop()
                d = stack.peek()!!
                temp = d.pow(temp)
                stack.replaceTop(temp)
            }
        }
        return temp
    }

    private fun priority(peek: Char?): Int {
        return when (peek) {
            '^' -> 3
            '*', '/' -> 2
            '+', '-', ')' -> 1
            else -> 0
        }
    }

    private fun isOperator(character: Char): Boolean {
        return when (character) {
            '-', '+', '*', '/', '%', '^' -> true
            else -> false
        }
    }
}