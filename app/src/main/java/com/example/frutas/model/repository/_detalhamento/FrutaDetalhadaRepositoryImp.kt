package com.example.frutas.model.repository._detalhamento

import com.example.frutas.model.entidades.Fruta
import com.example.frutas.model.entidades.FrutaDetalhada
import com.example.frutas.model.retrofit.ResponseAPI
import com.example.frutas.model.retrofit.RetrofitConfig
import com.example.frutas.model.retrofit.response.CallbackResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FrutaDetalhadaRepositoryImp(private val retroConfig: RetrofitConfig): FrutaDetalhadaRepository {

    override fun getDetailFruts(nomeDaFruta:String, callbackResponse: CallbackResponse<ArrayList<FrutaDetalhada>>) {
        val call = retroConfig.detalheDaFrutaService().carregarFrutas(nomeDaFruta)
        call.enqueue(object : Callback<ResponseAPI<ArrayList<FrutaDetalhada>>>{
            override fun onResponse(
                call: Call<ResponseAPI<ArrayList<FrutaDetalhada>>>,
                response: Response<ResponseAPI<ArrayList<FrutaDetalhada>>>
            ) {
                if(response.isSuccessful)
                    response.body()?.let {
                        callbackResponse.success(it.results)
                    }
                else
                    callbackResponse.failure(response.message())
            }

            override fun onFailure(
                call: Call<ResponseAPI<ArrayList<FrutaDetalhada>>>,
                t: Throwable
            ) {
                t.message?.let { callbackResponse.failure(it) }
            }
        })
    }
}