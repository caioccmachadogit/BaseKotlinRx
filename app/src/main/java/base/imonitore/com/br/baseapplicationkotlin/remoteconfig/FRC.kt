package base.imonitore.com.br.baseapplicationkotlin.remoteconfig

import android.util.Log
import base.imonitore.com.br.baseapplicationkotlin.BuildConfig
import base.imonitore.com.br.baseapplicationkotlin.R
import base.imonitore.com.br.baseapplicationkotlin.di.ApplicationBase
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.RemoteConstant
import com.google.android.gms.tasks.Task
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import java.util.*

class FRC {

    private var cacheExpiration: Long = 0 //valor em segundos - default producao

    private var map: HashMap<String,Any> = hashMapOf()

    val CONFIG_BASE_URL_DESENV = "base_url_desenv"
    val CONFIG_BASE_URL_TESTE = "base_url_teste"
    val CONFIG_BASE_URL_PROD = "base_url_prod"

    private val rc by lazy {
        FirebaseRemoteConfig.getInstance().apply {
            setConfigSettings(FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(BuildConfig.DEBUG).build())
            setDefaults(R.xml.remote_config_defaults)
            if(info.configSettings.isDeveloperModeEnabled) cacheExpiration = 0
        }
    }

    fun fetch(onComplete: ((Task<Void>) -> Unit)? = null) {
        val fetchTask = if (rc.info.configSettings.isDeveloperModeEnabled) rc.fetch(0) else rc.fetch(cacheExpiration) // The default value expiration duration is 12 hours
        if (onComplete != null) fetchTask.addOnCompleteListener {
            setRemoteConfig()
            Log.d("RemoteConfig ", "updateData->" + it.isSuccessful)
            onComplete.invoke(it)
        }
    }

    fun setRemoteConfig() {
        map.put(CONFIG_BASE_URL_DESENV, rc.getString(CONFIG_BASE_URL_DESENV))
        map.put(CONFIG_BASE_URL_TESTE, rc.getString(CONFIG_BASE_URL_TESTE))
        map.put(CONFIG_BASE_URL_PROD, rc.getString(CONFIG_BASE_URL_PROD))

        getBaseUrlFlavor()
    }

    fun getBaseUrlFlavor(): String {
        lateinit var ambiente :String
        lateinit var baseUrl :String

        when (BuildConfig.FLAVOR) {
            "appDesenv" -> ambiente = CONFIG_BASE_URL_DESENV

            "appTeste" -> ambiente = CONFIG_BASE_URL_TESTE

            "appProd" -> ambiente = CONFIG_BASE_URL_PROD
        }

        if(ApplicationBase.isMockAmbiente)
            baseUrl = RemoteConstant.BASE_API_MOCK
        else
            baseUrl = map.get(ambiente) as String

        Log.d("BaseUrlFlavor->", baseUrl)
        return baseUrl
    }
}