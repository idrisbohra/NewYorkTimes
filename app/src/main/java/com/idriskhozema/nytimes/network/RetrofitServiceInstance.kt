package com.idriskhozema.nytimes.network

import com.idriskhozema.nytimes.data.NyTimesData
import com.idriskhozema.nytimes.utils.AppConstants
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Idris Khozema on 28/07/2022.
 */
interface RetrofitServiceInstance {


    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?")
    suspend fun loadNyTimesArticles(
        @Query("api-key") key: String = AppConstants.apiKey
    ): Response<NyTimesData>
}