package com.example.noteapplication.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noteapplication.presentation.add.views.RefactorScreen
import com.example.noteapplication.presentation.details.views.DetailsScreen
import com.example.noteapplication.presentation.home.views.HomeScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    pd: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(pd)
    ){

        composable(Route.HomeRoute.route){
            HomeScreen(
                navController = navController
            )
        }

        composable(Route.RefactorScreen.route){
            RefactorScreen(
                navController = navController
            )
        }

        composable(Route.DetailsRoute.route){ navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            DetailsScreen(
                navController = navController,
                id = arguments.getString("id")
            )
        }
    }
}
