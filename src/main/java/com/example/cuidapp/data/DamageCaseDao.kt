package com.example.cuidapp.data

import androidx.room.*
import com.example.cuidapp.data.DamageCase

@Dao
interface DamageCaseDao {
    @Insert
    suspend fun insert(damageCase: DamageCase)

    @Update
    suspend fun update(damageCase: DamageCase)

    @Delete
    suspend fun delete(damageCase: DamageCase)

    @Query("SELECT * FROM damage_cases WHERE estado != 'Cerrado' ORDER BY fecha_registro DESC")
    fun getOpenCases(): List<DamageCase>

    @Query("SELECT * FROM damage_cases WHERE id = :id LIMIT 1")
    fun getCaseById(id: Int): DamageCase?
}