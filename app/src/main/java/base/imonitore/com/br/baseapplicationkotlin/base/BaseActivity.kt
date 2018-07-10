package base.imonitore.com.br.baseapplicationkotlin.base

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import base.imonitore.com.br.baseapplicationkotlin.R
import base.imonitore.com.br.baseapplicationkotlin.di.ApplicationBase
import br.com.imonitore.extensions.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * Created by ccouto on 25/09/2017.
 */
open class BaseActivity : AppCompatActivity() {

    protected val context: Context get() = this

    init {
        ApplicationBase.activityContext = this
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        view?.hideKeyboard(imm)
    }

    fun toast(message: CharSequence, length: Int = Toast.LENGTH_SHORT) =
            Toast.makeText(this, message, length).show()

    protected fun setActivityContext(activity: AppCompatActivity){
        ApplicationBase.activityContext = this
    }

    protected fun setupUi(layoutInclude: Int, isToolbar: Boolean?=true){
        setContentView(R.layout.activity_main)
        layoutInflater.inflate(layoutInclude, content_view)
        if (isToolbar!!)
            setUpToolbar()
    }
    // Configura a Toolbar
    protected fun setUpToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            tvNomeApp.text = "Nome App"
            tvDescricaoApp.text = "descrição app"
        }
    }
}