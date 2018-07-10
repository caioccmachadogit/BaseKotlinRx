package base.imonitore.com.br.baseapplicationkotlin.repository.local.user


import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.User
import javax.inject.Inject

class UserLocalRepo @Inject constructor(
        private val userDao: UserDao){

    fun insert(user: User) = userDao.insert(user)
}