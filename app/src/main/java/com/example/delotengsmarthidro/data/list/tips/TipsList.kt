package com.example.delotengsmarthidro.data.list.tips

import androidx.annotation.DrawableRes

data class TipsList(
    val title: String,
    val desc: String,
    @DrawableRes val icon: Int,
)