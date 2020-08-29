package com.jason.mvvm.ext.field

import androidx.databinding.ObservableField
/**
 *  @author : GuoQiang
 *  time   : 2020/06/23
 *  desc   : 自定义的Float类型ObservableField  提供了默认值，避免取值的时候还要判空
 *  version: 1.0
 */
class FloatObservableField(value: Float = 0f) : ObservableField<Float>(value) {

    override fun get(): Float {
        return super.get()!!
    }

}