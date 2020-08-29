package com.jetpack.mvvmdemo.viewmodel

import com.jason.mvvm.base.viewmodel.BaseViewModel
import com.jason.mvvm.ext.livedata.event.EventLiveData

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/07/06  13:37
 *  desc   : APP全局的ViewModel，可在此处发送全局通知替代EventBus，LiveDataBus等
 *  用法如下：
 *  如发送：eventViewModel.collectEvent.postValue(CollectBus(it.id, it.collect))
 *  如接收：collectEvent.observe(viewLifecycleOwner, Observer {})
 *  version: 1.0
 */
class EventViewModel :BaseViewModel() {
    //全局收藏，在任意一个地方收藏或取消收藏，监听该值的界面都会收到消息
    //val collectEvent =EventLiveData<CollectBus>()

}