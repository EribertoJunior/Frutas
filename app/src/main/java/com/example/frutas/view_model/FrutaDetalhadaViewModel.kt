package com.example.frutas.view_model

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frutas.model.entidades.FrutaDetalhada
import com.example.frutas.model.repository.STATUS
import com.example.frutas.model.repository._detalhamento.FrutaDetalhadaRepository
import com.example.frutas.model.repository.modelresponseCustom.FrutaDetalhadaResponseCustom
import com.example.frutas.model.retrofit.response.CallbackResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FrutaDetalhadaViewModel(
    private val frutaDetalhadaRepository: FrutaDetalhadaRepository
) : ViewModel(), LifecycleObserver {

    val frutaDetalhadaData : MutableLiveData<FrutaDetalhadaResponseCustom> = MutableLiveData(FrutaDetalhadaResponseCustom(status = STATUS.OPEN_LOADING))

    fun getDetalhes(nomeDaFruta:String){
        showLoading()
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                frutaDetalhadaRepository.getDetailFruts(nomeDaFruta, object : CallbackResponse<ArrayList<FrutaDetalhada>>{
                    override fun success(response: ArrayList<FrutaDetalhada>) {
                        frutaDetalhadaData.postValue(frutaDetalhadaData.value?.apply {
                            status = STATUS.SUCCESS
                            frutaDetalhada = response[0]
                        })
                    }

                    override fun failure(mensagem: String) {
                        showErrorMessage(mensagem)
                    }
                })
            }
        }

    }

    private fun showLoading() {
        frutaDetalhadaData.postValue(frutaDetalhadaData.value?.apply {
            status = STATUS.OPEN_LOADING
        })
    }

    private fun showErrorMessage(message: String) {
        frutaDetalhadaData.postValue(frutaDetalhadaData.value?.apply {
            status = STATUS.ERROR
            errorMessage = message
        })
    }
}