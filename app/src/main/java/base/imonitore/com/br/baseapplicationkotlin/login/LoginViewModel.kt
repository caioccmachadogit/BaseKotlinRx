package base.imonitore.com.br.baseapplicationkotlin.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import base.imonitore.com.br.baseapplicationkotlin.di.ApplicationBase
import base.imonitore.com.br.baseapplicationkotlin.repository.UserRepository
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.io.UserIn
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.io.TesteResponse
import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.User
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.ResultRepository
import javax.inject.Inject

class LoginViewModel : ViewModel(){

    @Inject lateinit var userRepository: UserRepository

    private var liveDataUser: LiveData<ResultRepository<User?>>? = null

    private var liveDataTeste: LiveData<TesteResponse>? = null

    init {
        initializeDagger()
    }

    fun login(userIn: UserIn): LiveData<ResultRepository<User?>>?{
        liveDataUser = null
        liveDataUser = MutableLiveData<ResultRepository<User?>>()

        userRepository.getUserInsert(userIn)?.subscribe { result ->
            (liveDataUser as MutableLiveData<ResultRepository<User?>>).value = result
        }

        return liveDataUser
    }

    fun getTeste(): LiveData<TesteResponse>?{
        liveDataTeste = null
        liveDataTeste = MutableLiveData<TesteResponse>()
        liveDataTeste = userRepository.getTeste()
        return liveDataTeste
    }

    private fun initializeDagger() = ApplicationBase.appComponent.inject(this)
}