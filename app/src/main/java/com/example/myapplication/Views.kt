package com.example.myapplication

import android.view.View

/**
 * to VISIBLE , INVISIBLE and GONE Views
 */
fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

