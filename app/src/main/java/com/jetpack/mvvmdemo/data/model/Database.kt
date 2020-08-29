package com.jetpack.mvvmdemo.data.model

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/06/30  16:19
 *  desc   : 服务器返回的model
 *  version: 1.0
 */

/**
 *转换String
 */
fun Any?.toJsonString():String{
    return Gson().toJson(this)
}

/**
 * 用户信息
 */
@Parcelize
data class UserInfo(var admin:Boolean=false,
                    var nickname:String="",
                    var username:String="",
                    var token:String="") : Parcelable{
    override fun toString(): String {
        return this.toJsonString()
    }
}