package com.jetpack.mvvmdemo.http.exception
/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/07/07
 *  desc   : 错误枚举类
 *  version: 1.0
 */
enum class Error(private val code: Int, private val errroMsg: String) {

    /**未知错误*/
    UNKNOWN(1000, "请求失败,请稍后再试"),

    /** 解析错误*/
    PARSE_ERROR(1001, "解析错误,请稍后再试"),

    /** 网络错误*/
    NETWORK_ERROR(1002, "网络连接错误,请稍后重试"),

    /** 证书出错*/
    SSL_ERROR(1004, "证书出错,请检查证书是否过期"),

    /** 连接超时*/
    TIMEOUT_ERROR(1005, "网络连接超时,请稍后重试"),

    /** 无网络*/
    NONET_ERROR(1006, "无网络连接,请稍后重试");

    fun getMsg(): String {
        return errroMsg
    }

    fun getCode(): Int {
        return code
    }
}