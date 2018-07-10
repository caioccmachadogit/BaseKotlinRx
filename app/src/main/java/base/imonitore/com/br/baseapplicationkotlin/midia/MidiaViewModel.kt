package base.imonitore.com.br.baseapplicationkotlin.midia

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import base.imonitore.com.br.baseapplicationkotlin.di.ApplicationBase
import base.imonitore.com.br.baseapplicationkotlin.repository.MidiaRepository
import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.Midia
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.ResultRepository
import javax.inject.Inject

class MidiaViewModel : ViewModel(){

    @Inject lateinit var midiaRepository: MidiaRepository

    private var liveDataMidia: LiveData<ResultRepository<List<Midia>?>>? = null
    private var lvDeleteMidia: LiveData<Int>? = null

    init {
        initializeDagger()
    }

    fun getMidiasInsertAll(): LiveData<ResultRepository<List<Midia>?>>?{
        liveDataMidia = null
        liveDataMidia = MutableLiveData<ResultRepository<List<Midia>?>>()

        midiaRepository.getMidiasInsertAll()
                .subscribe({ result ->
                    (liveDataMidia as MutableLiveData<ResultRepository<List<Midia>?>>).value = result},
                { t: Throwable? -> t?.printStackTrace() })

        return liveDataMidia
    }

    fun readMidias(): LiveData<List<Midia>> {
        return midiaRepository.readMidias()
    }

    fun deleteMidia(midia: Midia): LiveData<Int>? {
        lvDeleteMidia = null
        lvDeleteMidia = MutableLiveData<Int>()
        midiaRepository.deleteMidia(midia)?.subscribe({ t ->
            (lvDeleteMidia as MutableLiveData<Int>).value = t},
            {t: Throwable? -> t?.printStackTrace() })

        return lvDeleteMidia
    }

    private fun initializeDagger() = ApplicationBase.appComponent.inject(this)
}