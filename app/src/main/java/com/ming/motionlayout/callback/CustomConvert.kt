package com.ming.motionlayout.callback

import com.drake.net.error.RequestParamsException
import com.drake.net.error.ResponseException
import com.drake.net.error.ServerResponseException
import com.google.gson.Gson
import com.yanzhenjie.kalle.Request
import com.yanzhenjie.kalle.Response
import com.yanzhenjie.kalle.exception.ParseError
import com.yanzhenjie.kalle.simple.Converter
import org.json.JSONObject
import java.lang.reflect.Type

abstract class CustomConvert (
    val success: String = "0",
    val code: String = "code",
    val message: String = "msg"
): Converter {
    override fun <S> convert(
        succeed: Type,
        request: Request,
        response: Response,
        cache: Boolean
    ): S? {
        // 有data字段 是个对象
        var json = "{\"data\":{\"curPage\":1,\"datas\":[],\"offset\":0,\"over\":true,\"pageCount\":0,\"size\":20,\"total\":0},\"errorCode\":0,\"errorMsg\":\"\"}"
        // 有data字段 是个{}
        var json1 = "{\"data\":{},\"errorCode\":0,\"errorMsg\":\"\"}"
        // 有data字段 是个 ""
        var json2 = "{\"data\":\"\",\"errorCode\":0,\"errorMsg\":\"\"}"
        // 有data字段 是个 []
        var json3 = "{\"data\":[],\"errorCode\":0,\"errorMsg\":\"出错了\"}"
        // 没有data字段
        var json4 = "{\"errorCode\":0,\"errorMsg\":\"\"}"
        var body = response.body().string()/*json3*/
        println("bodybody======"+Gson().toJson(body))
        response.log = body  // 日志记录响应信息
        val code = response.code()
        when {
            code in 200..299 -> { // 请求成功
                val jsonObject = JSONObject(body) // 获取JSON中后端定义的错误码和错误信息
                if (jsonObject.getString(this.code) == success) { // 对比后端自定义错误码
                    body = if (jsonObject.has("data")){
                        jsonObject.getString("data")
                    }else{
                        ""
                    }
                    return if (succeed === String::class.java) body as S else body.parseBody(succeed)
                } else { // 错误码匹配失败, 开始写入错误异常
                    throw ResponseException(code, jsonObject.getString(message), request, body)
                }
            }
            code in 400..499 -> throw RequestParamsException(code, request) // 请求参数错误
            code >= 500 -> throw ServerResponseException(code, request) // 服务器异常错误
            else -> throw ParseError(request)
        }
    }

    /**
     * 解析字符串数据
     * 一般用于解析JSON
     * @param succeed 请求函数定义的泛型, 例如一般的Moshi/Gson解析数据需要使用
     * @receiver 原始字符串
     */
    abstract fun <S> String.parseBody(succeed: Type): S?
}