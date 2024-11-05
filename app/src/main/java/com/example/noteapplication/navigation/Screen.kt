package com.example.noteapplication.navigation

sealed class Screen(
    val route: String,
){
    data object Home: Screen("homescreen")
    data object Details: Screen("detailsscreen/")
    data object Refactor: Screen("refactorscreen")
}