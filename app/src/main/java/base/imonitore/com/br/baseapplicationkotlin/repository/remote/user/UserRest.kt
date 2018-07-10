package base.imonitore.com.br.baseapplicationkotlin.repository.remote.user

import base.imonitore.com.br.baseapplicationkotlin.repository.remote.io.TesteResponse
import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.User
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.io.UserIn
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.RemoteConstant
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserRest {
    @POST(RemoteConstant.SERVICE_ACESSAR)
    fun acessar(@Body userIn: UserIn):Observable<Response<User>>

    @GET("live?access_key=be4554e86f3a5670b287ccc40f5bead8&currencies=AED,BHD&format=1")
    fun teste():Observable<Response<TesteResponse>>
}