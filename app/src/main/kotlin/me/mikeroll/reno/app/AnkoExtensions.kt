package me.mikeroll.reno.app

import android.content.Context
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.__dslAddView

fun Context.recyclerView(init: RecyclerView.() -> Unit = {}) =
        __dslAddView({ RecyclerView(it) }, init, this)
