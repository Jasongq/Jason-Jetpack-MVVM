package com.jason.mvvm.ext.field

import androidx.databinding.ObservableField
/**
 *  @author : GuoQiang
 *  time   : 2020/06/23
 *  desc   : 自定义的Boolean类型ObservableField  提供了默认值，避免取值的时候还要判空
 *  version: 1.0
 */
class BooleanObservableField(value: Boolean = false) : ObservableField<Boolean>(value) {
    override fun get(): Boolean {
        return super.get()!!
    }

}