package com.example.stackexchangetask.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun HAS_NETWORK(context: Context): Boolean? {
    var isConnected: Boolean? = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

const val CACHE_SIZE = (5 * 1024 * 1024).toLong()