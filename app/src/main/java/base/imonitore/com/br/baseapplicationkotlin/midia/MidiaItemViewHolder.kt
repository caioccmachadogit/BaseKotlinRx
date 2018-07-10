package base.imonitore.com.br.baseapplicationkotlin.midia

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.Midia
import kotlinx.android.synthetic.main.adapter_midia_item.view.*

class MidiaItemViewHolder (
        parent: ViewGroup,
        @LayoutRes itemViewLayoutId: Int
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(itemViewLayoutId, parent, false)) {

    fun render(midia: Midia){
        itemView.setTag(midia)
        renderMidiaUrl(midia.urlMidia!!)
    }

    fun setViewClickListener(viewClickListener: View.OnClickListener) = itemView.setOnClickListener(viewClickListener)

    private fun renderMidiaUrl(url: String) {
        itemView.tvMidia.text = url
    }
}