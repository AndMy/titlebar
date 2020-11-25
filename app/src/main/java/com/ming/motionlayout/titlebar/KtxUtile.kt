package com.ming.motionlayout.titlebar

import android.content.Context
import android.graphics.drawable.Drawable

fun Int.getDimen(context: Context): kotlin.Int {
    return context.resources.getDimensionPixelSize(this)
}
fun Int.getDrawable(context: Context) : Drawable{
    return context.resources.getDrawable(this)
}