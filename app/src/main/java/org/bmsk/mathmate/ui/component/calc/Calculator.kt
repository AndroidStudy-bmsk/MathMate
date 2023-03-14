package org.bmsk.mathmate.ui.component.calc

import java.util.*

class Calculator {
    fun calculate(expression: String): Int {
        val numbers = Stack<Int>()
        val operators = Stack<Char>()
        val precedence = mapOf('+' to 1, '-' to 1, '*' to 2, '/' to 2)

        var i = 0
        while (i < expression.length) {
            val ch = expression[i]
            when {
                ch.isDigit() -> {
                    var num = ch.toString()
                    while (i + 1 < expression.length && expression[i + 1].isDigit()) {
                        num += expression[i + 1]
                        i++
                    }
                    numbers.push(num.toInt())
                }
                ch == '(' -> operators.push(ch)
                ch == ')' -> {
                    while (operators.peek() != '(') {
                        val result = applyOperation(operators.pop(), numbers.pop(), numbers.pop())
                        numbers.push(result)
                    }
                    operators.pop()
                }
                ch in precedence.keys -> {
                    while (!operators.empty() && operators.peek() != '(' && precedence[operators.peek()]!! >= precedence[ch]!!) {
                        val result = applyOperation(operators.pop(), numbers.pop(), numbers.pop())
                        numbers.push(result)
                    }
                    operators.push(ch)
                }
            }
            i++
        }

        while (!operators.empty()) {
            val result = applyOperation(operators.pop(), numbers.pop(), numbers.pop())
            numbers.push(result)
        }

        return numbers.pop()
    }

    private fun applyOperation(operator: Char, b: Int, a: Int): Int {
        when (operator) {
            '+' -> return a + b
            '-' -> return a - b
            '*' -> return a * b
            '/' -> return a / b
        }
        return 0
    }
}