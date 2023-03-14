package org.bmsk.mathmate.ui.component.calc

import java.util.*

class Calculator {
    private val precedence = mapOf('+' to 1, '-' to 1, '*' to 2, '/' to 2, '%' to 2)

    fun calculate(expression: String): Double {
        val postfixExpression = convertInfixToPostfix(expression.replace(" ",""))
        return evaluatePostfixExpression(postfixExpression)
    }

    private fun convertInfixToPostfix(infixExpression: String): String {
        val postfixExpression = StringBuilder()
        val operatorStack = Stack<Char>()

        infixExpression.forEach { char ->
            when {
                char.isDigit() -> postfixExpression.append(char)
                char == '(' -> operatorStack.push(char)
                char == ')' -> {
                    while (operatorStack.peek() != '(') {
                        postfixExpression.append(operatorStack.pop())
                    }
                    operatorStack.pop()
                }
                else -> {
                    while (operatorStack.isNotEmpty() && operatorStack.peek() != '(' && precedence[char]!! <= precedence[operatorStack.peek()]!!) {
                        postfixExpression.append(operatorStack.pop())
                    }
                    operatorStack.push(char)
                }
            }
        }

        while (operatorStack.isNotEmpty()) {
            postfixExpression.append(operatorStack.pop())
        }

        return postfixExpression.toString()
    }

    private fun evaluatePostfixExpression(postfixExpression: String): Double {
        val valueStack = Stack<Double>()

        postfixExpression.forEach { char ->
            when {
                char.isDigit() -> valueStack.push(char.toString().toDouble())
                char in setOf('+', '-', '*', '/', '%') -> {
                    val operand2 = valueStack.pop()
                    val operand1 = valueStack.pop()
                    val result = when (char) {
                        '+' -> operand1 + operand2
                        '-' -> operand1 - operand2
                        '*' -> operand1 * operand2
                        '/' -> operand1 / operand2
                        '%' -> operand1 % operand2
                        else -> throw IllegalArgumentException("Invalid operator: $char")
                    }
                    valueStack.push(result)
                }
                else -> throw IllegalArgumentException("Invalid character in expression: $char")
            }
        }

        return valueStack.pop()
    }
}