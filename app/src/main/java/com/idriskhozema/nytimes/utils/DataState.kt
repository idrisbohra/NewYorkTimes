package com.idriskhozema.nytimes.utils

/**
 * Created by Idris Khozema on 28/07/2022.
 */
sealed class DataState<out R> {

    object Loading : DataState<Nothing>()
    data class Success<out T>(val data: T?) : DataState<T>()
    data class Error(val message: String) : DataState<Nothing>()
    object ValidateSession : DataState<Nothing>()
    object ValidateAccessToken : DataState<Nothing>()
    data class NetworkError(val message: String) : DataState<Nothing>()
}

data class ConnectionTimeOut(val message: String)