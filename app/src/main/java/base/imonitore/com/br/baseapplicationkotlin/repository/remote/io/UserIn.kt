package base.imonitore.com.br.baseapplicationkotlin.repository.remote.io

data class UserIn (
    var codigoEmpresa: String? = null,
    var login: String? = null,
    var senha: String? = null,
    var versao: String? = null,
    var imei: String? = null,
    var macAddress: String? = null
)