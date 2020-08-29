package com.jetpack.mvvmdemo.data.repository

import androidx.lifecycle.MutableLiveData
import com.jetpack.mvvmdemo.http.Urls
import com.jetpack.mvvmdemo.data.BaseResponse
import com.jetpack.mvvmdemo.http.state.ResultState
import com.jetpack.mvvmdemo.data.model.UserInfo
import com.jetpack.mvvmdemo.util.CacheUtil
import rxhttp.await
import rxhttp.wrapper.param.RxHttp


/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/07/01  15:03
 *  desc   :
 *  version: 1.0
 */
class LoginRepository {

    var loginResult = MutableLiveData<ResultState<UserInfo>>()
    //var loginResult1 = MutableLiveData<ResultState<BaseResponse<UserInfo>>>()


    suspend fun loginReq(username: String, password: String) :BaseResponse<UserInfo>{
        return RxHttp.postForm(Urls.reqLogin)
                .add("username", username)
                .add("password", password)
                //.toResponse<UserInfo>()
                .await<BaseResponse<UserInfo>>()
    }

    /**
     * 保存或者在数据库中插入数据
     */
    fun insertUser(userInfo: UserInfo) {
        CacheUtil.setUser(userInfo)
    }


}