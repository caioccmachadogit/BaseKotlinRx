package base.imonitore.com.br.baseapplicationkotlin.repository.local.midia


import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.Midia
import javax.inject.Inject

class MidiaLocalRepo @Inject constructor(
        private val midiaDao: MidiaDao){

    fun insertAll(midias: List<Midia>) = midiaDao.insertAll(midias)

    fun read() = midiaDao.read()

    fun delete(midia: Midia) = midiaDao.delete(midia)
}