package com.farlan.readinglist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book (
    val title: String,
    val author: String,
    val description: String,
    val cover: Int,
    val publisher: String,
    val releaseYear: String,
) : Parcelable