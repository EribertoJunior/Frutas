package com.example.frutas.view_model

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frutas.model.entidades.Fruta
import com.example.frutas.model.repository.STATUS
import com.example.frutas.model.repository._frutas.FrutaRepository
import com.example.frutas.model.repository.modelresponseCustom.FrutaResponseCustom
import com.example.frutas.model.retrofit.response.CallbackResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val frutaRepository: FrutaRepository
)  : ViewModel(), LifecycleObserver {

    val frutaData : MutableLiveData<FrutaResponseCustom> = MutableLiveData(FrutaResponseCustom(status = STATUS.OPEN_LOADING))

    init {
        getListFrut()
    }

    fun buscarFrutas(search:String? = null){
        if(search.isNullOrEmpty()){
            getListFrut()
        } else{
            searchFrutByBarcode(search)
        }
    }

    private fun searchFrutByBarcode(search:String){
        showLoading()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                frutaRepository.serachFrut(search, object : CallbackResponse<ArrayList<Fruta>>{
                    override fun success(response: ArrayList<Fruta>) {
                        frutaData.postValue(frutaData.value?.apply {
                            status = STATUS.SUCCESS
                            itens = response
                        })
                    }

                    override fun failure(mensagem: String) {
                        showErrorMessage(mensagem)
                    }
                })
            }
        }
    }

    private fun getListFrut(){
        showLoading()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                frutaRepository.getListFruts(object : CallbackResponse<ArrayList<Fruta>>{
                    override fun success(response: ArrayList<Fruta>) {
                        frutaData.postValue(frutaData.value?.apply {
                            status = STATUS.SUCCESS
                            itens = response
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
        frutaData.postValue(frutaData.value?.apply {
            status = STATUS.OPEN_LOADING
        })
    }

    private fun showErrorMessage(message: String) {
        frutaData.postValue(frutaData.value?.apply {
            status = STATUS.ERROR
            errorMessage = message
        })
    }
}