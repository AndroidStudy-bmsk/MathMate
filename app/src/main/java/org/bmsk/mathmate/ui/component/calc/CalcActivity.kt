package org.bmsk.mathmate.ui.component.calc

import android.util.Log
import android.view.View
import android.widget.Button
import org.bmsk.mathmate.databinding.ActivityCalcBinding
import org.bmsk.mathmate.ui.component.base.BaseActivity

class CalcActivity : BaseActivity<ActivityCalcBinding>() {
    private val firstNumberText = StringBuilder("")
    private val secondNumberText = StringBuilder("")
    private val operatorText = StringBuilder("")

    override fun getViewBinding(): ActivityCalcBinding {
        return ActivityCalcBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    fun onClickNumber(view: View) {
        val numberString = (view as? Button)?.text?.toString() ?: ""
        val numberText = if(operatorText.isEmpty()) firstNumberText else secondNumberText

        numberText.append(numberString)
        updateEquationTextView()
    }

    fun onClickClear(view: View) {

    }

    fun onClickEqual(view: View) {

    }

    fun onClickOperator(view: View) {

    }

    private fun updateEquationTextView() {
        binding.tvEquation.text =
            "$firstNumberText $operatorText $secondNumberText"
    }
}