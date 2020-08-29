package com.jetpack.mvvmdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.jason.mvvm.base.viewmodel.BaseViewModel
import com.jason.mvvm.ext.field.StringObservableField
import com.jetpack.mvvmdemo.data.model.UserInfo
import com.jetpack.mvvmdemo.data.repository.LoginRepository
import com.jetpack.mvvmdemo.http.state.ResultState
import rxhttp.wrapper.param.RxHttp

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/07/03  16:53
 *  desc   : 登录注册的ViewModel
 *  version: 1.0
 */
class LoginViewModel : BaseViewModel() {
    var username = StringObservableField()
    var password = StringObservableField()
    //
    private val loginRepository: LoginRepository by lazy { LoginRepository() }
    var loginResult = MutableLiveData<ResultState<UserInfo>>()
    //var loginResult1 = MutableLiveData<ResultState<BaseResponse<UserInfo>>>()


    fun login(username: String, password: String){
        //发起请求
        request({ loginRepository.loginReq(username,password) },loginResult,true)
    }

    /**
     * 保存数据
     */
    fun saveUserinfo(userInfo: UserInfo) {
        loginRepository.insertUser(userInfo)
    }

}