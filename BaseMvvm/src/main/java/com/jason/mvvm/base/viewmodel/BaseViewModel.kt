package com.jason.mvvm.base.viewmodel

import androidx.lifecycle.ViewModel
import com.jason.mvvm.ext.livedata.event.EventLiveData

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/06/23  14:05
 *  desc   : ViewModel的基类
 *  version: 1.0
 */
//open class BaseViewModel(application: Application):AndroidViewModel(application) {
open class BaseViewModel: ViewModel() {

    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }

    /**
     * 内置封装好的可通知Activity/fragment 显示隐藏加载框 需要跟网络请求显示隐藏loading配套才加的，
     */
    inner class UiLoadingChange{
        //显示加载框
        val showDialog by lazy { EventLiveData<String>() }
        //隐藏
        val dismissDialog by lazy { EventLiveData<Boolean>() }
    }
}