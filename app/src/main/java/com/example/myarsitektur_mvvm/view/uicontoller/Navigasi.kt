package com.example.myarsitektur_mvvm.view.uicontoller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myarsitektur_mvvm.model.Data.DataJK
import com.example.myarsitektur_mvvm.view.FormIsian
import com.example.myarsitektur_mvvm.view.TampilData
import com.example.myarsitektur_mvvm.view.viewmodel.SiswaViewModel

enum class Navigasi {
    Formulirku,
    Detail
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataApp(
    modifier: Modifier = Modifier,
    viewModel: SiswaViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // Sesuai gambar → ambil uiState dari ViewModel
    val uiState = viewModel.statusUI.collectAsState()

    Scaffold { isiRuang ->

        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulirku.name,
            modifier = Modifier.padding(isiRuang)
        ) {

            composable(route = Navigasi.Formulirku.name) {

                // Sesuai gambar → butuh context
                val konteks = LocalContext.current

                FormIsian(
                    JenisJK = DataJK.Jenis,
                    onSubmitButtonClicked = { hasilInput ->
                        viewModel.setSiswa(hasilInput)
                        navController.navigate(Navigasi.Detail.name)
                    }
                )

            }

            composable(route = Navigasi.Detail.name) {

                TampilData(
                    statusUISiswa = uiState.value,
                    onBackBtnClick = {
                        cancelAndBackToFormulir(navController)
                    }
                )
            }
        }
    }
}

private fun cancelAndBackToFormulir(
    navController: NavHostController
) {
    navController.popBackStack(
        Navigasi.Formulirku.name,
        inclusive = false
    )
}
