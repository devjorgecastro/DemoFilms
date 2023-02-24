package com.devstro.demofilms.core.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DateRange(
    val maximum: String,
    val minimum: String
)
