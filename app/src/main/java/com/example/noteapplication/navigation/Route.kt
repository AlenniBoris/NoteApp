package com.example.noteapplication.navigation

sealed class Route(
    val route: String
) {
    data object HomeRoute: Route("homescreen")
    data object DetailsRoute: Route("detailsscreen/{id}")
    data object AddScreen: Route("addscreen")
}