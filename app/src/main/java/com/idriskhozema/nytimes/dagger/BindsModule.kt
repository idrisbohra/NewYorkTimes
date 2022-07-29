package com.idriskhozema.nytimes.dagger

import com.idriskhozema.nytimes.mvvm.NyTimesArticlesRepo
import com.idriskhozema.nytimes.mvvm.NyTimesArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface BindsModule {

    @Singleton
    @Binds
    fun bindNyTimesArticlesRepo(nyTimesArticlesRepository: NyTimesArticlesRepository) : NyTimesArticlesRepo
}