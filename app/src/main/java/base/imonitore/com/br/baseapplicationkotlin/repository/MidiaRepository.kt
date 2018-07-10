package base.imonitore.com.br.baseapplicationkotlin.repository

import android.arch.lifecycle.LiveData
import android.util.Log
import base.imonitore.com.br.baseapplicationkotlin.loading.LoadingDialog
import base.imonitore.com.br.baseapplicationkotlin.repository.local.midia.MidiaLocalRepo
import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.Midia
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.ResultRepository
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.midia.MidiaRemoteRepo
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.validateHttpCode
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MidiaRepository @Inject constructor(
        private val midiaRemoteRepo: MidiaRemoteRepo,
        private val midiaLocalRepo: MidiaLocalRepo,
        private val loadingDialog: LoadingDialog) {

    private val TAG: String = this::class.java.simpleName

    fun getMidias(isLoading:Boolean?=true): Observable<ResultRepository<List<Midia>?>>{
        return midiaRemoteRepo.midias()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {if(isLoading!!) loadingDialog.showLoading() }
                .doOnTerminate{if(isLoading!!) loadingDialog.dismissLoading()}
                .validateHttpCode()
    }

    fun getMidiasInsertAll(isLoading:Boolean?=true): Observable<ResultRepository<List<Midia>?>>{
        return midiaRemoteRepo.midias()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {if(isLoading!!) loadingDialog.showLoading() }
                .doOnTerminate{if(isLoading!!) loadingDialog.dismissLoading()}
                .validateHttpCode()
                .map {
                    if (it.isSuccess())
                        insertAllMidias(it.data)
                    it
                }
    }

    fun insertAllMidias(midias: List<Midia>?) {
        Single.fromCallable {
            midiaLocalRepo.insertAll(midias!!)
        }.subscribeOn(Schedulers.io())
                .subscribe (
                        {t: List<Long>? ->  Log.d(TAG, "insertAllMidias->"+t?.size.toString())},
                        {t: Throwable? -> Log.e(TAG,"insertAllMidias->",t) }
                )
    }

    fun readMidias(): LiveData<List<Midia>> {
        var midias = midiaLocalRepo.read()
//        TODO log read
        Log.d(TAG, "readMidias->"+midias.value?.size)
        return midias
    }

    fun deleteMidia(midia: Midia): Single<Int>? {
        return Single.fromCallable{
            midiaLocalRepo.delete(midia)
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map { t ->
            Log.d(TAG,"deleteMidia->"+t)
            t
        }
    }
}


