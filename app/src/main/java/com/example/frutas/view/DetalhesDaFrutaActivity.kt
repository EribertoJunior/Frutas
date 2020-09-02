package com.example.frutas.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import com.example.frutas.R
import com.example.frutas.model.repository.STATUS
import com.example.frutas.view.extencions.carregarUrl
import com.example.frutas.view.extencions.hide
import com.example.frutas.view.extencions.show
import com.example.frutas.view_model.FrutaDetalhadaViewModel
import kotlinx.android.synthetic.main.content_detalhes_da_fruta.ivFruta
import kotlinx.android.synthetic.main.content_detalhes_da_fruta.tvDescricaoCompleta
import kotlinx.android.synthetic.main.content_detalhes_da_fruta.tvOutrosNomes
import kotlinx.android.synthetic.main.content_detalhes_da_fruta.*
import kotlinx.android.synthetic.main.content_detalhes_da_fruta.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetalhesDaFrutaActivity : AppCompatActivity(), LifecycleObserver {

    private val viewModel: FrutaDetalhadaViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_da_fruta)
        setSupportActionBar(findViewById(R.id.toolbar))
        lifecycle.addObserver(this)

        initObserver()

        if (intent.hasExtra("nome")) {
            viewModel.getDetalhes(intent.extras?.get("nome") as String)
        }
    }

    private fun initObserver() {
        viewModel.frutaDetalhadaData.observe(this, Observer {
            when (it.status) {
                STATUS.OPEN_LOADING ->{
                    progressBar2.show()
                }
                STATUS.SUCCESS ->{
                    progressBar2.hide()
                    it.frutaDetalhada?.let {frutaDetalhada ->
                        ivFruta.carregarUrl(frutaDetalhada.imageurl)
                        tvDescricaoCompleta.text = frutaDetalhada.description
                        tvOutrosNomes.text = frutaDetalhada.othname
                        tvNomesBots.text = frutaDetalhada.botname
                        tvNomeDaFruta.text = frutaDetalhada.tfvname
                    }
                }
                STATUS.ERROR ->{
                    progressBar2.hide()
                }
            }
        })
    }

}