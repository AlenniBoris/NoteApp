package com.example.noteapplication.presentation.home.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.noteapplication.presentation.home.HomeScreenViewModel
import com.example.noteapplication.presentation.uikit.views.AppTopBar
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noteapplication.domain.model.Note


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
                    NoteItem(
                        note = note
                    )
                }
            }
        }

    }
}

@Composable
fun NoteItem(note: Note){
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .background(
                when(note.priority){
                    1 -> Color.Red.copy(alpha = 0.55f)
                    2 -> Color.Yellow.copy(alpha = 0.55f)
                    else -> Color.Green.copy(alpha = 0.55f)
                }
            )
            .padding(horizontal = 20.dp, vertical = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        Text(
            text = note.title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = note.contentPreview,
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}