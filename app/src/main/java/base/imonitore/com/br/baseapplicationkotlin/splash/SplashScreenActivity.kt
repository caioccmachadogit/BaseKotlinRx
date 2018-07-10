package base.imonitore.com.br.baseapplicationkotlin.splash

import android.os.Bundle
import base.imonitore.com.br.baseapplicationkotlin.R
import base.imonitore.com.br.baseapplicationkotlin.base.BaseActivity
import base.imonitore.com.br.baseapplicationkotlin.di.ApplicationBase
import base.imonitore.com.br.baseapplicationkotlin.login.LoginActivity
import base.imonitore.com.br.baseapplicationkotlin.remoteconfig.FRC
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashScreenActivity : BaseActivity() {

    @Inject
    lateinit var frc: FRC

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        ApplicationBase.appComponent.inject(this)

        frc.fetch {
            startActivity<LoginActivity>()
        }
    }
}