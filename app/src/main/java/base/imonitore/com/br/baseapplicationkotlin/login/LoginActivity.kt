package base.imonitore.com.br.baseapplicationkotlin.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import base.imonitore.com.br.baseapplicationkotlin.R
import base.imonitore.com.br.baseapplicationkotlin.base.BaseActivity
import base.imonitore.com.br.baseapplicationkotlin.midia.MidiaActivity
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.io.UserIn
import kotlinx.android.synthetic.main.login_activity.*
import org.jetbrains.anko.startActivity

class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var userIn : UserIn;

//    @Inject
//    lateinit var session:Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
//        ApplicationBase.appComponent.inject(this)
        initUi()
        initViewModel()
    }

    private fun initViewModel() {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    private fun initUi() {
        btEntrar.setOnClickListener{validarCampos()}
    }

    private fun validarCampos(){
        clearTextInput()
        userIn = UserIn(etCodigo.text.toString(), etUsuario.text.toString(), etSenha.text.toString())

        if(userIn.codigoEmpresa.isNullOrEmpty()){
            tiCodigo.error = resources.getString(R.string.campo_obrigatorio)
            return
        }
        if(userIn.login.isNullOrEmpty()){
            tiUsuario.error = resources.getString(R.string.campo_obrigatorio)
            return
        }
        if(userIn.senha.isNullOrEmpty()){
            tiSenha.error = resources.getString(R.string.campo_obrigatorio)
            return
        }

        loginViewModel.login(userIn)?.observe(this, Observer {
            if (it!!.isSuccess()) {
                toast(resources.getString(R.string.login_sucesso))
                startActivity<MidiaActivity>()
                finish()
            }
            else
                it.errorRepo?.mensagem?.let { it1 -> toast(it1) }
        })

//        loginViewModel.getTeste()?.observe(this, Observer {
//            if (it != null) {
//                toast(it!!.terms)
//            }
//        })


    }

    fun clearTextInput() {
        tiCodigo.isErrorEnabled = false
        tiUsuario.isErrorEnabled = false
        tiSenha.isErrorEnabled = false
    }
}