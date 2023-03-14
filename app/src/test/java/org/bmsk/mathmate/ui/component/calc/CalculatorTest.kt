package org.bmsk.mathmate.ui.component.calc

import org.junit.Assert.assertEquals
import org.junit.Test

internal class CalculatorTest {
    private val evaluator = Calculator()

    @Test
    fun testSimpleAddition() {
        val result = evaluator.calculate("111+222")
        assertEquals(333.0, result, 0.0)
    }

    @Test
    fun testSimpleSubtraction() {
        val result = evaluator.calculate("5-2")
        assertEquals(3.0, result, 0.0)
    }

    @Test
    fun testSimpleMultiplication() {
        val result = evaluator.calculate("4*3")
        assertEquals(12.0, result, 0.0)
    }

    @Test
    fun testSimpleDivision() {
        val result = evaluator.calculate("15/3")
        assertEquals(5.0, result, 0.0)
    }

    @Test
    fun testMixedOperators() {
        val result = evaluator.calculate("2+3*4")
        assertEquals(14.0, result, 0.0)
    }

    @Test
    fun testParentheses() {
        val result = evaluator.calculate("(2+3)*4")
        assertEquals(20.0, result, 0.0)
    }

    @Test
    fun testDecimalNumbers() {
        val result = evaluator.calculate("2.5+3.5")
        assertEquals(6.0, result, 0.0)
    }

    @Test
    fun testNegativeNumbers() {
        val result = evaluator.calculate("-2+3")
        assertEquals(1.0, result, 0.0)
    }

    @Test
    fun testComplexExpression() {
        val result = evaluator.calculate("2*(3+4)/(1+2)")
        assertEquals(4.0, result, 0.0)
    }
}