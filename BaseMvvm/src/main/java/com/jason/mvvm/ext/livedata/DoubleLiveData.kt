package com.jason.mvvm.ext.livedata

import androidx.lifecycle.MutableLiveData

/**
 *  @author : GuoQiang
 *  time   : 2020/06/23
 *  desc   : 自定义的Double类型 MutableLiveData 提供了默认值，避免取值的时候还要判空
 *  version: 1.0
 */
class DoubleLiveData : MutableLiveData<Double>() {
    override fun getValue(): Double {
        return super.getValue() ?: 0.0
    }
}