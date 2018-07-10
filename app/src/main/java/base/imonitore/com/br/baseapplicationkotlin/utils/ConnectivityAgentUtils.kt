package base.imonitore.com.br.baseapplicationkotlin.utils

import android.content.Context
import android.net.ConnectivityManager


class ConnectivityAgentUtils(private val context: Context) {

    fun isDeviceConnectedToInternet(): Boolean {
        val service = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = service.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}