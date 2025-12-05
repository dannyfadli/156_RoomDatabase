package com.example.myarsitektur_mvvm.view.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myarsitektur_mvvm.view.EditSiswaScreen
import com.example.myarsitektur_mvvm.view.EntrySiswaScreen
import com.example.myarsitektur_mvvm.view.HomeScreen
import com.example.myarsitektur_mvvm.view.DetailSiswaScreen
import com.example.myarsitektur_mvvm.view.route.DestinasiDetailSiswa
import com.example.myarsitektur_mvvm.view.route.DestinasiEditSiswa
import com.example.myarsitektur_mvvm.view.route.DestinasiEntry
import com.example.myarsitektur_mvvm.view.route.DestinasiHome

@Composable
fun SiswaApp(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    HostNavigasi(navController = navController, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {

        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                navigateToItemUpdate = {
                    navController.navigate("${DestinasiDetailSiswa.route}/${it}")
                }
            )
        }

        composable(DestinasiEntry.route) {
            EntrySiswaScreen(navigateBack = { navController.popBackStack() })
        }

        composable(
            route = DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailSiswa.itemIdArg) { type = NavType.IntType })
        ) {
            DetailSiswaScreen(
                navigateToEdit = {
                    navController.navigate("${DestinasiEditSiswa.route}/${it}")
                },
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = DestinasiEditSiswa.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditSiswa.itemIdArg) { type = NavType.IntType })
        ) {
            EditSiswaScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
