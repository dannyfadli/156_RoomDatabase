package com.example.myarsitektur_mvvm.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Siswa::class], version = 1, exportSchema = false)
abstract class  DataBaseSiswa : RoomDatabase(){
    abstract  fun siswaDao() : SiswaDao

    companion object{

        @Volatile
        private var Instance: DataBaseSiswa? = null

        fun getDatabase(context: Context): DataBaseSiswa {
            return (Instance?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    DataBaseSiswa::class.java,
                    "siswa_database")
                    .build().also { Instance = it }
            })
        }

    }

}
