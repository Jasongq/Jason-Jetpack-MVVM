package com.jetpack.mvvmdemo.http.exception

import java.lang.Exception

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/07/07  10:54
 *  desc   : 自定义错误信息异常
 *  version: 1.0
 */
class AppException : Exception {
    var errorMsg: String //错误消息
    var errCode: Int = 0 //错误码
    var errorLog: String? //错误日志

    constructor(errCode: Int, error: String?, errorLog: String? = "") : super(error) {
        this.errorMsg = error ?: "${errCode}-请求失败,请稍后再试"
        this.errCode = errCode
        this.errorLog = errorLog?:this.errorMsg
    }

    constructor(error: Error,e: Throwable?) {
        errCode = error.getCode()
        errorMsg = error.getMsg()
        errorLog = e?.message
    }
}