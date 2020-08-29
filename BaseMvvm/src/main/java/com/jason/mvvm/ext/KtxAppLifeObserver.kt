package com.jason.mvvm.ext

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.jason.mvvm.ext.livedata.UnPeekLiveData

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/06/24  17:25
 *  desc   : 监听app生命周期
 *  version: 1.0
 */
object KtxAppLifeObserver :LifecycleObserver {
    var isForeground = UnPeekLiveData<Boolean>()
    /**
     * ON_CREATE 在应用程序的整个生命周期中只会被调用一次
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private  fun onCreate() {}

    /**
     * 应用程序出现到前台时调用
     * 可用于前台判断  onForeground
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart(){
        isForeground.value = true
    }

    /**
     * 应用程序出现到前台时调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume(){}

    /**
     * 应用程序出现到后台时调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause(){}

    /**
     * 应用程序退出到后台时调用
     * 可用于后台判断  onBackground
     **/
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        isForeground.value = false
    }

    /**
     * 永远不会被调用到，系统不会分发调用ON_DESTROY事件
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {}
}