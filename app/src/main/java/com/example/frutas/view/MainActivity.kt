package com.example.frutas.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frutas.R
import com.example.frutas.model.entidades.Fruta
import com.example.frutas.model.repository.STATUS
import com.example.frutas.view.adapters.AdapterFrutas
import com.example.frutas.view.adapters.InteracaoComLista
import com.example.frutas.view_model.MainViewModel
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_main.btLimparPesquisa
import kotlinx.android.synthetic.main.activity_main.etPesquisa
import kotlinx.android.synthetic.main.content_main.rvListaDeFrutas
import kotlinx.android.synthetic.main.content_main.swipeRefresh_frutas
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), LifecycleObserver {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var adapter: AdapterFrutas
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = resources.getString(R.string.lista_de_frutas)
        setSupportActionBar(toolbar)

        swipeRefresh_frutas.apply {
            setOnRefreshListener {
                viewModel.buscarFrutas()
            }
            setColorSchemeColors(
                ContextCompat.getColor(this@MainActivity, R.color.colorPrimaryDark),
                ContextCompat.getColor(this@MainActivity, R.color.colorPrimary),
                ContextCompat.getColor(this@MainActivity, R.color.colorAccent)
            )
        }
        btLimparPesquisa.setOnClickListener {
            etPesquisa.text?.clear()
        }
        etPesquisa.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.buscarFrutas(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        initAdapter()
        initRecyclerView()
        initObserver()

        lifecycle.addObserver(this)
    }

    private fun initRecyclerView() {
        rvListaDeFrutas.layoutManager = LinearLayoutManager(this)
        rvListaDeFrutas.adapter = adapter
    }

    private fun initAdapter() {
        adapter =
            AdapterFrutas(
                interacaoComLista = object : InteracaoComLista<Fruta> {
                    override fun selecionou(itemSelecionado: Fruta) {
                        startActivity(
                            Intent(this@MainActivity, DetalhesDaFrutaActivity::class.java).apply {
                                putExtra("nome", itemSelecionado.tfvname)
                            })
                    }
                })
    }

    private fun initObserver() {
        viewModel.frutaData.observe(this, Observer {
            when (it.status) {
                STATUS.OPEN_LOADING -> {
                    swipeRefresh_frutas.isRefreshing = true
                }
                STATUS.SUCCESS -> {
                    swipeRefresh_frutas.isRefreshing = false
                    adicionarNovosItens(it.itens)
                }
                STATUS.ERROR -> {
                    swipeRefresh_frutas.isRefreshing = false
                    adicionarNovosItens(arrayListOf())
                    showMessageError(message = it.errorMessage)
                }
            }
        })
    }

    private fun adicionarNovosItens(items: ArrayList<Fruta>) {
        adapter.mValues = items
        adapter.notifyDataSetChanged()
    }

    private fun showMessageError(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }
}