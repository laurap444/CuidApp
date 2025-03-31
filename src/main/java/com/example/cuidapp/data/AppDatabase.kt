package com.example.cuidapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "damage_cases")
data class DamageCase(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val descripcion: String,
    val ubicacion: String,  // Formato "lat,lng"
    val foto: String?,  // Ruta de imagen
    val estado: String,  // "Pendiente", "En Proceso", "Cerrado"
    val fecha_registro: Long, // Guardado como timestamp
    val fecha_cierre: Long? // Puede ser null si no está cerrado
)