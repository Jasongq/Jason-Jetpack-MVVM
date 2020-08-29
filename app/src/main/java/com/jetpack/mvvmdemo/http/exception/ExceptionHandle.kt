package com.jetpack.mvvmdemo.http.exception

import android.net.ParseException
import android.text.TextUtils
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import rxhttp.wrapper.exception.HttpStatusCodeException
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/07/07  10:58
 *  desc   : 根据异常返回相关的错误信息工具类
 *  version: 1.0
 */
object ExceptionHandle  {
    fun handleException(e: Throwable?): AppException {
        val ex: AppException
        e?.let {
            when (it) {
                is HttpStatusCodeException ->{
                    val httpCodeException = it as HttpStatusCodeException
                    ex = AppException(httpCodeException.statusCode.toInt(),
                        if (TextUtils.isEmpty(httpCodeException.result)){
                            httpCodeException.statusCode
                        }else{
                            httpCodeException.result
                        }
                        ,httpCodeException.message)
                    return ex
                }
                is HttpException -> {
                    ex = AppException(Error.NETWORK_ERROR,e)
                    return ex
                }
                is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
                    ex = AppException(Error.PARSE_ERROR,e)
                    return ex
                }
                is ConnectException -> {
                    ex = AppException(Error.NETWORK_ERROR,e)
                    return ex
                }
                is javax.net.ssl.SSLException -> {
                    ex = AppException(Error.SSL_ERROR,e)
                    return ex
                }
                is SocketTimeoutException ,is ConnectTimeoutException -> {
                    ex = AppException(Error.TIMEOUT_ERROR,e)
                    return ex
                }
                is java.net.UnknownHostException -> {
                    ex = AppException(Error.NONET_ERROR,e)
                    return ex
                }
                is AppException -> return it
                else -> {
                    ex = AppException(Error.UNKNOWN,e)
                    return ex
                }
            }
        }
        ex = AppException(Error.UNKNOWN,e)
        return ex
    }
}