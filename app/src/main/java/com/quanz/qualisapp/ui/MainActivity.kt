package com.quanz.qualisapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.coroutineScope
import androidx.room.Room.databaseBuilder
import com.quanz.qualisapp.R
import com.quanz.qualisapp.db.QualisDatabase
import com.quanz.qualisapp.db.entities.PeriodicoEntity
import com.quanz.qualisapp.service.PeriodicosData
import com.quanz.qualisapp.service.QualisAppService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPeriodico()
    }

    private val service: QualisAppService = montaRetrofit().create(QualisAppService::class.java)

    private lateinit var periodico: PeriodicosData
    private val qualisDatabase by lazy {
        databaseBuilder(
            this, QualisDatabase::class.java, DATABASE_NAME
        ).build()
    }

    private fun montaRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    fun getPeriodico() {
        lifecycle.coroutineScope.launch {
            val periodicos = service.getPeriodicos()
            savePeriodico(periodicos.periodico)
        }
    }
    private fun savePeriodico(periodicos: List<List<String>>) {
        lifecycle.coroutineScope.launch {
            for (periodico in periodicos) {
                qualisTable().insertPeriodico(
                    PeriodicoEntity(
                        issn = periodico[0],
                        nome = periodico[1],
                        extratoCapes = periodico[2]
                    )
                )
            }
        }
    }

    private fun qualisTable() = qualisDatabase.qualisDao()

    companion object {
        private const val BASE_URL = "https://qualis.ic.ufmt.br/"
        private const val DATABASE_NAME = "DATABASE_NAME"
    }

}