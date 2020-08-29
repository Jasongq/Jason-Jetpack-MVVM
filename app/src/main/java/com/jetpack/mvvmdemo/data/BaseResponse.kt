package com.jetpack.mvvmdemo.data

import com.jetpack.mvvmdemo.data.model.toJsonString

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/06/30  16:02
 *  desc   : 服务器返回数据的基类
 *  version: 1.0
 */
open class BaseResponse<T> (private var errorCode :Int=1,private var errorMsg :String="",private var data :T){

    override fun toString(): String {
        return this.toJsonString()
    }

    open fun getCode():Int{
        return errorCode
    }
    open fun getMsg():String{
        return errorMsg
    }
    open fun getData():T{
        return data
    }

}