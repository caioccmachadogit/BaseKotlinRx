package base.imonitore.com.br.baseapplicationkotlin.di

import android.content.Context
import base.imonitore.com.br.baseapplicationkotlin.repository.local.DataBaseSource
import base.imonitore.com.br.baseapplicationkotlin.session.Session
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides @Singleton fun provideDataBaseSource(context: Context) =
            DataBaseSource.dataBaseBuild(context)

    @Provides @Singleton fun provideUserDao(database: DataBaseSource) = database.userDao()

    @Provides @Singleton fun provideMidiaDao(database: DataBaseSource) = database.midiaDao()

    @Provides @Singleton fun provideSession(): Session = Session()

}