package com.jetpack.mvvmdemo.viewmodel

import com.jason.mvvm.base.viewmodel.BaseViewModel
import com.jason.mvvm.ext.livedata.UnPeekLiveData
import com.jetpack.mvvmdemo.data.model.UserInfo
import com.jetpack.mvvmdemo.util.CacheUtil

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/07/06  11:51
 *  desc   : APP全局的ViewModel，可以存放公共数据，当他数据改变时，所有监听他的地方都会收到回调,也可以做发送消息
 * 比如 全局可使用的 地理位置信息，账户信息,App的基本配置等等，
 *  version: 1.0
 */
class SharedViewModel :BaseViewModel(){

    //UserInfo
    var userinfo = UnPeekLiveData<UserInfo>()

    init {
        //默认值保存的账户信息，没有登陆过则为null
        userinfo.value= CacheUtil.getUser()

    }


}