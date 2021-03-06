package com.example.frutas.di.module

import com.example.frutas.model.repository._detalhamento.FrutaDetalhadaRepository
import com.example.frutas.model.repository._detalhamento.FrutaDetalhadaRepositoryImp
import com.example.frutas.model.repository._frutas.FrutaRepository
import com.example.frutas.model.repository._frutas.FrutaRepositoryImp
import com.example.frutas.model.retrofit.RetrofitConfig
import com.example.frutas.view_model.FrutaDetalhadaViewModel
import com.example.frutas.view_model.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val PROPERTY_BASE_URL = "PROPERTY_BASE_URL"
const val PROPERTY_DETAILS_URL = "PROPERTY_DETAILS_URL"

val appModule = module {

    factory{
        val baseUrl = getProperty(PROPERTY_BASE_URL)
        val detailsUrl = getProperty(PROPERTY_DETAILS_URL)
        RetrofitConfig(baseUrl, detailsUrl)
    }

    single {
        FrutaRepositoryImp(retroConfig = get())
    }

    single {
        FrutaDetalhadaRepositoryImp(retroConfig = get())
    }

    viewModel {
        MainViewModel(
            frutaRepository = get<FrutaRepositoryImp>() as FrutaRepository
        )
    }

    viewModel {
        FrutaDetalhadaViewModel(
            get<FrutaDetalhadaRepositoryImp>() as FrutaDetalhadaRepository
        )
    }

}