package com.example.noteapplication.presentation.add.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.noteapplication.presentation.add.AddScreenViewModel
import com.example.noteapplication.presentation.uikit.views.AppTopBar
import com.example.noteapplication.presentation.uikit.views.AttachedFilesRow

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddScreen(
    navController: NavHostController,
    viewModel: AddScreenViewModel = hiltViewModel()
){

    val state by viewModel.screenState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
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

        Box(
          modifier = Modifier
              .padding(horizontal = 15.dp)
              .fillMaxSize()
              .clip(RoundedCornerShape(10.dp))
              .background(
                  when(state.newNotePriority){
                      1 -> Color.Red.copy(alpha = 0.55f)
                      2 -> Color.Yellow.copy(alpha = 0.55f)
                      3 -> Color.Green.copy(alpha = 0.55f)
                      else -> MaterialTheme.colorScheme.background
                  }
              )
              .padding(horizontal = 20.dp)
        ){
            Column {

                OutlinedTextField(
                    value = state.newNoteTitle,
                    onValueChange = { text -> viewModel.updateNoteTitleText(text) },
                    label = { Text("Title") },
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = state.newNoteContent,
                    onValueChange = { text -> viewModel.updateNoteContentText(text) },
                    label = { Text("Content") },
                    modifier = Modifier.fillMaxWidth()
                )


                AttachedFilesRow(
                    attachedFilesList = state.attachedFilesList,
                    onDetachAction = { attachedFile ->
                        viewModel.removeAttachedFile(attachedFile)
                    },
                    onAttachAction = { filePath, fileName ->
                        viewModel.addAttachedFile(filePath, fileName)
                    },
                    isInRefactoringMode = true
                )

            }
        }

    }
}

