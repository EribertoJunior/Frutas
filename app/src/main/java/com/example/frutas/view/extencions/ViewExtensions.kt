package com.example.frutas.view.extencions

import android.view.View

fun View.show(){
    visibility = View.VISIBLE
}

fun View.hide(){
    visibility = View.GONE
}

fun View.enable(){
    isEnabled = true
}

fun View.desable(){
    isEnabled = false
}