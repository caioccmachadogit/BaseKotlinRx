package base.imonitore.com.br.baseapplicationkotlin.repository.entitys

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.RepositoryResponse


@Entity(tableName = "MIDIA")
data class Midia(
        var urlMidia:String? = null,
        var downloaded:Boolean? = null,
        var idFk:Long? = null
) : RepositoryResponse() {
    @PrimaryKey(autoGenerate = true) var idMidia: Long = 0
}