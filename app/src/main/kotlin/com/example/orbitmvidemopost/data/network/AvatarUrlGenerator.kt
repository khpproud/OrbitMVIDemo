package com.example.orbitmvidemopost.data.network

class AvatarUrlGenerator {
    fun generateUrl(email: String) = "https://robohash.org/$email?set=set1"
}