package com.quanz.qualisapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.quanz.qualisapp.db.entities.PeriodicoEntity
import com.quanz.qualisapp.service.PeriodicosData
/*
Data access object(dao) é onde se cria o CRUD padrao e querys a serem executadas pelo app
 */
@Dao
interface QualisDao {
    @Query("SELECT * FROM periodico")
    suspend fun selectAllPeriodicos():List<PeriodicoEntity>

    @Insert(onConflict = REPLACE, entity = PeriodicoEntity::class)
    suspend fun insertPeriodico(vararg periodicoEntity: PeriodicoEntity)

    @Delete
    suspend fun deletePeriodico(periodicoEntity: PeriodicoEntity)

    @Query("DELETE FROM periodico")
    suspend fun deleteAllPeriodico(periodicosData: PeriodicosData)
}