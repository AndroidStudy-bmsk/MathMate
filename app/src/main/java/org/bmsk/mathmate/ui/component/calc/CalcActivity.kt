package org.bmsk.mathmate.ui.component.calc

import android.view.View
import android.widget.Button
import android.widget.Toast
import org.bmsk.mathmate.databinding.ActivityCalcBinding
import org.bmsk.mathmate.ui.component.base.BaseActivity
import java.text.DecimalFormat

class CalcActivity : BaseActivity<ActivityCalcBinding>() {
    private val firstNumberText = StringBuilder("")
    private val secondNumberText = StringBuilder("")
    private val operatorText = StringBuilder("")
    private val decimalFormat = DecimalFormat("#,###")
    override fun getViewBinding(): ActivityCalcBinding {
        return ActivityCalcBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    fun onClickNumber(view: View) {
        val numberString = (view as? Button)?.text?.toString() ?: ""
        val numberText = if (operatorText.isEmpty()) firstNumberText else secondNumberText

        numberText.append(numberString)
        updateEquationTextView()
    }

    fun onClickClear(view: View) {
        firstNumberText.clear()
        secondNumberText.clear()
        operatorText.clear()

        updateEquationTextView()
        binding.tvResult.text = ""
    }

    fun onClickEqual(view: View) {
        if (firstNumberText.isEmpty() || secondNumberText.isEmpty() || operatorText.isEmpty()) {
            Toast.makeText(this, "올바르지 않은 수식입니다.", Toast.LENGTH_SHORT).show()
            return
        }
        val firstNumber = firstNumberText.toString().toBigDecimal()
        val secondNumber = secondNumberText.toString().toBigDecimal()

        val result = when (operatorText.toString()) {
            "+" -> decimalFormat.format(firstNumber + secondNumber)
            "-" -> decimalFormat.format(firstNumber - secondNumber)
            else -> "잘못된 수식 입니다."
        }

        binding.tvResult.text = result
    }

    fun onClickOperator(view: View) {
        val operatorString = (view as? Button)?.text?.toString() ?: ""

        if (firstNumberText.isEmpty()) {
            Toast.makeText(this, "먼저 숫자를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (secondNumberText.isNotEmpty()) {
            Toast.makeText(this, "한 개의 연산자에 대한 연산만 지원됩니다.", Toast.LENGTH_SHORT).show()
            return
        }

        operatorText.append(operatorString)
        updateEquationTextView()
    }

    private fun updateEquationTextView() {
        val firstFormattedNumber = if (firstNumberText.isNotEmpty()) decimalFormat.format(
            firstNumberText.toString().toBigDecimal()
        ) else ""
        val secondFormattedNumber = if (secondNumberText.isNotEmpty()) decimalFormat.format(
            secondNumberText.toString().toBigDecimal()
        ) else ""

        binding.tvEquation.text = "$firstFormattedNumber $operatorText $secondFormattedNumber"
    }
}
