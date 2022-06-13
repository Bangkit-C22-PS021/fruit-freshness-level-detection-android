package com.example.capstoneproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    var name: String,
    var description: String,
    var photo: Int
): Parcelable