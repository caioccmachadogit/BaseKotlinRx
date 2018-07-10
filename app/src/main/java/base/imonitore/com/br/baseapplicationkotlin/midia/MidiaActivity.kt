package base.imonitore.com.br.baseapplicationkotlin.midia

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import base.imonitore.com.br.baseapplicationkotlin.R
import base.imonitore.com.br.baseapplicationkotlin.base.BaseActivity
import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.Midia
import kotlinx.android.synthetic.main.main_include.*

class MidiaActivity : BaseActivity() {

    private lateinit var midiaViewModel: MidiaViewModel

    private val adapter by lazy { MidiasAdapter(listenerAdapter()) }

    private fun listenerAdapter(): View.OnClickListener {
        return  View.OnClickListener {
            var midiaSelected = it.tag as Midia
            midiaViewModel.deleteMidia(midiaSelected)?.observe(this, Observer {
                if(it == 1)
                    toast("delete->"+midiaSelected.urlMidia)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi(R.layout.main_include)
        initViewModel()
        initUi()
    }

    private fun initUi() {
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rvMidias.layoutManager = layoutManager
        rvMidias.adapter = adapter

        btnGetMidias.setOnClickListener {
            midiaViewModel.getMidiasInsertAll()?.observe(this, Observer {
                if (it!!.isSuccess()) {
                    toast(it.data!!.size.toString()+" mídias encontradas")
                }
            })
        }

        btnRefresh.setOnClickListener {
            midiaViewModel.readMidias().observe(this, Observer {
                toast(it!!.size.toString()+" Midias")
                adapter.setItems(it)
            })
        }

    }

    private fun initViewModel() {
        midiaViewModel = ViewModelProviders.of(this).get(MidiaViewModel::class.java)
    }
}
