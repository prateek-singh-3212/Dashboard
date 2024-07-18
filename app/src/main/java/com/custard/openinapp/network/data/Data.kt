package com.custard.openinapp.network.data


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("favourite_links")
    val favouriteLinks: List<Any>,
    @SerializedName("overall_url_chart")
    val overallUrlChart: OverallUrlChart,
    @SerializedName("recent_links")
    val recentLinks: List<LinkData>,
    @SerializedName("top_links")
    val topLinks: List<LinkData>
)