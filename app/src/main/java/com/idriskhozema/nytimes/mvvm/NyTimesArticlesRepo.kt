package com.idriskhozema.nytimes.mvvm

import com.idriskhozema.nytimes.data.NyTimesData
import com.idriskhozema.nytimes.utils.DataState
import kotlinx.coroutines.flow.Flow

interface NyTimesArticlesRepo {

    suspend fun fetchArticles() : Flow<DataState<NyTimesData>>
}