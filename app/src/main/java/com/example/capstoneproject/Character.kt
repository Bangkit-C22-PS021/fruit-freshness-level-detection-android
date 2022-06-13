package com.example.capstoneproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    var name: String,
    var description: String,
    var photo: Int,
): Parcelable