package com.idriskhozema.nytimes.network

import com.idriskhozema.nytimes.data.NyTimesData
import retrofit2.Response
import javax.inject.Singleton

/**
 * Created by Idris Khozema on 28/07/2022.
 */
@Singleton
interface ApiServiceHelper {

    suspend fun fetchNyTimesArticles() : Response<NyTimesData>
}