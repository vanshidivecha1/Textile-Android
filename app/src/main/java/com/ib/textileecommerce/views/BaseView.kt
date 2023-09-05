package com.ib.textileecommerce.views

import androidx.annotation.LayoutRes
import com.ib.textileecommerce.viewModel.BaseViewModel

interface BaseView {
    fun showMessage(msgId: Int)

    fun showMessage(msg: String)

    fun hideKeyboard()

    fun onHandleException(e: Throwable)

    fun onSuccess(msg: String)

    fun onFailed(msg: String, error: Int)

    fun getViewModel(): BaseViewModel

    fun isInternetAvailable(): Boolean

    @LayoutRes
    fun getLayoutId(): Int

    fun getBindingVariable(): Int
}