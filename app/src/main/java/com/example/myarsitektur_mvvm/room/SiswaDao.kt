package com.example.myarsitektur_mvvm.room

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SiswaDao {

    @Query( "SELECT * from tblSiswa ORDER BY nama ASC")
    fun getAllSiswa(): Flow<List<Siswa>>



}