package com.jetpack.mvvmdemo.base

import android.view.Gravity
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.hjq.toast.ToastUtils
import com.hjq.toast.style.ToastAliPayStyle
import com.jaeger.library.StatusBarUtil
import com.jason.mvvm.base.activity.BaseVmActivity
import com.jason.mvvm.base.activity.BaseVmDbActivity
import com.jason.mvvm.base.dialog.BaseDialog
import com.jason.mvvm.base.viewmodel.BaseViewModel
import com.jetpack.mvvmdemo.widget.LoadingDialog
import com.jason.mvvm.util.NetworkUtil
import com.jason.mvvm.util.logd
import com.jetpack.mvvmdemo.viewmodel.EventViewModel
import com.jetpack.mvvmdemo.viewmodel.SharedViewModel
import com.jetpack.mvvmdemo.widget.ToastDialog

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/07/03  15:49
 *  desc   : 项目Activity基类 . 如果不想使用DataBind,可继承 [BaseVmActivity]
 *  version: 1.0
 */
abstract class BaseActivity<VM:BaseViewModel,DB: ViewDataBinding>:BaseVmDbActivity<VM,DB>() {
    private var mBaseDialog : BaseDialog?=null
    /**里面存放了一些账户信息，基本配置信息等*/
    val sharedViewModel:SharedViewModel by lazy { SharedViewModel() }
    /**用于发送全局通知操作*/
    val eventViewModel:EventViewModel by lazy { EventViewModel() }

    override fun initOtherSetting(){
        setStatusBarColor(ContextCompat.getColor(this, android.R.color.white), 0)
        //字体颜色
        StatusBarUtil.setLightMode(this)
    }

    /**
     * 设置状态栏颜色
     * @param color
     * @param statusBarAlpha 透明度
     */
    open fun setStatusBarColor(@ColorInt color: Int, @IntRange(from = 0, to = 255) statusBarAlpha: Int) {
        /**无侧滑返回的使用设置方法 */
        //StatusBarUtil.setColor(this, color, statusBarAlpha);
        /**可以侧滑返回的设置方法 */
        StatusBarUtil.setTranslucentForImageView(this, statusBarAlpha, null)
        /* 7.0及以下系统状态栏高度有问题
        StatusBarUtil.setColorForSwipeBack(this, color,statusBarAlpha);*/
    }

    /**
     * 创建liveData观察者
     */
    override fun createObserver() {}

    override fun onNetworkStateChanged(netState: NetworkUtil.NetworkType) {
        super.onNetworkStateChanged(netState)
        "网络状态：$netState".logd()
    }

    override fun onAppLifecycleChanged(isForeground: Boolean) {
        super.onAppLifecycleChanged(isForeground)
        "是否在前台：$isForeground".logd()
    }

    override fun showLoading(message: String) {
        mBaseDialog?:let {
            mBaseDialog = LoadingDialog.Builder(this)
                .setMessage(message)
                .setCancelable(true).create()
        }
        mBaseDialog?.show()
    }

    override fun dismissLoading() {
        mBaseDialog?.dismiss()
    }

    fun toast(@StringRes id: Int) {
        toast(getString(id))
    }

    fun toast(id: String?) {
        ToastUtils.initStyle(ToastAliPayStyle(application))
        ToastUtils.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 200)
        ToastUtils.show(id)
    }
    fun toastSuccess(msg: String?){
        ToastDialog.Builder(this)
            .setType(ToastDialog.Type.FINISH)
            .setMessage(msg)
            .show()
    }
    fun toastError(msg: String?){
        ToastDialog.Builder(this)
            .setType(ToastDialog.Type.ERROR)
            .setMessage(msg)
            .show()
    }
    fun toastWarning(msg: String?){
        ToastDialog.Builder(this)
            .setType(ToastDialog.Type.WARN)
            .setMessage(msg)
            .show()
    }

    /**
     * 适配屏幕时使用
     * 在任何情况下本来适配正常的布局突然出现适配失效，适配异常等问题，只要重写 Activity 的 getResources() 方法
     */
   /* override fun getResources(): Resources {
        if (Looper.myLooper()== Looper.getMainLooper()) {
            AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources()));//如果没有自定义需求用这个方法
//            AutoSizeCompat.autoConvertDensity(super.getResources(), 698f,
//                AutoSizeConfig.getInstance().screenWidth > AutoSizeConfig.getInstance().screenHeight) //如果有自定义需求就用这个方法

        }
        return super.getResources()
    }*/
}