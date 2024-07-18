package com.custard.openinapp.network.data


import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName

data class DashboardData(
    @SerializedName("applied_campaign")
    val appliedCampaign: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("extra_income")
    val extraIncome: Double,
    @SerializedName("links_created_today")
    val linksCreatedToday: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("startTime")
    val startTime: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("support_whatsapp_number")
    val supportWhatsappNumber: String,
    @SerializedName("today_clicks")
    val todayClicks: Int,
    @SerializedName("top_location")
    val topLocation: String,
    @SerializedName("top_source")
    val topSource: String,
    @SerializedName("total_clicks")
    val totalClicks: Int,
    @SerializedName("total_links")
    val totalLinks: Int
)

fun DashboardData.toTopData(a1: Drawable, a2: Drawable, a3: Drawable): List<TopData> {
    return listOf(
        TopData(a1, this.todayClicks.toString(), "Today's Click"),
        TopData(a2, this.topLocation, "Top Location"),
        TopData(a3, this.topSource, "Top Source"),
        TopData(a1, this.extraIncome.toString(), "Extra Income"),
    )
}