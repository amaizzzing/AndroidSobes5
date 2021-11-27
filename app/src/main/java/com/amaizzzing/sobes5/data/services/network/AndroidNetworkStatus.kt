package com.amaizzzing.sobes5.data.services.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AndroidNetworkStatus(context: Context): INetworkStatus {
    private val _networkStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val networkStatus = _networkStateFlow.asStateFlow()

    init {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().build()

        connectivityManager.registerNetworkCallback(request, object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                val isInternetAvailable = getConnectionType(connectivityManager) == InternetAvailableStates.AVAILABLE

                _networkStateFlow.update {
                    isInternetAvailable
                }
            }

            override fun onUnavailable() {
                _networkStateFlow.update { false }
            }

            override fun onLost(network: Network) {
                _networkStateFlow.update { false }
            }
        })
    }

    private fun getConnectionType(cm: ConnectivityManager): InternetAvailableStates {
        var result = InternetAvailableStates.NONE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        result = InternetAvailableStates.AVAILABLE
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        result = InternetAvailableStates.AVAILABLE
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                        result = InternetAvailableStates.AVAILABLE
                    }
                }
            }
        } else {
            val activeNetwork = cm.activeNetworkInfo
            if (activeNetwork != null) {
                // connected to the internet
                when {
                    activeNetwork.type === ConnectivityManager.TYPE_WIFI -> {
                        result = InternetAvailableStates.AVAILABLE
                    }
                    activeNetwork.type === ConnectivityManager.TYPE_MOBILE -> {
                        result = InternetAvailableStates.AVAILABLE
                    }
                    activeNetwork.type === ConnectivityManager.TYPE_VPN -> {
                        result = InternetAvailableStates.AVAILABLE
                    }
                }
            }
        }
        return result
    }

    override fun isOnline(): Boolean = networkStatus.value
}

enum class InternetAvailableStates(val value: Int) {
    NONE(0),
    AVAILABLE(1)
}