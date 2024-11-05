package com.example.noteapplication.presentation.home.views

import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.noteapplication.navigation.Screen
import com.example.noteapplication.presentation.uikit.views.AppTopBar

@Composable
fun HomeScreen(
    navController: NavHostController
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppTopBar(
            needsAddButton = true,
            onAddButtonClicked = {},
            needsSortButton = true,
            onSortButtonClicked = {},
            needsEditButton = true,
            onEditButtonClicked = {},
            needsAcceptButton = true,
            onAcceptButtonClicked = {},
            needsDeclineButton = true,
            onDeclineButtonClicked = {}
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Screen.Refactor.route)
            }
        ) {
            Text("Add screen")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Screen.Details.route + "12")
            }
        ) {
            Text("Details screen")
        }
    }
}