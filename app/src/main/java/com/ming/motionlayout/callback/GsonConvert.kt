package com.ming.motionlayout.callback
import com.drake.net.convert.DefaultConvert
import com.google.gson.GsonBuilder
import java.lang.reflect.Type

class GsonConvert : CustomConvert(code = "errorCode", message = "errorMsg", success = "0") {
    val gson = GsonBuilder().serializeNulls().create()

    override fun <S> String.parseBody(succeed: Type): S? {
        println("GsonConvert==自定义转换器收到的字符串=="+this)
        println("GsonConvert==自定义转换器接受的类型=="+succeed)

        return gson.fromJson(this, succeed)
    }
}