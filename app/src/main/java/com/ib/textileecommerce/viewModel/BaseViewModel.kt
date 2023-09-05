package com.ib.textileecommerce.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ib.textileecommerce.networking.NetworkService
import com.ib.textileecommerce.views.BaseView
import java.lang.ref.WeakReference

open class BaseViewModel constructor(
    private val networkService: NetworkService, private val baseView: BaseView
) : ViewModel() {

    private val mIsLoading = ObservableBoolean(false)
    private lateinit var viewInterface: WeakReference<BaseView>

    fun getIsLoading(): ObservableBoolean {
        return mIsLoading
    }

    fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }

    fun getViewInterface(): BaseView? {
        return baseView
    }

    fun setViewInterface(viewInterface: BaseView) {
        this.viewInterface = WeakReference(viewInterface)
    }

    fun networkService() = networkService

    private val mutableSnackBar = MutableLiveData<String>()

    fun showSnackBar(msg: String) {
        mutableSnackBar.postValue(msg)
    }

    fun afterSnackBarShow() {
        mutableSnackBar.value = null
    }

    fun getMutableSnackBar() = mutableSnackBar
}