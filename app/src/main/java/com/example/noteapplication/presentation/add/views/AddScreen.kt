package com.example.noteapplication.presentation.add.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.noteapplication.domain.usecase.GetNoteByIdUseCase
import com.example.noteapplication.presentation.add.AddScreenViewModel
import com.example.noteapplication.presentation.uikit.views.AppTopBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddScreen(
    navController: NavHostController,
    viewModel: AddScreenViewModel = hiltViewModel()
){

    val state by viewModel.screenState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize().background(
            when(state.newNotePriority){
                1 -> Color.Red.copy(alpha = 0.55f)
                2 -> Color.Yellow.copy(alpha = 0.55f)
                3 -> Color.Green.copy(alpha = 0.55f)
                else -> MaterialTheme.colorScheme.background
            }
        )
    ) {

        AppTopBar(
            needsAcceptButton = true,
            onAcceptButtonClicked = {
                viewModel.addNewNote()
                navController.popBackStack()
            },
            needsDeclineButton = true,
            onDeclineButtonClicked = {
                navController.popBackStack()
            },
            needsPriorityButtons = true,
            onPriorityButtonClicked = { priority ->
                viewModel.updateNotePriority(priority)
            }
        )

        OutlinedTextField(
            value = state.newNoteTitle,
            onValueChange = { text -> viewModel.updateNoteTitleText(text) },
            label = { Text("Title") }
        )

        OutlinedTextField(
            value = state.newNoteContent,
            onValueChange = { text -> viewModel.updateNoteContentText(text) },
            label = { Text("Content") }
        )

    }
}