package com.idriskhozema.nytimes.mvvm

import com.idriskhozema.nytimes.utils.NyTimesApp
import com.idriskhozema.nytimes.R
import com.idriskhozema.nytimes.data.NyTimesData
import com.idriskhozema.nytimes.network.ApiServiceHelperImplementation
import com.idriskhozema.nytimes.utils.DataState
import com.idriskhozema.nytimes.utils.NetworkHelper
import java.lang.Exception
import java.net.HttpURLConnection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Idris Khozema on 28/07/2022.
 */
@Singleton
class NyTimesArticlesRepository @Inject constructor(
    private val context: NyTimesApp,
    private val networkHelper: NetworkHelper,
    private val apiServiceHelper: ApiServiceHelperImplementation
){

    suspend fun fetchArticles() : Flow<DataState<NyTimesData>> = flow{

            val response = apiServiceHelper.fetchNyTimesArticles()
            when(response.code()) {
                HttpURLConnection.HTTP_OK -> { emit(DataState.Success(response.body())) }
                HttpURLConnection.HTTP_INTERNAL_ERROR -> emit(DataState.Error(context.getString(R.string.generic_error)))
            }
    }
}