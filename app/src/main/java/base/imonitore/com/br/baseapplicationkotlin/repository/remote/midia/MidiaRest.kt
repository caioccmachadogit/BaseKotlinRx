package base.imonitore.com.br.baseapplicationkotlin.repository.remote.midia

import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.Midia
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.RemoteConstant
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface MidiaRest {

    @GET(RemoteConstant.SERVICE_MIDIA)
    fun midias(): Observable<Response<List<Midia>>>
}