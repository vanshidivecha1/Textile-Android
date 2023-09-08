package com.ib.textileecommerce.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.ib.textileecommerce.R
import com.ib.textileecommerce.application.TextileApplication
import com.ib.textileecommerce.components.ActivityComponent
import com.ib.textileecommerce.interfaces.OnInteractionWithFragment
import com.ib.textileecommerce.module.ViewModule
import com.ib.textileecommerce.utils.CustomAlertDialog
import com.ib.textileecommerce.utils.SessionManager
import com.ib.textileecommerce.views.BaseView
import retrofit2.HttpException

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(), BaseView {

    private lateinit var viewDataBinding: T
    private lateinit var onInteractionWithFragment: OnInteractionWithFragment

    protected fun getActivityComponent(): ActivityComponent {
        return TextileApplication.appComponent().activityComponent(ViewModule(this))
    }

    /** Bind Data to View */
    protected fun bindViewData(): T {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding.lifecycleOwner = this
        getViewModel().setViewInterface(this)
        setUpSnackBar()
        return viewDataBinding
    }

    override fun showMessage(messageId: Int) {
        hideKeyboard()
        showAlert(getString(messageId))
    }

    override fun showMessage(message: String) {
        hideKeyboard()
        showAlert(message)
    }

    private fun setUpSnackBar() {
        getViewModel().getMutableSnackBar().observe(this, Observer { msg ->
            msg?.let {
                Snackbar.make(currentFocus!!, msg, Snackbar.LENGTH_LONG).show()
                getViewModel().afterSnackBarShow()
            }
        })
    }

    /**
     * Hide Keyboard
     */
    override fun hideKeyboard() {
        runOnUiThread {
            val inputMethodManager: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (currentFocus != null) {
                inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
        }
    }

    /**
     * Handle exception by code
     */
    override fun onHandleException(e: Throwable) {
        var msg = ""

        if (e is java.net.UnknownHostException) {
            msg = getString(R.string.no_internet)
            Log.e("BaseActivity", "no_internet ->" + e.message)
        } else if (e is java.net.SocketTimeoutException || e is java.net.ConnectException) {
            msg = getString(R.string.slow_internet)
            Log.e("BaseActivity", "slow_internet ->" + e.message)
        } else if (e is HttpException) {
            Log.e("BaseActivity", "Server code ->" + e.code())
            Log.e("BaseActivity", "Server response ->" + e.message)
            if (e.code() == 500) {
                msg = getString(R.string.server_error)
            } else if (e.code() == 404) {
                //   msg = getString(R.string.server_error)
                showMessage(e.message.toString())
            }
        }

        if (!TextUtils.isEmpty(msg)) {
            runOnUiThread {
                showMessage(msg)
            }
        }
        e.printStackTrace()
    }

    /**
     * Api call Success Response
     */
    override fun onSuccess(msg: String) {
        showMessage(msg)
    }

    /**
     * Api call failure response
     */
    override fun onFailed(msg: String, error: Int) {
        Log.e("BaseActivity", "onFailed  error: $error")
        Log.e("BaseActivity", "onFailed  msg: $msg")

        showMessage(msg)
    }

    /**
     * Successfully logout click
     */
    fun logoutAndRedirectSignUp() {
        SessionManager(this@BaseActivity).logout()
        /*val intent = Intent(this@BaseActivity, LoginActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        openActivity(intent)*/
    }

    /**
     * Add fragment on activity or fragment
     */
    fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainer, fragment, tag)
            .addToBackStack(tag)
            .commitAllowingStateLoss()
    }

    /**
     * Add Child fragment- fragment in fragment
     */
    fun addFragmentChild(childFragmentManager: FragmentManager, fragment: Fragment, tag: String) {
        childFragmentManager.beginTransaction().addToBackStack(tag)
            .add(R.id.frameContainer, fragment, tag)
            .commit()
    }

    /**
     * Show alert dialog
     */
    private fun showAlert(msg: String) {
        runOnUiThread {
            object : CustomAlertDialog(
                this,
                msg, getString(R.string.ok), ""
            ) {
                override fun onBtnClick(id: Int) {
                }
            }.show()
        }
    }

    /**
     * Override method to check internet is available or not
     */
    override fun isInternetAvailable(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT < 23) {
            val ni = cm.activeNetworkInfo

            if (ni != null) {
                val isInternetAvail =
                    ni.isConnected && (ni.type == ConnectivityManager.TYPE_WIFI || ni.type == ConnectivityManager.TYPE_MOBILE)
                TextileApplication.appComponent().sessionManager()
                    .setInternetAvailable(isInternetAvail)
                return isInternetAvail
            }
        } else {
            val n = cm.activeNetwork
            if (n != null) {
                if (Build.VERSION.SDK_INT == 28) {
                    if (isNetworkConnected()) {
                        TextileApplication.appComponent().sessionManager()
                            .setInternetAvailable(isNetworkConnected())
                    }
                    return isNetworkConnected()
                } else {
                    val nc = cm.getNetworkCapabilities(n)
                    var isInternetAvail = false
                    if (nc != null) {
                        isInternetAvail =
                            nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                                NetworkCapabilities.TRANSPORT_WIFI
                            )
                    }
                    TextileApplication.appComponent().sessionManager()
                        .setInternetAvailable(isInternetAvail)
                    return isInternetAvail
                }
            }
        }
        return false
    }

    /**
     * Check whether device is connected to network or not
     */
    private fun isNetworkConnected(): Boolean {
//        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
        var result = false
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }

    /**
     * start one activity from other with animation
     */
    fun openActivity(activity: Activity, destinationActivity: Activity) {
        startActivity(Intent(activity, destinationActivity::class.java))
        overridePendingTransition(R.anim.anim_screen_enter, R.anim.anim_screen_exit)
    }

    /**
     * start activity using intent (if want to pass data using intent)
     */
    fun openActivity(intent: Intent) {
        startActivity(intent)
        overridePendingTransition(R.anim.anim_screen_enter, R.anim.anim_screen_exit)
    }

    /**
     * start activity when we have result code
     */
    fun openActivityForResult(intent: Intent, resultCode: Int) {
        startActivityForResult(intent, resultCode)
        overridePendingTransition(R.anim.anim_screen_enter, R.anim.anim_screen_exit)
    }

    /**
     * finish activity with intent
     */
    fun finishActivity() {
        finish()
        overridePendingTransition(
            R.anim.anim_screen_left_to_right,
            R.anim.anim_screen_right_to_left
        )
    }

    fun setListenerOfInteractionWithFragment(onInteractionWithFragment: OnInteractionWithFragment) {
        this.onInteractionWithFragment = onInteractionWithFragment
    }

    fun getOnInteractionWithFragment() =
        if (::onInteractionWithFragment.isInitialized) onInteractionWithFragment else null
}