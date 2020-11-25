package com.ming.motionlayout.bean

data class ArticleBean(
    val curPage: Int,
    val datas: List<Any>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)