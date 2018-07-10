package base.imonitore.com.br.baseapplicationkotlin.repository.remote.midia

import javax.inject.Inject

class MidiaRemoteRepo @Inject constructor(private val midiaRest: MidiaRest){

    fun midias() = midiaRest.midias()
}