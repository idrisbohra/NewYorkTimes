package com.idriskhozema.nytimes.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idriskhozema.nytimes.data.NyTimesData
import com.idriskhozema.nytimes.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Idris Khozema on 28/07/2022.
 */

@HiltViewModel
class NyTimesViewModel @Inject constructor(
    private val repository: NyTimesArticlesRepo
) :ViewModel() {


    private val _mutableData = MutableLiveData<DataState<NyTimesData>>()
    val liveData: LiveData<DataState<NyTimesData>>
        get() = _mutableData


    fun getArticles() {
        viewModelScope.launch {
            repository.fetchArticles().onStart {
                _mutableData.value = DataState.Loading
            }.map {
                _mutableData.value = it
            }.launchIn(viewModelScope)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}