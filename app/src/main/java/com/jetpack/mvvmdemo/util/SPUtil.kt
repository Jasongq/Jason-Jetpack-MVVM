package com.jetpack.mvvmdemo.util

import android.os.Parcelable
import com.tencent.mmkv.MMKV
import java.util.*

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/07/06  13:49
 *  desc   :
 *  version: 1.0
 */
object SPUtil {
    /**默认是defaultMMKV ，可通过withID设置指定的mmkv*/
    private var mmkv=MMKV.defaultMMKV()

    /**
     * 可单独设置id
     * SPUtil.withID(MMKV.mmkvWithID(app)).decodeString()
     */
    fun withID(kv: MMKV) :SPUtil{
        mmkv=kv
        return SPUtil
    }

    fun encode(key: String, value: Any?) {
        when (value) {
            is String -> mmkv.encode(key, value)
            is Float -> mmkv.encode(key, value)
            is Boolean -> mmkv.encode(key, value)
            is Int -> mmkv.encode(key, value)
            is Long -> mmkv.encode(key, value)
            is Double -> mmkv.encode(key, value)
            is ByteArray -> mmkv.encode(key, value)
            is Nothing -> return
        }
    }

    fun <T : Parcelable> encode(key: String, t: T?) {
        if(t ==null){
            return
        }
        mmkv.encode(key, t)
    }

    fun encode(key: String, sets: Set<String>?) {
        if(sets ==null){
            return
        }
        mmkv.encode(key, sets)
    }

    fun decodeInt(key: String): Int {
        return mmkv.decodeInt(key, 0)
    }

    fun decodeDouble(key: String): Double {
        return mmkv.decodeDouble(key, 0.00)
    }

    fun decodeLong(key: String): Long {
        return mmkv.decodeLong(key, 0L)
    }

    fun decodeBoolean(key: String): Boolean {
        return decodeBoolean(key, false)
    }
    fun decodeBoolean(key: String,defaultValue:Boolean): Boolean {
        return mmkv.decodeBool(key, defaultValue)
    }


    fun decodeFloat(key: String): Float {
        return mmkv.decodeFloat(key, 0F)
    }

    fun decodeByteArray(key: String): ByteArray {
        return mmkv.decodeBytes(key)
    }

    fun decodeString(key: String): String {
        return mmkv.decodeString(key, "")
    }

    fun <T : Parcelable> decodeParcelable(key: String, tClass: Class<T>): T {
        return mmkv.decodeParcelable(key, tClass)
    }

    fun decodeStringSet(key: String): Set<String> {
        return mmkv.decodeStringSet(key, Collections.emptySet())
    }

    fun removeKey(key: String) {
        mmkv.removeValueForKey(key)
    }

    fun clearAll() {
        mmkv.clearAll()
    }
}