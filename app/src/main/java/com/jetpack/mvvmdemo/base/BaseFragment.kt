package com.jetpack.mvvmdemo.base

import androidx.databinding.ViewDataBinding
import com.jason.mvvm.base.dialog.BaseDialog
import com.jason.mvvm.base.fragment.BaseVmDbFragment
import com.jason.mvvm.base.viewmodel.BaseViewModel
import com.jetpack.mvvmdemo.widget.LoadingDialog


/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/07/03  15:49
 *  desc   : 项目Fragment基类 . 如果不想使用DataBind,可继承 [com.jason.mvvm.base.fragment.BaseVmFragment]
 *  version: 1.0
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> :BaseVmDbFragment<VM,DB>(){
    private var mBaseDialog : BaseDialog?=null

    /**
     * 创建liveData观察者
     */
    override fun createObserver() {}


    override fun showLoading(message: String) {
        mBaseDialog?:let {
            mBaseDialog = LoadingDialog.Builder(mActivity)
                .setMessage(message)
                .setCancelable(true).create()
        }
        mBaseDialog?.show()
    }

    override fun dismissLoading() {
        mBaseDialog?.dismiss()
    }

}