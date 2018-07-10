package base.imonitore.com.br.baseapplicationkotlin.repository.local.user

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.User

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun insert(user: User):Long
}