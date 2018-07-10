package base.imonitore.com.br.baseapplicationkotlin.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import base.imonitore.com.br.baseapplicationkotlin.loading.LoadingDialog
import base.imonitore.com.br.baseapplicationkotlin.repository.local.user.UserLocalRepo
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.io.TesteResponse
import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.User
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.io.UserIn
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.ResultRepository
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.user.UserRemoteRepo
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.validateHttpCode
import base.imonitore.com.br.baseapplicationkotlin.session.Session
import base.imonitore.com.br.baseapplicationkotlin.utils.ConnectivityAgentUtils
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
        private val userRemoteRepo: UserRemoteRepo,
        private val userLocalRepo: UserLocalRepo,
        private var session: Session,
        private val loadingDialog: LoadingDialog,
        private val connectivityAgentUtils: ConnectivityAgentUtils) {

    private val TAG: String = this::class.java.simpleName

    fun getUserInsert(userIn: UserIn, isLoading:Boolean?=true): Observable<ResultRepository<User?>>? {
        if(!connectivityAgentUtils.isDeviceConnectedToInternet())
            return ResultRepository(User()).connectivityErrorObservable()

        return userRemoteRepo.acessar(userIn)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {if(isLoading!!) loadingDialog.showLoading() }
                    .doOnTerminate{if(isLoading!!) loadingDialog.dismissLoading()}
                    .validateHttpCode()
                    .map {
                        if (it.isSuccess()){
                            it.data.let {
                                insertUserSession(it)
                                insertUser(it)
                            }
                        }
                        it
                    }
    }

    fun insertUser(user: User?){
        Single.fromCallable {
            userLocalRepo.insert(user!!)
        }.subscribeOn(Schedulers.io())
                .subscribe (
                        {t: Long? -> Log.d(TAG,"insertUser->"+t.toString()) },
                        {t: Throwable? -> Log.e(TAG,"insertUser->",t) }
                )
    }

    fun insertUserSession(user: User?){
        session.user = user
    }


//    fun getUser(userIn: UserIn): LiveData<ResultRepository<User?>>{
//
//        val disposable = userRemoteRepo.acessar(userIn)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .validateHttpCode()
//                .subscribe({result ->
//                    insertUser(result.data)
//                    session.user = result.data
//                    mutableLiveData.value = result
//                })
//
//
////        val disposable = userRemoteRepo.acessar(userIn)
////                .subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
//////                .filter { it -> it.code() == 201 }
////                .flatMap({user ->
////                    Observable.zip(
////                            Observable.just(
////                            if(user.code() == 200){
////                                var user = ResultRepository.fromData(user.body())
////                            }
////                            else
////                                ResultRepository.fromError(user.body())
////                            ),
////                    )
////                })
////                .subscribe({ result ->
////                    session.user = result.data
////                    mutableLiveData.value = result
////
////                }, { t: Throwable? -> t?.printStackTrace() })
//        return mutableLiveData
//    }

    fun getTeste(): LiveData<TesteResponse>{
        val mutableLiveData = MutableLiveData<TesteResponse>()
        userRemoteRepo.teste()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { testeResponse ->
                        Log.d("code->", testeResponse.code().toString())
                        mutableLiveData.value = testeResponse.body()
                    },
                    {
                        //sem conexão ou qlqr outra exception
                        t: Throwable? -> t?.printStackTrace()
                    })
//        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }

//    fun insertUser(user: ResultRepository<User?>): Disposable? {
//       return Completable.fromAction(Action {
//            userLocalRepo.insert(user.data!!)
//        })
//        .subscribeOn(Schedulers.io())
//        .subscribe(Action {
//            Log.d("insertUser","insertUser")
//        })
//    }

//    fun insertUser(user: User?) {
//        Single.create(SingleOnSubscribe { emitter: SingleEmitter<Long> ->
//            try {
//                emitter.onSuccess(userLocalRepo.insert(user!!))
//            }
//            catch (ex:Exception){
//                emitter.onError(ex)
//            }
//        }).subscribeOn(Schedulers.io())
//                .subscribe { t: Long? ->
//                    Log.d("insertUser->", t.toString())
//                }
//    }
//
//    fun insertUser(user: ResultRepository<User?>): Observable<Long>{
//        return Observable.just(
//                userLocalRepo.insert(user.data!!)
//        )
//    }

//    fun <T> Observable<Response<T>>.addUser() : Observable<ResultRepository<T?>> {
//        return map {
//            if(it.code() == 200){
//                var user = ResultRepository.fromData(it.body())
////                insertUser(user as ResultRepository<User?>)
//                return@map user
//            }
//            else
//                ResultRepository.fromError(it.body())
//        }
//    }

//    fun addUser(resultUser : ResultRepository<User?>) : Observable<() -> Unit>? {
//        return  Observable.just({
//            userLocalRepo.insert(resultUser.data!!)
//            }
//        )
//    }
}


