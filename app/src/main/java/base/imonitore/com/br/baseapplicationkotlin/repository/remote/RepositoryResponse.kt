package base.imonitore.com.br.baseapplicationkotlin.repository.remote

import android.arch.persistence.room.Ignore

open class RepositoryResponse() {
    @Ignore
    var success : Boolean = false



}