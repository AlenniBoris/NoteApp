package com.example.noteapplication.presentation.details.views

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.noteapplication.presentation.details.DetailsScreenViewModel
import com.example.noteapplication.presentation.uikit.views.AppTopBar
import com.example.noteapplication.presentation.uikit.views.AttachedFilesRow
import com.example.noteapplication.utils.OpenFilesFun

@Composable
fun DetailsScreen(
    navController: NavHostController,
    noteId: String,
    viewModel: DetailsScreenViewModel = hiltViewModel()
){

    val state by viewModel.screenState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getNoteById(noteId)
        if (!state.someErrorHappened){
            viewModel.getAllAttachedFiles(noteId)
        }
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        AppTopBar(
            needsBackButton = !state.isRefactoring,
            onBackButtonClicked = {
                navController.popBackStack()
            },
            needsEditButton = !state.isRefactoring,
            onEditButtonClicked = {
                viewModel.actionOnRefactoringButton()
            },
            needsDeleteAllButton = !state.isRefactoring,
            onDeleteAllButtonClicked = {
                val note = state.userNote
                viewModel.deleteNote(note!!)
                navController.popBackStack()
            },
            needsAcceptButton = state.isRefactoring,
            onAcceptButtonClicked = {
                viewModel.updateCurrentNote()
            },
            needsDeclineButton = state.isRefactoring,
            onDeclineButtonClicked = {
                viewModel.actionOnRefactoringButton()
            },
            needsPriorityButtons = state.isRefactoring,
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
                    when (
                        if(!state.isRefactoring) state.userNote?.priority else state.newNotePriority
                    ) {
                        1 -> Color.Red.copy(alpha = 0.55f)
                        2 -> Color.Yellow.copy(alpha = 0.55f)
                        3 -> Color.Green.copy(alpha = 0.55f)
                        else -> MaterialTheme.colorScheme.background
                    }
                )
                .padding(horizontal = 20.dp)
        ){

            Column{

                if (!state.isRefactoring) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 30.dp)
                            .fillMaxWidth(),
                        text = state.userNote?.title.toString(),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 30.sp
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = state.userNote?.content.toString(),
                        fontSize = 20.sp
                    )
                } else {
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
                }

                AttachedFilesRow(
                    attachedFilesList =
                        if (state.isRefactoring) state.newAttachedFiles
                        else state.attachedFiles,
                    isInRefactoringMode = state.isRefactoring,
                    onDetachAction = { attachedFile ->
                        viewModel.removeAttachedFile(attachedFile)
                    },
                    onAttachAction = { filePath, fileName ->
                        viewModel.addAttachedFile(filePath, fileName)
                    },
                    onFileClicked = { path ->
                        OpenFilesFun.invoke(context, path)
                    }
                )

            }
        }

    }
}