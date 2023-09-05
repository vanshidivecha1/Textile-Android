package com.ib.textileecommerce.utils

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.Window
import com.ib.textileecommerce.R
import com.ib.textileecommerce.customViews.CustomTextView

/**
 * Created by Sakshi Soni
 */
abstract class CustomAlertDialog(
    activity: Activity,
    private val msg: String,
    private val posBtnName: String,
    private val negBtnName: String
) : Dialog(activity, R.style.MyDialogTheme) {

    private val tvAlertMsg = findViewById<CustomTextView>(R.id.tvAlertMsg)
    private val btnView = findViewById<CustomTextView>(R.id.btnView)
    private val btnPositive = findViewById<CustomTextView>(R.id.btnPositive)
    private val btnNegative = findViewById<CustomTextView>(R.id.btnNegative)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_custom_alert_dialog)
        setCancelable(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        tvAlertMsg.text = msg
        btnPositive.text = posBtnName
        btnNegative.text = negBtnName
        btnPositive.visibility = if (!TextUtils.isEmpty(posBtnName)) View.VISIBLE else View.GONE
        btnNegative.visibility = if (!TextUtils.isEmpty(negBtnName)) View.VISIBLE else View.GONE

        btnPositive.setOnClickListener { v ->
            dismiss()
            onBtnClick(v.id)
        }

        btnNegative.setOnClickListener { v ->
            dismiss()
            onBtnClick(v.id)
        }

        btnView.setOnClickListener { v ->
            dismiss()
            onBtnClick(v.id)
        }
    }

    abstract fun onBtnClick(id: Int)
}