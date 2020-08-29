package com.jason.mvvm.ext.livedata
import androidx.lifecycle.MutableLiveData

/**
 *  @author : GuoQiang
 *  time   : 2020/06/23
 *  desc   : 自定义的String类型 MutableLiveData 提供了默认值，避免取值的时候还要判空
 *  version: 1.0
 */
class StringLiveData : MutableLiveData<String>() {

    override fun getValue(): String {
        return super.getValue() ?: ""
    }

}