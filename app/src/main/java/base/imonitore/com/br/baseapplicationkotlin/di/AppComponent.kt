package base.imonitore.com.br.baseapplicationkotlin.di

import base.imonitore.com.br.baseapplicationkotlin.login.LoginViewModel
import base.imonitore.com.br.baseapplicationkotlin.midia.MidiaViewModel
import base.imonitore.com.br.baseapplicationkotlin.splash.SplashScreenActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RemoteModule::class, DataBaseModule::class))
@Singleton interface AppComponent {
    fun inject(loginViewModel: LoginViewModel)

    fun inject(midiaViewModel: MidiaViewModel)

    fun inject(app: ApplicationBase)
    fun inject(splashScreenActivity: SplashScreenActivity)

//    fun inject(target: LoginActivity)
}
