package com.comit.simtong.handler

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

/**
 * 네트워크 끊김을 방지하기 위한 [NetworkConnection]
 */
class NetworkConnection(private val context: Context) :
    ConnectivityManager.NetworkCallback() {

    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val networkRequest: NetworkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .build()

    /**
     * 네트워크가 연결이 되어있지 않을 때 보여줄 Dialog
     * TODO(limsaehyun) 디자인을 이쁘게 개선해야 함
     */
    private val dialog: AlertDialog by lazy {
        AlertDialog.Builder(context)
            .setTitle("네트워크 연결 안 됨")
            .setMessage("와이파이 또는 모바일 데이터를 확인해주세요")
            .create()
    }

    /**
     * Network Callback 등록
     */
    fun register() {
        connectivityManager.registerNetworkCallback(networkRequest, this)
    }

    /**
     * Network Callback 해제
     */
    fun unregister() {
        connectivityManager.unregisterNetworkCallback(this)
    }

    /**
     * 현재 네트워크 상태를 확인하는 Method
     *
     * @return 네트워크거 연결되어있지 않을 경우 null
     */
    private fun getConnectivityStatus(): Network? {
        return connectivityManager.activeNetwork
    }

    /**
     * Callback이 등록되거나 네트워크가 연결되었을 떄 실행되는 메소드
     */
    override fun onAvailable(network: Network) {
        super.onAvailable(network)

        if (getConnectivityStatus() == null) {
            dialog.show()
        } else {
            dialog.dismiss()
        }
    }

    /**
     * 네트워크가 끊겼을 때 실행되는 메소드
     */
    override fun onLost(network: Network) {
        super.onLost(network)
        dialog.show()
    }
}
