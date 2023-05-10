package com.example.stackexchangetask.util

import android.content.Context
import android.content.res.ColorStateList
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.ContextCompat
import com.example.stackexchangetask.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

const val CACHE_SIZE = (5 * 1024 * 1024).toLong()

fun ChipGroup.addChip(context: Context, text: String) {

    this.setChipSpacing(6)

    Chip(context).apply {

//        setTextAppearance(TextAppearance(context, R.style.))

        this.text = text

        chipBackgroundColor = ColorStateList.valueOf(
            ContextCompat.getColor(
                context,
                R.color.tag_background_color
            )
        )

        setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    context,
                    R.color.tag_text_color
                )
            )
        )

        chipStrokeWidth = 0f

        this@addChip.addView(this)
    }
}