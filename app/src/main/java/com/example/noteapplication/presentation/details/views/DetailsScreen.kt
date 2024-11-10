package com.example.noteapplication.presentation.details.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.noteapplication.presentation.details.DetailsScreenViewModel
import com.example.noteapplication.presentation.uikit.views.AppTopBar

@Composable
fun DetailsScreen(
    navController: NavHostController,
    id: String,
    viewModel: DetailsScreenViewModel = hiltViewModel()
){

    val state by viewModel.screenState.collectAsStateWithLifecycle()

    viewModel.getNoteById(id)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        AppTopBar(
            needsBackButton = true,
            onBackButtonClicked = {
                navController.popBackStack()
            },
            needsEditButton = true,
            onEditButtonClicked = {},
            needsDeleteAllButton = true,
            onDeleteAllButtonClicked = {
                val note = state.userNote
                viewModel.deleteNote(note!!)
                navController.popBackStack()
            }
        )

        Text(
            modifier = Modifier.padding(bottom = 15.dp).fillMaxWidth(),
            text = state.userNote?.title.toString(),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = state.userNote?.content.toString(),
            fontSize = 20.sp
        )

    }
}