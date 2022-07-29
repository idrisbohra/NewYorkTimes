package com.idriskhozema.nytimes

import com.google.common.truth.Truth
import com.idriskhozema.nytimes.data.NyTimesData
import com.idriskhozema.nytimes.mvvm.NyTimesArticlesRepo
import com.idriskhozema.nytimes.utils.DataState
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class NyTimesArticlesRepositoryTest {

    @Mock
    lateinit var repoInstance: NyTimesArticlesRepo

    @Before
    fun init() {
        repoInstance = Mockito.mock(NyTimesArticlesRepo::class.java)

    }
    @Test
    fun validateAPI_isSuccess() {

        runBlocking {
            Mockito.`when`(repoInstance.fetchArticles()).thenReturn(
                flow {
                    emit(DataState.Success(NyTimesData(status="", copyright="", numResults=0, resultData= arrayListOf())))
                }.map {
                    it
                }
            )
        }

        runBlocking {
            Truth.assertThat(repoInstance.fetchArticles())
                .isEqualTo(DataState.Success(NyTimesData(status="", copyright="", numResults=0, resultData= arrayListOf())))
        }
    }

    @Test
    fun validateAPI_isFailed() {

        runBlocking {
            Mockito.`when`(repoInstance.fetchArticles()).thenReturn(
                null
            )
        }

        runBlocking {
            Truth.assertThat(repoInstance.fetchArticles())
                .isEqualTo(null)
        }
    }

}