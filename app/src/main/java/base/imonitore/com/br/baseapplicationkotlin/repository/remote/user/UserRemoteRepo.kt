package base.imonitore.com.br.baseapplicationkotlin.repository.remote.user

import base.imonitore.com.br.baseapplicationkotlin.repository.remote.io.UserIn
import javax.inject.Inject

class UserRemoteRepo @Inject constructor( private val userRest: UserRest){

    fun acessar(userIn: UserIn) = userRest.acessar(userIn)

    fun teste() = userRest.teste()
}