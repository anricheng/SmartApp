package com.capgemini.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class UserInformation  constructor(
    val owner: Owners,
    val language: String // Python
) : Parcelable

@Parcelize
data class Owners(
    val login: String, // anricheng
    val avatar_url: String, // https://avatars.githubusercontent.com/u/15911700?v=4
):Parcelable