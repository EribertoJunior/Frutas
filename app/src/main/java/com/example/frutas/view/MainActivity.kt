package com.example.frutas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import com.example.frutas.R
import com.example.frutas.model.repository.STATUS
import com.example.frutas.view_model.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), LifecycleObserver {

    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObserver()

        lifecycle.addObserver(this)
    }

    private fun initObserver() {
        viewModel.frutaData.observe(this, Observer {
            when(it.status){
                STATUS.OPEN_LOADING -> {
                    //progressBarFormularioProduto.show()
                }
                STATUS.CLOSE_LOADING -> {
                    //progressBarFormularioProduto.hide()
                }
                STATUS.SUCCESS -> {
                    //progressBarFormularioProduto.hide()
                    showMessageError(message = it.itens.size.toString())
                }
                STATUS.ERROR -> {
                    //progressBarFormularioProduto.hide()
                    showMessageError(message = it.errorMessage)

                }
            }
        })
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