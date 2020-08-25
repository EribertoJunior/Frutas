package com.example.frutas.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.frutas.R
import com.example.frutas.model.entidades.Fruta
import kotlinx.android.synthetic.main.item_list_frut.view.*

class AdapterFrutas(
    var mValues: ArrayList<Fruta> = arrayListOf(),
    private val interacaoComLista: InteracaoComLista<Fruta>
) : RecyclerView.Adapter<AdapterFrutas.ViewHolder>() {

    private val mOnclickListener: View.OnClickListener = View.OnClickListener { view ->
        val repositorio = view.tag as Fruta

        interacaoComLista.selecionou(repositorio)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_frut, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruta = mValues[position]

        holder.tvNomeFruta.text = fruta.tfvname

        with(holder.mView) {
            tag = fruta
            setOnClickListener(mOnclickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var tvNomeFruta: TextView = mView.tvNomeFruta
    }
}