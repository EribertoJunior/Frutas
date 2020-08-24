package com.example.frutas.view.extencions

import android.widget.ImageView
import com.example.frutas.R
import com.squareup.picasso.Picasso

fun ImageView.carregarUrl(url: String) {
    Picasso.get()
        .load(url)
        .resize(50, 50)
        .centerCrop()
        .placeholder(R.mipmap.image_placeholder_350x350)
        .into(this)
}