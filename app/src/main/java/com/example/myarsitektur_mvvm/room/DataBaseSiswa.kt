package com.example.myarsitektur_mvvm.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Siswa::class], version = 1, exportSchema = false)
abstract class  DataBaseSiswa : RoomDatabase(){
    abstract  fun siswaDao() : SiswaDao
}