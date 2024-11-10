package com.example.noteapplication.presentation.details.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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


        Box(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .background(
                    when(state.userNote?.priority){
                        1 -> Color.Red.copy(alpha = 0.55f)
                        2 -> Color.Yellow.copy(alpha = 0.55f)
                        3 -> Color.Green.copy(alpha = 0.55f)
                        else -> MaterialTheme.colorScheme.background
                    }
                )
                .padding(horizontal = 20.dp)
        ){

            Column{

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

    }
}