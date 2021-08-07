package com.example.orbitmvidemopost.domain

sealed class Status<out T> {
    data class Success<out T>(val data: T) : Status<T>()
    data class Failure(val exception: Exception) : Status<Nothing>()
}