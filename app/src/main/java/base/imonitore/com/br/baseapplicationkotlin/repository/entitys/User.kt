package base.imonitore.com.br.baseapplicationkotlin.repository.entitys

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.RepositoryResponse
import java.util.*

@Entity(tableName = "USER")
data class User(
    var token: String? = null,
    var expiracao: Date? = null,
    var urlBrasaoEstado: String? = null,
    var urlLogoDetran: String? = null,
//    private var roles: List<PerfilUserEnum>? = null
    var codigoEmpresa: String? = null
//    private var notificacoes: List<Notificacoes>? = null
) : RepositoryResponse(){
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}