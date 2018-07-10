package base.imonitore.com.br.baseapplicationkotlin.repository.local.midia

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import base.imonitore.com.br.baseapplicationkotlin.repository.local.LocalConstant
import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.Midia

@Dao
interface MidiaDao {

    @Insert(onConflict = REPLACE)
    fun insertAll(midias:List<Midia>) : List<Long>

    @Query(LocalConstant.READ_MIDIA)
    fun read() : LiveData<List<Midia>>

//    @Query(LocalConstant.READ_MIDIA)
//    fun readFlowable() : Flowable<List<Midia>>

    @Delete
    fun delete(midia: Midia) : Int
}