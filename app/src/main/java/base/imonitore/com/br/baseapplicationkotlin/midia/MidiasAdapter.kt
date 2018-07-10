package base.imonitore.com.br.baseapplicationkotlin.midia

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import base.imonitore.com.br.baseapplicationkotlin.R
import base.imonitore.com.br.baseapplicationkotlin.repository.entitys.Midia

class MidiasAdapter(private val viewClickListener: View.OnClickListener) : RecyclerView.Adapter<MidiaItemViewHolder>() {

    private val midias: MutableList<Midia> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MidiaItemViewHolder =
            MidiaItemViewHolder(parent, R.layout.adapter_midia_item)

    override fun onBindViewHolder(holder: MidiaItemViewHolder, position: Int) {
        holder.setViewClickListener(viewClickListener)
        holder.render(midias[position])
    }

    override fun getItemCount() = midias.size

    fun setItems(midias:List<Midia>){
        this.midias.clear()
        this.midias.addAll(midias)
        notifyDataSetChanged()
    }
}