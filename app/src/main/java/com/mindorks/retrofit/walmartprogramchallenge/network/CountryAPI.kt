package com.mindorks.retrofit.walmartprogramchallenge.network

import com.mindorks.retrofit.walmartprogramchallenge.model.CountryInfoItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface CountryAPI {
    @GET("/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountryInfo1(): Call<MutableList<CountryInfoItem>>

    @GET("/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountryInfo(): Response<MutableList<CountryInfoItem>>

}
