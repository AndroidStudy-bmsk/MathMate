package org.bmsk.mathmate.ui.component.calc

import org.junit.Assert.assertEquals
import org.junit.Test

internal class CalculatorTest {
    private val evaluator = Calculator()

    @Test
    fun testSimpleAddition() {
        val result = evaluator.calculate("111+222")
        assertEquals(333, result)
    }

    @Test
    fun testSimpleSubtraction() {
        val result = evaluator.calculate("5-2")
        assertEquals(3, result)
    }

    @Test
    fun testSimpleMultiplication() {
        val result = evaluator.calculate("4*3")
        assertEquals(12, result)
    }

    @Test
    fun testSimpleDivision() {
        val result = evaluator.calculate("15/3")
        assertEquals(5, result)
    }

    @Test
    fun testMixedOperators() {
        val result = evaluator.calculate("2+3*4")
        assertEquals(14, result)
    }

    @Test
    fun testParentheses() {
        val result = evaluator.calculate("(2+3)*4")
        assertEquals(20, result)
    }

    @Test
    fun testDecimalNumbers() {
        val result = evaluator.calculate("2+3")
        assertEquals(5, result)
    }

    @Test
    fun testNegativeNumbers() {
        val result = evaluator.calculate("-2+3")
        assertEquals(1, result)
    }

    @Test
    fun testComplexExpression() {
        val result = evaluator.calculate("2*(3+4)/(1+2)")
        assertEquals(4, result)
    }
}