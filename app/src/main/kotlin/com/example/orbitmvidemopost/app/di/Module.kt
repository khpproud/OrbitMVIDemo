package com.example.orbitmvidemopost.app.di

import androidx.lifecycle.SavedStateHandle
import com.example.orbitmvidemopost.app.features.postdetails.viewmodel.PostDetailViewModel
import com.example.orbitmvidemopost.app.features.postlist.viewmodel.PostListViewModel
import com.example.orbitmvidemopost.data.PostRepository
import com.example.orbitmvidemopost.data.network.AvatarUrlGenerator
import com.example.orbitmvidemopost.data.network.PostNetworkDataSource
import com.example.orbitmvidemopost.data.network.TypicodeService
import com.example.orbitmvidemopost.domain.model.PostOverview
import com.example.orbitmvidemopost.domain.repository.IPostRepository
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

fun module() = module {
    viewModel { PostListViewModel(get(), get()) }

    viewModel { (savedStateHandle: SavedStateHandle, postOverview: PostOverview) -> PostDetailViewModel(savedStateHandle, get(), postOverview) }

    single {
        ObjectMapper().registerKotlinModule().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create(get()))
            .baseUrl("https://jsonplaceholder.typicode.com").build()
    }

    single { get<Retrofit>().create(TypicodeService::class.java) }

    single { PostNetworkDataSource(get()) }

    single { AvatarUrlGenerator() }

    single<IPostRepository> { PostRepository(get(), get()) }
}