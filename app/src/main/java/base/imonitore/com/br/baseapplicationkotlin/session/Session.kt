package base.imonitore.com.br.baseapplicationkotlin.session

import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.User

data class Session (
        var user: User? = null,
        var dataLogin:String? = null
) {


//    init {
//        initializeDagger()
//    }
//
//    private fun initializeDagger() = ApplicationBase.appComponent.inject(this)
}