package base.imonitore.com.br.baseapplicationkotlin.utils

import base.imonitore.com.br.baseapplicationkotlin.repository.remote.GsonDateAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.*

object JSONUtils {

    private var builder: GsonBuilder? = null

    init {
        builder = GsonBuilder()
        builder!!.registerTypeAdapter(Date::class.java, GsonDateAdapter())
//        builder!!.registerTypeAdapter(PerfilUserEnum::class.java, GsonEnumAdapter())
    }

    fun newGson(): Gson {
        return builder!!.create()
    }
}
