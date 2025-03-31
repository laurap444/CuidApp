package com.example.cuidapp.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.cuidapp.data.DamageCase
import com.example.cuidapp.data.DamageCaseDao

class DamageCaseRepository(private val damageCaseDao: DamageCaseDao) {

    suspend fun insert(damageCase: DamageCase) {
        withContext(Dispatchers.IO) {
            damageCaseDao.insert(damageCase)
        }
    }

    suspend fun update(damageCase: DamageCase) {
        withContext(Dispatchers.IO) {
            damageCaseDao.update(damageCase)
        }
    }

    suspend fun delete(damageCase: DamageCase) {
        withContext(Dispatchers.IO) {
            damageCaseDao.delete(damageCase)
        }
    }

    fun getOpenCases() = damageCaseDao.getOpenCases()
}
