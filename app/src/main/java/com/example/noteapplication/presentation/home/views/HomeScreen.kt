package com.example.noteapplication.presentation.home.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.noteapplication.presentation.home.HomeScreenViewModel
import com.example.noteapplication.presentation.uikit.views.AppTopBar
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noteapplication.navigation.Screen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel()
){

    val state by viewModel.screenState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppTopBar(
            needsAddButton = true,
            onAddButtonClicked = {
                viewModel.addNote()
            },
            needsSortButton = true,
            onSortButtonClicked = {},
            needsEditButton = true,
            onEditButtonClicked = {},
            needsAcceptButton = true,
            onAcceptButtonClicked = {},
            needsDeclineButton = true,
            onDeclineButtonClicked = {},
            needsDeleteAllButton = true,
            onDeleteAllButtonClicked = {
                viewModel.deleteAllNotes()
            }
        )

        if(state.notesAreLoading){
            CircularProgressIndicator()
        }else if (state.someErrorHappened){
            Text(state.errorMessage)
        }else if(state.noNotesFound){
            Text("Nothing was noted")
        }else{
            LazyColumn(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .fillMaxSize()
            ) {
                items(state.listOfNotes){ note ->

                    Box(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {

                        ActionsRow(
                            actionIconSize = 40.dp,
                            onDelete = {
                                viewModel.deleteNoteBySwipe(note)
                            },
                            onEdit = {},
                            onPin = {}
                        )

                        NoteItem(
                            note = note,
                            isRevealed = viewModel.checkIfCardIsInRevealed(note.noteId),
                            noteOffset = 340f,
                            onExpand = { viewModel.onItemExpanded(note.noteId) },
                            onCollapse = { viewModel.onItemCollapsed(note.noteId) },
                            onClick = {
//                                navController.navigate(Screen.Details.route + note.noteId)
                                navController.navigate(Screen.Details.route + note.noteId)
                            }
                        )
                    }
                }
            }
        }

    }
}

