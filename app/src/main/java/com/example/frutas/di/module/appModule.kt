package com.example.frutas.di.module

import com.example.frutas.BuildConfig
import com.example.frutas.model.repository._frutas.FrutaRepository
import com.example.frutas.model.repository._frutas.FrutaRepositoryImp
import com.example.frutas.model.retrofit.RetrofitConfig
import com.example.frutas.view_model.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val PROPERTIES_BASE_URL = "BASE_URL"
const val PROPERTIES_DETAILS_URL = "DETAILS_URL"
val appModule = module {

    factory{
        RetrofitConfig()
    }

    single {
        FrutaRepositoryImp(retroConfig = get())
    }

    viewModel {
        MainViewModel(
            frutaRepository = get<FrutaRepositoryImp>() as FrutaRepository
        )
    }


}