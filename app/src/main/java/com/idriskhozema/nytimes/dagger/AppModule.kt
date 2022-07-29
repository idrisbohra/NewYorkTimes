package com.idriskhozema.nytimes.dagger

import android.content.Context
import com.idriskhozema.nytimes.mvvm.NyTimesArticlesRepo
import com.idriskhozema.nytimes.mvvm.NyTimesArticlesRepository
import com.idriskhozema.nytimes.utils.NyTimesApp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Idris Khozema on 28/07/2022.
 */

@Module(includes = [BindsModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) = context as NyTimesApp

}