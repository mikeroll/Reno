package me.mikeroll.reno.app

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewManager
import org.jetbrains.anko.__dslAddView

fun ViewManager.recyclerView(init: RecyclerView.() -> Unit = {}) =
        __dslAddView({ RecyclerView(it) }, init, this)

fun Context.recyclerView(init: RecyclerView.() -> Unit = {}) =
        __dslAddView({ RecyclerView(it) }, init, this)
