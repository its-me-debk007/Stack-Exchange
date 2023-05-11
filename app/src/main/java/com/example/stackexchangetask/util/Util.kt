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

const val AD_LINK = "https://gumlet.assettype.com/afaqs%2F2022-07%2Fe42911e0-9586-47a6-bbf3-c3f55f0bebde%2Fnescafe_asmr.png?w=1200&auto=format%2Ccompress&ogImage=true"

fun ChipGroup.addChip(context: Context, text: String) {

    Chip(context).apply {

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