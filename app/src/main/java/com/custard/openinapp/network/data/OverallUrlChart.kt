package com.custard.openinapp.network.data


import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.gson.annotations.SerializedName

data class OverallUrlChart(
    @SerializedName("00:00")
    val x0000: Int,
    @SerializedName("01:00")
    val x0100: Int,
    @SerializedName("02:00")
    val x0200: Int,
    @SerializedName("03:00")
    val x0300: Int,
    @SerializedName("04:00")
    val x0400: Int,
    @SerializedName("05:00")
    val x0500: Int,
    @SerializedName("06:00")
    val x0600: Int,
    @SerializedName("07:00")
    val x0700: Int,
    @SerializedName("08:00")
    val x0800: Int,
    @SerializedName("09:00")
    val x0900: Int,
    @SerializedName("10:00")
    val x1000: Int,
    @SerializedName("11:00")
    val x1100: Int,
    @SerializedName("12:00")
    val x1200: Int,
    @SerializedName("13:00")
    val x1300: Int,
    @SerializedName("14:00")
    val x1400: Int,
    @SerializedName("15:00")
    val x1500: Int,
    @SerializedName("16:00")
    val x1600: Int,
    @SerializedName("17:00")
    val x1700: Int,
    @SerializedName("18:00")
    val x1800: Int,
    @SerializedName("19:00")
    val x1900: Int,
    @SerializedName("20:00")
    val x2000: Int,
    @SerializedName("21:00")
    val x2100: Int,
    @SerializedName("22:00")
    val x2200: Int,
    @SerializedName("23:00")
    val x2300: Int
)

fun OverallUrlChart.toLineData(backColor: Int, fillColorBack: Drawable): LineData {
    val entry = listOf(
        Entry(1f, this.x0000.toFloat()),
        Entry(2f, this.x0100.toFloat()),
        Entry(3f, this.x0200.toFloat()),
        Entry(4f, this.x0300.toFloat()),
        Entry(5f, this.x0400.toFloat()),
        Entry(6f, this.x0500.toFloat()),
        Entry(7f, this.x0600.toFloat()),
        Entry(8f, this.x0700.toFloat()),
        Entry(9f, this.x0800.toFloat()),
        Entry(10f, this.x0900.toFloat()),
        Entry(11f, this.x0100.toFloat()),
        Entry(12f, this.x1100.toFloat()),
        Entry(13f, this.x1200.toFloat()),
        Entry(14f, this.x1300.toFloat()),
        Entry(15f, this.x1400.toFloat()),
        Entry(16f, this.x1500.toFloat()),
        Entry(17f, this.x1600.toFloat()),
        Entry(18f, this.x1700.toFloat()),
        Entry(19f, this.x1800.toFloat()),
        Entry(20f, this.x1900.toFloat()),
        Entry(21f, this.x2000.toFloat()),
        Entry(22f, this.x2100.toFloat()),
        Entry(23f, this.x2200.toFloat()),
        Entry(24f, this.x2300.toFloat()),
    )
    val set = LineDataSet(entry, "Live Data").apply {
        setDrawCircleHole(false)
        setDrawIcons(false)
        lineWidth = 1f
        valueTextSize = 9f
        setDrawFilled(true)
        setDrawValues(false)
        setDrawCircles(false)
        fillDrawable = fillColorBack
        color = backColor
    }
    return LineData(set)
}