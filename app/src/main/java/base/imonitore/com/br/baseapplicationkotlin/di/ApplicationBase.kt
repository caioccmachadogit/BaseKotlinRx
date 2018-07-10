package base.imonitore.com.br.baseapplicationkotlin.di

import android.app.Application
import android.support.v7.app.AppCompatActivity

class ApplicationBase : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var activityContext : AppCompatActivity
        val isMockAmbiente: Boolean = true
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .dataBaseModule(DataBaseModule())
                .remoteModule(RemoteModule())
                .build()

    }

}