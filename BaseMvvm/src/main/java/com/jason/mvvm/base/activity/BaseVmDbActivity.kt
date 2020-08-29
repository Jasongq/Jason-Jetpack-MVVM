package com.jason.mvvm.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.jason.mvvm.base.viewmodel.BaseViewModel

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/06/23  15:56
 *  desc   : 包含Viewmodel 和Databind ViewModelActivity基类，把ViewModel 和Databind注入进来
 *           需要使用Databind的清继承它
 *  version: 1.0
 */
abstract class BaseVmDbActivity<VM : BaseViewModel,DB : ViewDataBinding> :BaseVmActivity<VM>()  {
    lateinit var mDatabind :DB
    override fun onCreate(savedInstanceState: Bundle?) {
        userDataBinding(true)
        super.onCreate(savedInstanceState)
    }

    override fun initDataBind() {
        mDatabind= DataBindingUtil.setContentView<DB>(this,layoutId())
        mDatabind.lifecycleOwner=this
    }
}