package com.idriskhozema.nytimes.network

import com.idriskhozema.nytimes.data.NyTimesData
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Idris Khozema on 28/07/2022.
 */
@Singleton
class ApiServiceHelperImplementation @Inject constructor(
    @Named("APIServiceJSON") private val apiServiceJSON: RetrofitServiceInstance
) : ApiServiceHelper {


    override suspend fun fetchNyTimesArticles(): Response<NyTimesData> = apiServiceJSON.loadNyTimesArticles()

}