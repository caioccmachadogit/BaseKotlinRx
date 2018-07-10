package base.imonitore.com.br.baseapplicationkotlin.repository.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import base.imonitore.com.br.baseapplicationkotlin.repository.local.midia.MidiaDao
import base.imonitore.com.br.baseapplicationkotlin.repository.local.user.UserDao
import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.Midia
import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.User

@Database(entities = arrayOf(User::class, Midia::class), version = 1)
@TypeConverters(Converters::class)
abstract class DataBaseSource : RoomDatabase() {

    abstract fun userDao():UserDao

    abstract fun midiaDao():MidiaDao

    companion object {

        fun dataBaseBuild(context: Context): DataBaseSource = Room.databaseBuilder(
                context.applicationContext,
                DataBaseSource::class.java,
                "databaseLocal"
        ).build()
    }
}