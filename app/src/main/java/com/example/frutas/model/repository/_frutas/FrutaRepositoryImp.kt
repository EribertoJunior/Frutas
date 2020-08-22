package com.example.frutas.model.repository._frutas

import com.example.frutas.model.entidades.Fruta
import com.example.frutas.model.retrofit.ResponseAPI
import com.example.frutas.model.retrofit.RetrofitConfig
import com.example.frutas.model.retrofit.response.CallbackResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FrutaRepositoryImp(private val retroConfig: RetrofitConfig): FrutaRepository {

    override fun getListFruts(callbackResponse: CallbackResponse<ArrayList<Fruta>>) {
        val call = retroConfig.frutasService().carregarFrutas()
        call.enqueue(object : Callback<ResponseAPI<ArrayList<Fruta>>>{
            override fun onResponse(
                call: Call<ResponseAPI<ArrayList<Fruta>>>,
                response: Response<ResponseAPI<ArrayList<Fruta>>>
            ) {
                if(response.isSuccessful)
                    response.body()?.let {
                        callbackResponse.success(it.results)
                    }
                else
                    callbackResponse.failure(response.message())
            }

            override fun onFailure(call: Call<ResponseAPI<ArrayList<Fruta>>>, t: Throwable) {
                t.message?.let { callbackResponse.failure(it) }
            }
        })
    }

    override fun serachFrut(search: String, callbackResponse: CallbackResponse<ArrayList<Fruta>>) {
        val call = retroConfig.frutasService().carregarFrutas(search)
        call.enqueue(object : Callback<ResponseAPI<ArrayList<Fruta>>>{
            override fun onResponse(
                call: Call<ResponseAPI<ArrayList<Fruta>>>,
                response: Response<ResponseAPI<ArrayList<Fruta>>>
            ) {
                if(response.isSuccessful)
                    response.body()?.let {
                        callbackResponse.success(it.results)
                    }
                else
                    callbackResponse.failure(response.message())
            }

            override fun onFailure(call: Call<ResponseAPI<ArrayList<Fruta>>>, t: Throwable) {
                t.message?.let { callbackResponse.failure(it) }
            }
        })
    }
}