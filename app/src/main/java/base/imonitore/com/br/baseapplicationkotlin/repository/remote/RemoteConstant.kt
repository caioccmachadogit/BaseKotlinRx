package base.imonitore.com.br.baseapplicationkotlin.repository.remote

class RemoteConstant {

    companion object {
        const val VERSION = "v1/"
        const val ROTE_SEGURANCA = "seguranca/";
        const val ROTE_MIDIA = "midia/";
        const val ROTE_LOGIN = "login/";
        const val ROTE_USER = "user/";
        const val SERVICE_ENCERRAR = ROTE_SEGURANCA+VERSION+ROTE_LOGIN+"encerrar";
        const val SERVICE_ACESSAR = ROTE_SEGURANCA+VERSION+ROTE_LOGIN+"acessar";
        const val SERVICE_MIDIA = ROTE_MIDIA+VERSION+ROTE_USER+"midias";
        const val BASE_API_MOCK = "http://mock.api/"
    }
}