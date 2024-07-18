package com.custard.openinapp.network.data


import com.google.gson.annotations.SerializedName

data class LinkData(
    @SerializedName("app")
    val app: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("domain_id")
    val domainId: String,
    @SerializedName("is_favourite")
    val isFavourite: Boolean,
    @SerializedName("original_image")
    val originalImage: String,
    @SerializedName("smart_link")
    val smartLink: String,
    @SerializedName("thumbnail")
    val thumbnail: Any?,
    @SerializedName("times_ago")
    val timesAgo: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("total_clicks")
    val totalClicks: Int,
    @SerializedName("url_id")
    val urlId: Int,
    @SerializedName("url_prefix")
    val urlPrefix: String?,
    @SerializedName("url_suffix")
    val urlSuffix: String,
    @SerializedName("web_link")
    val webLink: String
)