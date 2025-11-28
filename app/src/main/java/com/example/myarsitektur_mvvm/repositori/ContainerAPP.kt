package com.example.myarsitektur_mvvm.repositori

import android.app.Application
import android.content.Context
import androidx.compose.ui.unit.Constraints
import com.example.myarsitektur_mvvm.room.DataBaseSiswa

interface ContainerAPP {
    val repositoriSiswa: RepositoriSiswa
}

class ContainerDataApp(private val context: Context):
        ContainerAPP{
            override val repositoriSiswa: RepositoriSiswa by lazy {
                OfflineRepositoriSiswa(
                    DataBaseSiswa.getDatabase(context).siswaDao()
                )
            }
        }


class AplikasiSiswa : Application(){
    /**
     * appContainer instance digunakan oleh kelas kelas lainya untuk menyimpan ....
     */
    
    lateinit var container: ContainerAPP

    override fun  onCreate(){
        super.onCreate()
        container = ContainerDataApp( this)
    }
}