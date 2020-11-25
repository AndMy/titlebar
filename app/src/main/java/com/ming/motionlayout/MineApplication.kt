package com.ming.motionlayout

import android.app.Application
import com.drake.net.NetConfig.logEnabled
import com.drake.net.initNet
import com.ming.motionlayout.callback.GsonConvert

class MineApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // http://182.92.97.186/  这是接口全局域名, 可以使用NetConfig.host读写
        initNet("https://movie.douban.com/") {

            // 大括号内部都是可选项配置
            //sslSocketFactory()
            converter(GsonConvert()) // 转换器
            //cacheEnabled() // 开启缓存
            setLogRecord(BuildConfig.DEBUG) // 日志记录器
            logEnabled = BuildConfig.DEBUG // LogCat异常日志
        }
    }
}