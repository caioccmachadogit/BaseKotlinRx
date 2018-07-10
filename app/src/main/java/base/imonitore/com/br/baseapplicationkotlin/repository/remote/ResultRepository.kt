package base.imonitore.com.br.baseapplicationkotlin.repository.remote

import android.util.Log
import io.reactivex.Observable
import retrofit2.Response

open class ResultRepository<T>( val data: T? = null, val errorRepo: ErrorRepo? = null) {
    companion object {
        fun <T> fromData( data: T ) : ResultRepository<T> {
            return ResultRepository(data, null)
        }

        fun <T> fromError( errorRepo: ErrorRepo?) : ResultRepository<T> {
            return ResultRepository(null, errorRepo)
        }
    }

    fun isError() : Boolean{
        return errorRepo != null
    }

    fun isSuccess() : Boolean {
        return data != null
    }

    fun connectivityErrorObservable() : Observable<ResultRepository<T?>>? {
        return Observable.just(fromError(ErrorRepo(ErrorConstant.CONNECTION_NOT)))
    }

}

fun <T> Observable<Response<T>>.validateHttpCode() : Observable<ResultRepository<T?>> {
    return map {
        Log.d("validateHttpCode->", it.code().toString())
        if(it.code() == 200){
            ResultRepository.fromData(it.body())
        }
        else
        ResultRepository.fromError(ErrorRepo(ErrorConstant.SERVICE_NOT))
    }
}



