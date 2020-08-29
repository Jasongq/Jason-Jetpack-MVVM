package com.jetpack.mvvmdemo.util

import android.app.ActivityManager
import android.content.Context
import android.os.Process
import android.text.TextUtils
import android.util.Log
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.regex.Pattern

/**
 *  @author : GuoQiang
 *  e-mail : 849199845@qq.com
 *  time   : 2020/06/29  15:28
 *  desc   : appUtil
 *  version: 1.0
 */
object AppUtil {
    /**
     * 获取进程号对应的进程名
     * @param pid 进程号
     * @return 进程名
     */
    fun getProcessName(pid: Int): String? {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            var processName = reader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim { it <= ' ' }
            }
            return processName
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        } finally {
            try {
                reader?.close()
            } catch (exception: IOException) {
                exception.printStackTrace()
            }

        }
        return null
    }

    /**
     * 获取当前应用程序的包名
     * @param context 上下文对象
     * @return 返回包名
     */
    fun getProcessName(context: Context): String? {
        //当前应用pid
        val myPid = Process.myPid()
        //任务管理类
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        //遍历所有应用
        val appProcesses = activityManager.runningAppProcesses
        if (appProcesses == null || appProcesses.size == 0) {
            return null
        }
        for (appProcess in appProcesses) {
            if (appProcess.processName == context.packageName) {
                if (appProcess.pid == myPid) {
                    return appProcess.processName
                }
            }
        }
        return ""
    }

    fun isVMMultidexCapable(): Boolean {
        return isVMMultidexCapable(System.getProperty("java.vm.version"))
    }

    /**
     * MultiDex 拷出来的的方法，判断VM是否支持多dex
     */
    fun isVMMultidexCapable(versionString: String?): Boolean {
        var isMultidexCapable = false
        if (versionString != null) {
            val matcher =
                Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(versionString)
            if (matcher.matches()) {
                try {
                    val major = matcher.group(1).toInt()
                    val minor = matcher.group(2).toInt()
                    isMultidexCapable = major > 2 || major == 2 && minor >= 1
                } catch (var5: NumberFormatException) {
                }
            }
        }
        Log.i("MultiDex",
            "VM with version " + versionString + if (isMultidexCapable) " has multidex support" else " does not have multidex support")
        return isMultidexCapable
    }
}