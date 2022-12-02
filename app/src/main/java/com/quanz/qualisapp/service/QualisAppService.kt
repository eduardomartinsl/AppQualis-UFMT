package com.quanz.qualisapp.service

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET


interface QualisAppService {
    @GET("periodico.json")
    suspend fun getPeriodicos(): PeriodicosData

    @GET("todos2.json")
    suspend fun getCorrelacaoOutrasAreas(): List<List<String>>

    @GET("qualis_conferencias_2016.json")
    suspend fun getConferencias(): List<List<String>>
}

data class PeriodicosData(
    @SerializedName("data")
    val periodico: List<List<String>>
)
