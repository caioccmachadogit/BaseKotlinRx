
package base.imonitore.com.br.baseapplicationkotlin.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val applicationBase : ApplicationBase) {

  @Provides @Singleton fun provideContext(): Context = applicationBase

//  @Provides @Singleton fun context(): Context {
//    return applicationBase
//  }

}
