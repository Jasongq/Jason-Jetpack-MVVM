package com.jetpack.mvvmdemo.util

import android.text.TextUtils
import com.google.gson.Gson
import com.jetpack.mvvmdemo.data.model.UserInfo

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/07/06  11:55
 *  desc   :
 *  version: 1.0
 */
object CacheUtil {
    private const val appFirstOpen ="isFirstInto"
    private const val user ="userInfo"
    private const val isLoginApp ="isLoginApp"

    /**
     * 是否是第一次登陆
     * @return true:第一次进入
     */
    fun isFirst(): Boolean {
        return SPUtil.decodeBoolean(appFirstOpen, true)
    }
    /**
     * 设置是否第一次打开app
     */
    fun setFirst(first:Boolean) {
        SPUtil.encode(appFirstOpen, first)
    }

    /**
     * 获取保存的账户信息
     */
    fun getUser() :UserInfo?{
        val userStr=SPUtil.decodeString(user)
        return if (TextUtils.isEmpty(userStr)) null else Gson().fromJson(userStr,UserInfo::class.java)
    }

    /**
     * 设置用户信息
     */
    fun setUser(userInfo: UserInfo?){
        if (userInfo!=null) {
            SPUtil.encode(user,Gson().toJson(userInfo))
            setIsLogin(true)
        }else{
            SPUtil.encode(user,"")
            setIsLogin(false)
        }
    }
    /**
     * 是否已经登录
     */
    fun isLogin(): Boolean {
        return SPUtil.decodeBoolean(isLoginApp)
    }

    /**
     * 设置是否已经登录
     */
    fun setIsLogin(isLogin: Boolean) {
        SPUtil.encode(isLoginApp,isLogin)
    }


}