package com.example.isalmiapp.api

import com.google.gson.annotations.SerializedName

data class Radio(
  @field:SerializedName("name")
    val name: String?,

  @field:SerializedName("url")
    val url: String?
)