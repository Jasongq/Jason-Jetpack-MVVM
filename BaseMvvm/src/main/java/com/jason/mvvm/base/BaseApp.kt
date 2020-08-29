package com.jason.mvvm.base

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/07/03  11:48
 *  desc   : 此处写BaseApp，会提供一个很有用的功能--在Activity/fragment中获取Application级别的ViewModel，
 *  如果你不想继承BaseApp，又想获取Application级别的ViewModel功能
 * 那么你可以复制该类的代码到你的自定义Application中去，然后可以自己写获取Viewmodel的拓展函数即 :
 * GetViewModelExt类的getAppViewModel方法
 *  version: 1.0
 */
open class BaseApp :Application() ,ViewModelStoreOwner{

    private lateinit var mAppViewModelStore:ViewModelStore

    private var mFactory: ViewModelProvider.Factory ?=null

    override fun onCreate() {
        super.onCreate()
        mAppViewModelStore= ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    /**
     * 获取一个全局的ViewModel
     */
    fun getAppViewModelProvider():ViewModelProvider{
        return ViewModelProvider(this,this.getAppFactory())
    }

    private fun getAppFactory():ViewModelProvider.Factory{
        if (mFactory==null){
            mFactory=ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }

}