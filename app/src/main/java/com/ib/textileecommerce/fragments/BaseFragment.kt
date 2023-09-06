package com.ib.textileecommerce.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.view.inputmethod.InputMethodManager
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

abstract class BaseFragment<T : ViewDataBinding> : Fragment(), BaseView {

    private lateinit var viewDataBinding: T
    private lateinit var onInteractionWithFragment: OnInteractionWithFragment

    protected fun getActivityComponent(): ActivityComponent {
        return TextileApplication.appComponent()
            .activityComponent(ViewModule(this))
    }

    protected fun bindViewData(): T {
        viewDataBinding = DataBindingUtil.setContentView(requireActivity(), getLayoutId())
        viewDataBinding.lifecycleOwner = this
        getViewModel().setViewInterface(this)
        setUpSnackBar()
        return viewDataBinding
    }

    private fun setUpSnackBar() {
        getViewModel().getMutableSnackBar().observe(this, Observer { msg ->
            msg?.let {
                Snackbar.make(requireActivity().currentFocus!!, msg, Snackbar.LENGTH_LONG).show()
                getViewModel().afterSnackBarShow()
            }
        })
    }

    /*override fun showMsg(msgId: Int) {
        if (activity != null && requireActivity() is HomeActivity)
            (requireActivity() as HomeActivity).showMsg(msgId)
        else if (activity != null && requireActivity() is FollowingFollowersActivity)
            (requireActivity() as FollowingFollowersActivity).showMsg(msgId)
        else if (activity != null && requireActivity() is UserProfileActivity)
            (requireActivity() as UserProfileActivity).showMsg(msgId)
        else if (activity != null && requireActivity() is MyProfileActivity)
            (requireActivity() as MyProfileActivity).showMsg(msgId)
    }

    override fun showMsg(msg: String) {
        if (activity != null && requireActivity() is HomeActivity)
            (requireActivity() as HomeActivity).showMsg(msg)
        else if (activity != null && requireActivity() is FollowingFollowersActivity)
            (requireActivity() as FollowingFollowersActivity).showMsg(msg)
        else if (activity != null && requireActivity() is UserProfileActivity)
            (requireActivity() as UserProfileActivity).showMsg(msg)
        else if (activity != null && requireActivity() is MyProfileActivity)
            (requireActivity() as MyProfileActivity).showMsg(msg)
    }

    override fun hideKeyboard() {
        if (activity != null && requireActivity() is HomeActivity)
            (requireActivity() as HomeActivity).hideKeyboard()
        else if (activity != null && requireActivity() is FollowingFollowersActivity)
            (requireActivity() as FollowingFollowersActivity).hideKeyboard()
        else if (activity != null && requireActivity() is UserProfileActivity)
            (requireActivity() as UserProfileActivity).hideKeyboard()
        else if (activity != null && requireActivity() is MyProfileActivity)
            (requireActivity() as MyProfileActivity).hideKeyboard()
    }*/

    override fun onSuccess(msg: String) {
        showMessage(msg)
    }

    /*override fun onFailed(msg: String, error: Int) {
        if (activity != null && requireActivity() is HomeActivity)
            (requireActivity() as HomeActivity).onFailed(msg, error)
        else if (activity != null && requireActivity() is FollowingFollowersActivity)
            (requireActivity() as FollowingFollowersActivity).onFailed(msg, error)
        else if (activity != null && requireActivity() is UserProfileActivity)
            (requireActivity() as UserProfileActivity).onFailed(msg, error)
        else if (activity != null && requireActivity() is MyProfileActivity)
            (requireActivity() as MyProfileActivity).onFailed(msg, error)
    }

    override fun onHandleException(e: Throwable) {
        if (activity != null && requireActivity() is HomeActivity)
            (requireActivity() as HomeActivity).onHandleException(e)
        else if (activity != null && requireActivity() is FollowingFollowersActivity)
            (requireActivity() as FollowingFollowersActivity).onHandleException(e)
        else if (activity != null && requireActivity() is UserProfileActivity)
            (requireActivity() as UserProfileActivity).onHandleException(e)
        else if (activity != null && requireActivity() is MyProfileActivity)
            (requireActivity() as MyProfileActivity).onHandleException(e)
    }*/

    /*override fun isInternetAvailable() =
        if (activity != null) {
            when {
                requireActivity() is HomeActivity -> (requireActivity() as HomeActivity).isInternetAvailable()
                requireActivity() is FollowingFollowersActivity -> (requireActivity() as FollowingFollowersActivity).isInternetAvailable()
                requireActivity() is UserProfileActivity -> (requireActivity() as UserProfileActivity).isInternetAvailable()
                requireActivity() is MyProfileActivity -> (requireActivity() as MyProfileActivity).isInternetAvailable()
                else -> false
            }
        } else false*/

    fun openActivity(activity: Activity, destinationActivity: Activity) {
        startActivity(Intent(activity, destinationActivity::class.java))
        activity.overridePendingTransition(R.anim.anim_screen_enter, R.anim.anim_screen_exit)
    }

    fun openActivity(intent: Intent) {
        startActivity(intent)
        requireActivity().overridePendingTransition(
            R.anim.anim_screen_enter,
            R.anim.anim_screen_exit
        )
    }

    fun openActivityWithResultCode(
        activity: Activity,
        destinationActivity: Activity,
        resultCode: Int
    ) {
        startActivityForResult(Intent(activity, destinationActivity::class.java), resultCode)
        activity.overridePendingTransition(R.anim.anim_screen_enter, R.anim.anim_screen_exit)
    }

    fun openActivityForResult(activity: Activity, intent: Intent, resultCode: Int) {
        startActivityForResult(intent, resultCode)
        activity.overridePendingTransition(R.anim.anim_screen_enter, R.anim.anim_screen_exit)
    }

    fun finishActivity(activity: Activity) {
        activity.finish()
        activity.overridePendingTransition(
            R.anim.anim_screen_left_to_right,
            R.anim.anim_screen_right_to_left
        )
    }

    fun addFragment(fragment: Fragment, tag: String) {
        requireActivity().supportFragmentManager.beginTransaction().addToBackStack(tag)
            .replace(R.id.frameContainer, fragment, tag)
            .commitAllowingStateLoss()
    }

    fun addFragmentChild(childFragmentManager: FragmentManager, fragment: Fragment, tag: String) {
        childFragmentManager.beginTransaction().addToBackStack(tag)
            .add(R.id.frameContainer, fragment, tag)
            .commitAllowingStateLoss()
    }

    /**
     * Remove all fragment from stack
     */
    fun removeAllFragment(fragmentManager: FragmentManager) {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun showMessage(messageId: Int) {
        hideKeyboard()
        showAlert(getString(messageId))
    }

    override fun showMessage(message: String) {
        hideKeyboard()
        showAlert(message)
    }

    override fun hideKeyboard() {
        requireActivity().runOnUiThread {
            val inputMethodManager: InputMethodManager =
                TextileApplication.getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (requireActivity().currentFocus != null) {
                inputMethodManager.hideSoftInputFromWindow(
                    requireActivity().currentFocus!!.windowToken,
                    0
                )
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
            requireActivity().runOnUiThread {
                showMessage(msg)
            }
        }
        e.printStackTrace()
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
        SessionManager(TextileApplication.getContext()).logout()
        /*val intent = Intent(this@BaseActivity, LoginActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        openActivity(intent)*/
    }

    private fun showAlert(msg: String) {
        requireActivity().runOnUiThread {
            object : CustomAlertDialog(
                requireActivity(),
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
        val cm =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

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
            TextileApplication.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
}