package com.idriskhozema.nytimes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.idriskhozema.nytimes.data.NyTimesData
import com.idriskhozema.nytimes.mvvm.NyTimesArticlesRepo
import com.idriskhozema.nytimes.mvvm.NyTimesViewModel
import com.idriskhozema.nytimes.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class NyTimesViewModelTest {

    private lateinit var nyTimesViewModel: NyTimesViewModel
    private lateinit var nyTimesArticlesRepo : NyTimesArticlesRepo
    val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initSetUp(){
        Dispatchers.setMain(testDispatcher)
        nyTimesArticlesRepo = Mockito.mock(NyTimesArticlesRepo::class.java)
        nyTimesViewModel = NyTimesViewModel(nyTimesArticlesRepo)
    }

    @Test
    fun checkLoadingState_OnRequestInit_isTrue()= runBlocking{
        Mockito.`when`(nyTimesArticlesRepo.fetchArticles()).thenReturn(flow {
            emit(DataState.Loading)
        }.map {
            it
        })
        nyTimesViewModel.getArticles()
        Truth.assertThat(nyTimesViewModel.liveData.value).isEqualTo(DataState.Loading)
    }

    @Test
    fun onResponseReceived_checkFailedState_isError() = runBlocking {
        Mockito.`when`(nyTimesArticlesRepo.fetchArticles()).thenReturn(flow {
            emit(DataState.Error("Error"))
        }.map {
            it
        })
        nyTimesViewModel.getArticles()
        Truth.assertThat(nyTimesViewModel.liveData.value).isEqualTo(DataState.Error("Error"))
    }

    @Test
    fun onResponseReceived_checkSuccessState_isSuccess() = runBlocking {
        Mockito.`when`(nyTimesArticlesRepo.fetchArticles()).thenReturn(
            flow {
                emit(DataState.Success(NyTimesData()))
            }.map {
                it
            }
        )
        nyTimesViewModel.getArticles()
        Truth.assertThat(nyTimesViewModel.liveData.value).isEqualTo(DataState.Success(NyTimesData()))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

}